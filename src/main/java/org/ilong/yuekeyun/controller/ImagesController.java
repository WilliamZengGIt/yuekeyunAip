package org.ilong.yuekeyun.controller;

import io.swagger.annotations.Api;
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

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 与图片处理相关的接口
 *
 * @author long
 * @date 2020-12-04 10:40
 */
@RestController
@RequestMapping("/Images/")
@Api(tags = "图片处理相关的接口")
public class ImagesController {

    @Autowired
    FastDFSClientUtil fastDFSClientUtil;
    @Autowired
    ImagesHistoryService imagesHistoryService;

    Logger logger= LoggerFactory.getLogger(getClass());

    @PostMapping("/course")
    @ApiOperation("课程图片上传接口")
    public RespBean CourseImages(@RequestParam("file") MultipartFile file) throws IOException{

        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        //上传图片
        String url = fastDFSClientUtil.uploadFile(file, ImgDir.KeChengTu.ItoByte());
        //创建图片历史记录
        ImagesHistory imagesHistory = createImagesHistory(user, url);
        imagesHistory.setType(ImgDir.KeChengTu.getValue());
        imagesHistoryService.create(imagesHistory);
        //返回上传图片地址
        return RespBean.ok("上传成功！",url);
    }


    @PostMapping("/carousels")
    @ApiOperation("轮播图图片上传接口")
    public RespBean CarouselsImages(@RequestParam("file") MultipartFile file) throws IOException{
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        //上传图片
        String url = fastDFSClientUtil.uploadFile(file, ImgDir.LunBoTu.ItoByte());
        //创建图片历史记录
        ImagesHistory imagesHistory = createImagesHistory(user, url);
        imagesHistory.setType(ImgDir.LunBoTu.getValue());
        imagesHistoryService.create(imagesHistory);
        //返回上传图片地址
        return RespBean.ok("上传成功！",url);
    }

    @PostMapping("/header")
    @ApiOperation("用户头像上传接口")
    public RespBean HeaderImages(@RequestParam("file") MultipartFile file) throws IOException
    {

        //获取当前用户
         AuthUser user = HttpSessionUtil.getUser();
        //上传图片
         String url = fastDFSClientUtil.uploadFile(file, ImgDir.TouXiang.ItoByte());
        //创建图片历史记录
        ImagesHistory imagesHistory = createImagesHistory(user, url);
        imagesHistory.setType(ImgDir.TouXiang.getValue());
        imagesHistoryService.create(imagesHistory);
        //返回上传图片地址
        return RespBean.ok("修改成功！",url);
    }
    @GetMapping("/deleteImg")
    @ApiOperation("物理删除图片接口")
    public RespBean deleteImg(@RequestParam("imgUrl") String imgUrl){
        ImagesHistory images = imagesHistoryService.getImagesHistoryByHeader(imgUrl);
        if (images==null){
            return RespBean.error("该图片不存在！");
        }
        fastDFSClientUtil.deleteFile(imgUrl);
        imagesHistoryService.delete(images);
        return RespBean.ok("删除成功！");
    }

    @GetMapping("/deleteLogicImg")
    @ApiOperation("逻辑删除用户接口")
    public RespBean deleteLogicImg(@RequestParam("imgUrl") String imgUrl){
         ImagesHistory imagesHistoryByHeader = imagesHistoryService.getImagesHistoryByHeader(imgUrl);
         if (imagesHistoryByHeader==null){
             return RespBean.error("该图片不存在！");
         }
        imagesHistoryService.deleteLogic(imagesHistoryByHeader);
        return RespBean.ok("逻辑删除成功！");
    }

    public static ImagesHistory createImagesHistory(AuthUser user,String url){
        ImagesHistory imagesHistory =new ImagesHistory();
        imagesHistory.setUserId(user.getId());
        imagesHistory.setHeader(url);
        imagesHistory.setUserMobile(user.getMobile());
        imagesHistory.setCreateTime(new Date());
        imagesHistory.setCreateUser(user.getTrueUsername());
        imagesHistory.setUpdateTime(new Date());
        imagesHistory.setUpdateUser(user.getTrueUsername());
        return imagesHistory;
    }
}
