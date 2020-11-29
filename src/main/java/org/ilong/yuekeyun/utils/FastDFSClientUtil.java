package org.ilong.yuekeyun.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import org.ilong.yuekeyun.config.MyFastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 直接用此类方法就可以将文件传入fastdfs图片服务器中
 *
 * @author long
 * @date 2020-10-12 22:41
 */
@Component
public class FastDFSClientUtil {

    @Autowired
    private MyFastFileStorageClient storageClient;

    @Value("${fdfs.web-server-url}")
    private  String fastdfsURL;

    /**
     * 上传文件 指定目录sh上传
     * @param file
     * @param per 指定的目录 通过 byte参数 代表着linux中的 store_path
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file,byte per) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null, per);
        String resAccessUrl = this.getResAccessUrl(storePath);

        return resAccessUrl;
    }

//    @Autowired
//    private AppConfig appConfig; // 项目参数配置





    /**
     * 上传图片 返回png格式的图片
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadQRCode(MultipartFile file,byte pre) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                "png", null,pre);
        String resAccessUrl = this.getResAccessUrl(storePath);
        return resAccessUrl;
    }

    /*public String uploadFace(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
                "png", null);

        return storePath.getPath();
    }*/

    public String uploadBase64(MultipartFile file,byte pre) throws IOException {
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
                "png", null,pre);
        String resAccessUrl = this.getResAccessUrl(storePath);
        return resAccessUrl;
    }

    /**
     * 将一段字符串生成一个文件上传
     *
     * @param content
     * 文件内容
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension,byte pre) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream, buff.length, fileExtension, null,pre);
        return storePath.getPath();
    }



    //封装图片完整URL地址
    private String getResAccessUrl(StorePath storePath) {
      String fileUrl = fastdfsURL
        + "/" + storePath.getFullPath();
     return fileUrl;
    }



    /**
     * 删除文件
     *
     * @param fileUrl
     * 文件访问地址
     * @return
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {

            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            e.getMessage();
        }
    }
}
