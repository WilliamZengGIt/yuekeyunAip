
package org.ilong.yuekeyun.config;

import com.github.tobato.fastdfs.FdfsClientConstants;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorageNode;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.domain.proto.storage.StorageSetMetadataCommand;
import com.github.tobato.fastdfs.domain.proto.storage.StorageUploadFileCommand;
import com.github.tobato.fastdfs.domain.proto.storage.StorageUploadSlaveFileCommand;
import com.github.tobato.fastdfs.domain.proto.storage.enums.StorageMetadataSetType;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.domain.upload.ThumbImage;
import com.github.tobato.fastdfs.exception.FdfsUnsupportImageTypeException;
import com.github.tobato.fastdfs.exception.FdfsUploadImageException;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;

import org.ilong.yuekeyun.utils.FastDFSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * linux 中相应的命令 fdfs_upload_file /etc/fdfs/storage.conf anni.jpg 192.168.1.215:23000 5
 * 定制个人操作FastDfs Storage类
 * 功能：可指定上传文件的目录，其它功能和FastFileStorageCilent一致。上传功能--可指定上传目录。
 * 指定fastdfs 文件存储的stroage 相当于变相指定存储路径
 ** 核心：通过修改StorageNode 节点中的 storeIndex 的参数还指定上传目录
 * @return {@link }
 * @throws
 * @author long
 * @date 2020-10-14 10:10
 */


@Component
public class MyFastFileStorageClient extends DefaultGenerateStorageClient {

/**
     * 支持的图片类型
     */

    private static final List<String> SUPPORT_IMAGE_LIST = Arrays.asList(FdfsClientConstants.SUPPORT_IMAGE_TYPE);


/**
     * 缩略图生成配置
     */

    @Autowired
    private ThumbImageConfig thumbImageConfig;


/**
     * 上传文件
     */

    public StorePath uploadFile(InputStream inputStream, long fileSize,
                                String fileExtName, Set<MetaData> metaDataSet,byte per) {
        FastFile fastFile;
        if (null == metaDataSet) {
            fastFile = new FastFile.Builder()
                    .withFile(inputStream, fileSize, fileExtName)
                    .build();
        } else {
            fastFile = new FastFile.Builder()
                    .withFile(inputStream, fileSize, fileExtName)
                    .withMetaData(metaDataSet)
                    .build();
        }
        return uploadFile(fastFile,per);
    }


/**
     * 上传图片并且生成缩略图
     */


    public StorePath uploadImageAndCrtThumbImage(InputStream inputStream,
                                                 long fileSize,
                                                 String fileExtName,
                                                 Set<MetaData> metaDataSet,byte per) {
        FastImageFile fastImageFile;
        if (null == metaDataSet) {
            fastImageFile = new FastImageFile.Builder()
                    .withFile(inputStream, fileSize, fileExtName)
                    .withThumbImage()
                    .build();
        } else {
            fastImageFile = new FastImageFile.Builder()
                    .withFile(inputStream, fileSize, fileExtName)
                    .withMetaData(metaDataSet)
                    .withThumbImage()
                    .build();
        }
        return uploadImage(fastImageFile,per);
    }


/**
     * 上传文件
     * <pre>
     * 可通过fastFile对象配置
     * 1. 上传图像分组
     * 2. 上传元数据metaDataSet
     * <pre/>
     * @param fastFile
     * @return
     */


    public StorePath uploadFile(FastFile fastFile,byte per) {
        Validate.notNull(fastFile.getInputStream(), "上传文件流不能为空");
        Validate.notBlank(fastFile.getFileExtName(), "文件扩展名不能为空");
        // 获取存储节点
        StorageNode client = getStorageNode(fastFile.getGroupName());

        client.setStoreIndex(per);

        // 上传文件
        return uploadFileAndMetaData(client, fastFile.getInputStream(),
                fastFile.getFileSize(), fastFile.getFileExtName(),
                fastFile.getMetaDataSet());
    }


/**
     * 上传图片
     * <pre>
     * 可通过fastImageFile对象配置
     * 1. 上传图像分组
     * 2. 上传元数据metaDataSet
     * 3. 是否生成缩略图
     *   3.1 根据默认配置生成缩略图
     *   3.2 根据指定尺寸生成缩略图
     *   3.3 根据指定比例生成缩略图
     * <pre/>
     * @param fastImageFile
     * @return
     */


