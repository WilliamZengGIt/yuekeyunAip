package org.ilong.yuekeyun.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.ilong.yuekeyun.Enum.ImgDir;
import org.ilong.yuekeyun.bean.AuthUser;
import org.ilong.yuekeyun.bean.ImagesHistory;
import org.ilong.yuekeyun.bean.RespBean;
import org.ilong.yuekeyun.service.ImagesHistoryService;
import org.ilong.yuekeyun.utils.FastDFSClientUtil;
import org.ilong.yuekeyun.utils.HttpSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 与图片处理相关的接口
 *
 * @author long
 * @date 2020-12-04 10:40
 */
@RestController
@RequestMapping("/images/")
@Api(tags = "图片处理相关的接口")
public class ImagesController {

    @Autowired
    FastDFSClientUtil fastDFSClientUtil;
    @Autowired
    ImagesHistoryService imagesHistoryService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/upload")
    @ApiOperation("图片上传接口")
    @ApiImplicitParam(name = "ImgDir",
            value = "上传到服务器里的文件夹序号0:头像路径;1:轮播图存储路径;2:课程图存储路径;3:课程视频存储路径",
            defaultValue = "0", required = true)
    public RespBean ImagesUpload(@RequestParam("file") MultipartFile file,Byte ImgDir) throws IOException{
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        Set<Integer> allowSuffix = new HashSet<>(Arrays.asList(0, 1, 2, 3));
        if (!allowSuffix.contains(ImgDir.intValue())) {
            return RespBean.error("当前存储目录错误！" );
        }
        //判断是否是图片
        if (isImage(file)==false){
            return RespBean.error("非法图片,请用正确的图片！");
        }
        //上传图片
        String url = fastDFSClientUtil.uploadFile(file, ImgDir);
        //创建图片历史记录
        ImagesHistory imagesHistory = createImagesHistory(user, url);
        imagesHistory.setType(Byte.toUnsignedInt(ImgDir));
        imagesHistoryService.create(imagesHistory);
        //返回上传图片地址
        return RespBean.ok("上传成功！", url);

    }

