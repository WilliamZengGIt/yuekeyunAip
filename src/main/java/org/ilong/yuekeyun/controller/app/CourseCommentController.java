package org.ilong.yuekeyun.controller.app;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.ilong.yuekeyun.bean.AuthUser;
import org.ilong.yuekeyun.bean.CourseComment;
import org.ilong.yuekeyun.bean.CourseSection;
import org.ilong.yuekeyun.bean.RespBean;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.request.CourseCommentRequest;
import org.ilong.yuekeyun.bean.request.doCommentRequest;
import org.ilong.yuekeyun.service.CourseCommentService;
import org.ilong.yuekeyun.service.CourseSectionService;
import org.ilong.yuekeyun.utils.HttpSessionUtil;
import org.ilong.yuekeyun.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-25 10:30
 */
@RestController
@RequestMapping("/courseComment")
@Api(tags = "课程评论相关接口")
public class CourseCommentController {

     @Autowired
     private CourseCommentService courseCommentService;
     @Autowired
     private CourseSectionService courseSectionService;
     @Autowired
     RedisUtil redisUtil;

     @PostMapping("/doComment")
     @ApiOperation("发表评论接口")
     public RespBean doComment(@RequestBody  doCommentRequest doCommentRequest){
         //获取当前用户
         AuthUser user = HttpSessionUtil.getUser();
        //验证码判断
        String indeityCode= doCommentRequest.getIndeityCode();
         if(null == doCommentRequest.getIndeityCode() ||
                 (indeityCode != null && !indeityCode.equals(
                         redisUtil.get(doCommentRequest.getcToken())))){
             return RespBean.error("验证码错误!");//验证码错误
         }
         //文字太长
         CourseComment entity=doCommentRequest.getCourseComment();
         if(entity.getContent().trim().length() > 200
                 || entity.getContent().trim().length() == 0){
             return RespBean.error("文字太长或者为空!");//文字太长或者为空
         }
         if(null != entity.getRefId()){//来自于个人中心评论
             CourseComment refComment = this.courseCommentService.getById(entity.getRefId());
             if(null != refComment){
                 CourseSection courseSection = courseSectionService.getById(refComment.getSectionId());
                 if(null != courseSection){
                     entity.setRefContent(refComment.getContent());
                     entity.setRefId(entity.getRefId());
                     entity.setCourseId(refComment.getCourseId());
                     entity.setSectionId(refComment.getSectionId());
                     entity.setSectionTitle(courseSection.getName());

                     entity.setToUsername(refComment.getUsername());//引用的评论的username
                     entity.setUsername(user.getUsername());
                     entity.setCreateTime(new Date());
                     entity.setCreateUser(user.getUsername());
                     entity.setUpdateTime(new Date());
                     entity.setUpdateUser(user.getUsername());

                     this.courseCommentService.createSelectivity(entity);
                     return RespBean.ok("发布成功!");
                 }
             }
         }else{
             CourseSection courseSection = courseSectionService.getById(entity.getSectionId());
             if(null != courseSection){
                 entity.setSectionTitle(courseSection.getName());
                 entity.setToUsername(entity.getCreateUser());//toUsername可以作为页面入参
                 entity.setUsername(user.getUsername());
                 entity.setCreateTime(new Date());
                 entity.setCreateUser(user.getUsername());
                 entity.setUpdateTime(new Date());
                 entity.setUpdateUser(user.getUsername());

                 this.courseCommentService.createSelectivity(entity);
                 return RespBean.ok("发布成功!");
             }
         }

        return RespBean.ok("");
     }
    /**
     * 加载评论&答疑
     * sectionId：章节id
     * courseId ：课程id
     * type : 类型
     * @return
     */
    @PostMapping("/segment")
    @ApiOperation("加载评论&答疑")
    public RespBean segment(@RequestBody CourseCommentRequest courseCommentRequest){
        TailPage<CourseComment> page = new TailPage<>();
        if(null == courseCommentRequest.getCourseComment().getCourseId()
                || courseCommentRequest.getCourseComment().getType() == null){
            return RespBean.error("该课程不存在！");
        }
        if (courseCommentRequest.getSort().equals("pop")){
            page.descSortField("studyCount");
        }else {
            page.descSortField("id");
        }
        page.setPageNum(courseCommentRequest.getCurrentPage());
        page.setPageSize(courseCommentRequest.getPagesize());
        page = courseCommentService.queryPage(courseCommentRequest.getCourseComment(), page);
        return RespBean.ok("加载成功！",page);
    }
}