    public StorePath uploadImage(FastImageFile fastImageFile,byte per) {
        String fileExtName = fastImageFile.getFileExtName();
        Validate.notNull(fastImageFile.getInputStream(), "上传文件流不能为空");
        Validate.notBlank(fileExtName, "文件扩展名不能为空");
        // 检查是否能处理此类图片
        if (!isSupportImage(fileExtName)) {
            throw new FdfsUnsupportImageTypeException("不支持的图片格式" + fileExtName);
        }
        // 获取存储节点
        StorageNode client = getStorageNode(fastImageFile.getGroupName());

        client.setStoreIndex(per);
        byte[] bytes = inputStreamToByte(fastImageFile.getInputStream());

        // 上传文件和metaDataSet
        StorePath path = uploadFileAndMetaData(client, new ByteArrayInputStream(bytes),
                fastImageFile.getFileSize(), fileExtName,
                fastImageFile.getMetaDataSet());

        //如果设置了需要上传缩略图
        if (null != fastImageFile.getThumbImage()) {
            // 上传缩略图
            uploadThumbImage(client, new ByteArrayInputStream(bytes), path.getPath(), fastImageFile);
        }
        bytes = null;
        return path;
    }



/**
     * 获取存储Group
     *
     * @param groupName
     * @return
     */

    private StorageNode getStorageNode(String groupName) {
        if (null == groupName) {
            return trackerClient.getStoreStorage();
        } else {
            return trackerClient.getStoreStorage(groupName);
        }
    }


/**
     * 获取byte流
     *
     * @param inputStream
     * @return
     */

    private byte[] inputStreamToByte(InputStream inputStream) {
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            LOGGER.error("image inputStream to byte error", e);
            throw new FdfsUploadImageException("upload ThumbImage error", e.getCause());
        }
    }


/**
     * 检查是否有MetaData
     *
     * @param metaDataSet
     * @return
     */

    private boolean hasMetaData(Set<MetaData> metaDataSet) {
        return null != metaDataSet && !metaDataSet.isEmpty();
    }


/**
     * 是否是支持的图片文件
     *
     * @param fileExtName
     * @return
     */

    private boolean isSupportImage(String fileExtName) {
        return SUPPORT_IMAGE_LIST.contains(fileExtName.toUpperCase());
    }


/**
     * 上传文件和元数据
     *
     * @param client
     * @param inputStream
     * @param fileSize
     * @param fileExtName
     * @param metaDataSet
     * @return
     */

    private StorePath uploadFileAndMetaData(StorageNode client, InputStream inputStream, long fileSize,
                                            String fileExtName, Set<MetaData> metaDataSet) {
        // 上传文件
        StorageUploadFileCommand command = new StorageUploadFileCommand(client.getStoreIndex(), inputStream,
                fileExtName, fileSize, false);
        StorePath path = connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
        // 上传metadata
        if (hasMetaData(metaDataSet)) {
            StorageSetMetadataCommand setMDCommand = new StorageSetMetadataCommand(path.getGroup(), path.getPath(),
                    metaDataSet, StorageMetadataSetType.STORAGE_SET_METADATA_FLAG_OVERWRITE);
            connectionManager.executeFdfsCmd(client.getInetSocketAddress(), setMDCommand);
        }
        return path;
    }


