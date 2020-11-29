package org.ilong.yuekeyun.controller;

import org.ilong.yuekeyun.bean.Course;
import org.ilong.yuekeyun.bean.CourseComment;
import org.ilong.yuekeyun.bean.RespBean;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.request.CourseCommentRequest;
import org.ilong.yuekeyun.service.CourseCommentService;
import org.ilong.yuekeyun.service.CourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-25 10:30
 */
@RestController
@RequestMapping("/course")
public class CourseCommentController {

     @Autowired
     private CourseCommentService courseCommentService;
     @Autowired
     private CourseSectionService courseSectionService;

    /**
     * 加载评论&答疑
     * sectionId：章节id
     * courseId ：课程id
     * type : 类型
     * @return
     */
    @PostMapping("/segment")
    public RespBean segment(@RequestBody CourseCommentRequest courseCommentRequest){
        TailPage<CourseComment> page = new TailPage<>();
        System.out.println(courseCommentRequest.toString());
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