    @PostMapping("/course")
    @ApiOperation("课程图片上传接口")
    public RespBean CourseImages(@RequestParam("file") MultipartFile file) throws IOException {
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        //使用ImageIo判断是否是图片
        //获取原始文件名称
        String filename = file.getOriginalFilename();
        //解析到文件后缀，判断是否合法
        int index = filename.lastIndexOf(".");
        String suffix = null;
        if (index == -1 || (suffix = filename.substring(index + 1)).isEmpty()) {
            return RespBean.error("非法文件,文件后缀不能为空");

        }
        //允许上传的文件的后缀
        Set<String> allowSuffix = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "gif"));
        if (!allowSuffix.contains(suffix.toLowerCase())) {
            return RespBean.error("非法文件，不允许的文件类型:" + suffix);

        }
        File tempFile = new File(System.getProperty("java.io.tmpdir"), filename);
         try {
        // 先把文件序列化到临时目录
        file.transferTo(tempFile);
        try {
            // 尝试IO文件，判断文件的合法性
            BufferedImage bufferedImage = ImageIO.read(tempFile);
            bufferedImage.getWidth();
            bufferedImage.getHeight();
        } catch (Exception e) {
            // IO异常，不是合法的图片文件，返回异常信息
          return RespBean.error("不是合法的图片文件");
        }
             //上传图片
             String url = fastDFSClientUtil.uploadFile(file, ImgDir.KeChengTu.ItoByte());
             //创建图片历史记录
             ImagesHistory imagesHistory = createImagesHistory(user, url);
             imagesHistory.setType(ImgDir.KeChengTu.getValue());
             imagesHistoryService.create(imagesHistory);
             //返回上传图片地址
             return RespBean.ok("上传成功！", url);
     } finally {
            // 响应客户端后，始终删除临时文件
            tempFile.delete();
        }
    }


    @PostMapping("/carousels")
    @ApiOperation("轮播图图片上传接口")
    public RespBean CarouselsImages(@RequestParam("file") MultipartFile file) throws IOException {
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        //使用ImageIo判断是否是图片
        if (isImage(file)==false){
            return RespBean.error("非法图片,请用正确的图片！");
        }
        //上传图片
        String url = fastDFSClientUtil.uploadFile(file, ImgDir.LunBoTu.ItoByte());
        //创建图片历史记录
        ImagesHistory imagesHistory = createImagesHistory(user, url);
        imagesHistory.setType(ImgDir.LunBoTu.getValue());
        imagesHistoryService.create(imagesHistory);
        //返回上传图片地址
        return RespBean.ok("上传成功！", url);
    }

    @PostMapping("/header")
    @ApiOperation("用户头像上传接口")
    public RespBean HeaderImages(@RequestParam("file") MultipartFile file) throws IOException {

        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        //判断文件后缀
        if (isImage(file)==false){
            return RespBean.error("非法图片,请用正确的图片！");
        }
        //上传图片
        String url = fastDFSClientUtil.uploadFile(file, ImgDir.TouXiang.ItoByte());
        //创建图片历史记录
        ImagesHistory imagesHistory = createImagesHistory(user, url);
        imagesHistory.setType(ImgDir.TouXiang.getValue());
        imagesHistoryService.create(imagesHistory);
        //返回上传图片地址
        return RespBean.ok("修改成功！", url);
    }

    @DeleteMapping("/deleteImg")
    @ApiOperation("物理删除图片接口")
    public RespBean deleteImg(@RequestParam("imgUrl") String imgUrl) {
        ImagesHistory images = imagesHistoryService.getImagesHistoryByHeader(imgUrl);
        if (images == null) {
            return RespBean.error("该图片不存在！");
        }
        fastDFSClientUtil.deleteFile(imgUrl);
        imagesHistoryService.delete(images);
        return RespBean.ok("删除成功！");
    }

    @DeleteMapping("/deleteLogicImg")
    @ApiOperation("逻辑删除用户接口")
    public RespBean deleteLogicImg(@RequestParam("imgUrl") String imgUrl) {
        ImagesHistory imagesHistoryByHeader = imagesHistoryService.getImagesHistoryByHeader(imgUrl);
        if (imagesHistoryByHeader == null) {
            return RespBean.error("该图片不存在！");
        }
        imagesHistoryService.deleteLogic(imagesHistoryByHeader);
        return RespBean.ok("逻辑删除成功！");
    }

    public static ImagesHistory createImagesHistory(AuthUser user, String url) {
        ImagesHistory imagesHistory = new ImagesHistory();
        imagesHistory.setUserId(user.getId());
        imagesHistory.setHeader(url);
        imagesHistory.setUserMobile(user.getMobile());
        imagesHistory.setCreateTime(new Date());
        imagesHistory.setCreateUser(user.getTrueUsername());
        imagesHistory.setUpdateTime(new Date());
        imagesHistory.setUpdateUser(user.getTrueUsername());
        return imagesHistory;
    }
    //简单的判断文件后缀，来判断是不是图片
    public static boolean isImage(MultipartFile file){
        //获取原始文件名称
        String filename = file.getOriginalFilename();
        //解析到文件后缀，判断是否合法
        int index = filename.lastIndexOf(".");
        String suffix = null;
        if (index == -1 || (suffix = filename.substring(index + 1)).isEmpty()) {
           /* return RespBean.error("非法文件,文件后缀不能为空");*/
            return false;
        }
        //允许上传的文件的后缀
        Set<String> allowSuffix = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "gif"));
        if (!allowSuffix.contains(suffix.toLowerCase())) {
         /*   return RespBean.error("非法文件，不允许的文件类型:" + suffix);*/
            return false;
        }
        return true;
    }

    //使用ImagesIo判断是否是图片，会消耗性能，一般不采用.
    public static boolean isImagebyIO(MultipartFile file) throws IOException {
        //获取原始文件名称
        String filename = file.getOriginalFilename();
        //解析到文件后缀，判断是否合法
        int index = filename.lastIndexOf(".");
        String suffix = null;
        if (index == -1 || (suffix = filename.substring(index + 1)).isEmpty()) {
            /*return RespBean.error("非法文件,文件后缀不能为空");*/
            return false;
        }
        //允许上传的文件的后缀
        Set<String> allowSuffix = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "gif"));
        if (!allowSuffix.contains(suffix.toLowerCase())) {
           /* return RespBean.error("非法文件，不允许的文件类型:" + suffix);*/
            return false;
        }
        File tempFile = new File(System.getProperty("java.io.tmpdir"), filename);
       /* try {*/
            // 先把文件序列化到临时目录
            file.transferTo(tempFile);
            try {
                // 尝试IO文件，判断文件的合法性
                BufferedImage bufferedImage = ImageIO.read(tempFile);
                bufferedImage.getWidth();
                bufferedImage.getHeight();
            } catch (Exception e) {
                // IO异常，不是合法的图片文件，返回异常信息
             /* return RespBean.error("不是合法的图片文件");*/
                return false;
            }
     /*   } finally {
            // 响应客户端后，始终删除临时文件
            tempFile.delete();
        }*/
      /*  return RespBean.ok("");*/
        return true;
    }
}