/**
     * 上传缩略图
     *
     * @param client
     * @param inputStream
     * @param masterFilename
     * @param fastImageFile
     */

    private void uploadThumbImage(StorageNode client, InputStream inputStream,
                                  String masterFilename, FastImageFile fastImageFile) {
        ByteArrayInputStream thumbImageStream = null;
        ThumbImage thumbImage = fastImageFile.getThumbImage();
        try {
            //生成缩略图片
            thumbImageStream = generateThumbImageStream(inputStream, thumbImage);
            // 获取文件大小
            long fileSize = thumbImageStream.available();
            // 获取配置缩略图前缀
            String prefixName = thumbImage.getPrefixName();
            LOGGER.error("获取到缩略图前缀{}", prefixName);
            StorageUploadSlaveFileCommand command = new StorageUploadSlaveFileCommand(thumbImageStream, fileSize,
                    masterFilename, prefixName, fastImageFile.getFileExtName());
            connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);

        } catch (IOException e) {
            LOGGER.error("upload ThumbImage error", e);
            throw new FdfsUploadImageException("upload ThumbImage error", e.getCause());
        } finally {
            IOUtils.closeQuietly(thumbImageStream);
        }
    }


/**
     * 生成缩略图
     *
     * @param inputStream
     * @param thumbImage
     * @return
     * @throws IOException
     */

    private ByteArrayInputStream generateThumbImageStream(InputStream inputStream,
                                                          ThumbImage thumbImage) throws IOException {
        //根据传入配置生成缩略图
        if (thumbImage.isDefaultConfig()) {
            //在中间修改配置，这里不是一个很好的实践，如果有时间再进行优化
            thumbImage.setDefaultSize(thumbImageConfig.getWidth(), thumbImageConfig.getHeight());
            return generateThumbImageByDefault(inputStream);
        } else if (thumbImage.getPercent() != 0) {
            return generateThumbImageByPercent(inputStream, thumbImage);
        } else {
            return generateThumbImageBySize(inputStream, thumbImage);
        }

    }


/**
     * 根据传入比例生成缩略图
     *
     * @param inputStream
     * @param thumbImage
     * @return
     * @throws IOException
     */

    private ByteArrayInputStream generateThumbImageByPercent(InputStream inputStream,
                                                             ThumbImage thumbImage) throws IOException {
        LOGGER.debug("根据传入比例生成缩略图");
        // 在内存当中生成缩略图
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //@formatter:off
        Thumbnails
                .of(inputStream)
                .scale(thumbImage.getPercent())
                .imageType(BufferedImage.TYPE_INT_ARGB)
                .toOutputStream(out);
        //@formatter:on
        return new ByteArrayInputStream(out.toByteArray());
    }


/**
     * 根据传入尺寸生成缩略图
     *
     * @param inputStream
     * @param thumbImage
     * @return
     * @throws IOException
     */

    private ByteArrayInputStream generateThumbImageBySize(InputStream inputStream,
                                                          ThumbImage thumbImage) throws IOException {
        LOGGER.debug("根据传入尺寸生成缩略图");
        // 在内存当中生成缩略图
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //@formatter:off
        Thumbnails
                .of(inputStream)
                .size(thumbImage.getWidth(), thumbImage.getHeight())
                .imageType(BufferedImage.TYPE_INT_ARGB)
                .toOutputStream(out);
        //@formatter:on
        return new ByteArrayInputStream(out.toByteArray());
    }


/**
     * 获取缩略图
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private ByteArrayInputStream generateThumbImageByDefault(InputStream inputStream) throws IOException {
        LOGGER.debug("根据默认配置生成缩略图");
        // 在内存当中生成缩略图
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //@formatter:off
        Thumbnails
                .of(inputStream)
                .size(thumbImageConfig.getWidth(), thumbImageConfig.getHeight())
                .imageType(BufferedImage.TYPE_INT_ARGB)
                .toOutputStream(out);
        //@formatter:on
        return new ByteArrayInputStream(out.toByteArray());
    }


/**
     * 删除文件
     */


    public void deleteFile(String filePath) {
        StorePath storePath = StorePath.parseFromUrl(filePath);
        super.deleteFile(storePath.getGroup(), storePath.getPath());
    }

}

