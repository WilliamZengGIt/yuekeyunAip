package org.ilong.yuekeyun.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ilong.yuekeyun.Enum.ImgDir;
import org.ilong.yuekeyun.bean.*;
import org.ilong.yuekeyun.service.CourseSectionService;
import org.ilong.yuekeyun.service.CourseService;
import org.ilong.yuekeyun.service.VideoHistoryService;
import org.ilong.yuekeyun.utils.FastDFSClientUtil;
import org.ilong.yuekeyun.utils.HttpSessionUtil;
import org.ilong.yuekeyun.utils.VideoUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaInfo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TOOD
 *
 * @author long
 * @date 2020-12-07 14:13
 */
@RestController
@RequestMapping("/video")
@Api(tags = "课程视频操作相关接口")
public class VideoController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    FastDFSClientUtil fastDFSClientUtil;
    @Autowired
    VideoUploadUtils videoUploadUtils;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseSectionService courseSectionService;

    @Autowired
    VideoHistoryService videoHistoryService;


    @PostMapping("/upload")
    @ApiOperation("视频上传接口")
    public RespBean VideoUpload(@RequestParam("file") MultipartFile file,
                                long courseId,long sectionId) throws IOException {
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        //判断课程是否存在 通过courseId和sectionId查询
        Course course = courseService.getById(courseId);
        if(course==null){
            return RespBean.error("该课程不存在！");
        }
        CourseSection courseSection = courseSectionService.getById(sectionId);
        if (courseSection==null){
            return RespBean.error("该课程下不存在该章节！");
        }
        //获取原始文件名称
        String filename = file.getOriginalFilename();
        //解析到文件的后缀判断其合法性
        int index = filename.lastIndexOf(".");
        String suffix = null;
        if (index == -1 || (suffix = filename.substring(index + 1)).isEmpty()) {
            return RespBean.error("非法文件,文件后缀不能为空");
        }
        //允许上传的文件的后缀
        Set<String> allowSuffix = new HashSet<>(Arrays.asList("avi", "mp4", "mov", "wmv","flv","mpeg"));
        if (!allowSuffix.contains(suffix.toLowerCase())) {
             return RespBean.error("非法文件，不允许的文件类型:" + suffix);
        }

       //上传视频到服务器上
        String url = fastDFSClientUtil.uploadFile(file, ImgDir.KeChengVideo.ItoByte());
        //获取视频的时长
        String videoTime = videoUploadUtils.ReadVideoTime(url);
        //创建视频历史记录
        VideoHistory videoHistory=new VideoHistory();
        videoHistory.setUserId(user.getId());
        videoHistory.setUserMobile(user.getMobile());
        videoHistory.setCourseId(courseId);
        videoHistory.setSectionId(sectionId);
        videoHistory.setVideoUrl(url);
        videoHistory.setVideoName(courseSection.getName());
        videoHistory.setTime(videoTime);
        videoHistory.setCreateTime(new Date());
        videoHistory.setCreateUser(user.getTrueUsername());
        videoHistory.setUpdateTime(new Date());
        videoHistory.setUpdateUser(user.getTrueUsername());
        //插入数据库
        videoHistoryService.create(videoHistory);
        return RespBean.ok("上传成功！");
    }



}
