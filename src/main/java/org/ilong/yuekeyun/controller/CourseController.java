package org.ilong.yuekeyun.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.ilong.yuekeyun.bean.*;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.request.CourseRequest;
import org.ilong.yuekeyun.bean.request.CurLeanInfoRequest;
import org.ilong.yuekeyun.bean.vo.CourseSectionVO;
import org.ilong.yuekeyun.business.CourseBusiness;
import org.ilong.yuekeyun.service.CourseSectionService;
import org.ilong.yuekeyun.service.CourseService;
import org.ilong.yuekeyun.service.UserCourseSectionService;
import org.ilong.yuekeyun.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *课程控制器
 * @author long
 * @date 2020-11-13 16:18
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseBusiness courseBusiness;

    @Autowired
    CourseSectionService courseSectionService;

    @Autowired
    UserCourseSectionService userCourseSectionService;

    //加载当前用户学习最新课程
    @PostMapping("/CurLeanInfo")
    public RespBean getCurLeanInfo(@RequestBody CurLeanInfoRequest curLeanInfo){
        UserCourseSection userCourseSection = new UserCourseSection();
        userCourseSection.setUserId(curLeanInfo.getUserId());
        userCourseSection.setCourseId(curLeanInfo.getCourseId());
        userCourseSection = userCourseSectionService.queryLatest(userCourseSection);
        if(null != userCourseSection){
            CourseSection result = this.courseSectionService.getById(userCourseSection.getSectionId());
            return RespBean.ok("",result);
        }
        return RespBean.ok("");
    }
    @GetMapping("/learn")
    public RespBean CoureseInfo(@RequestParam Long id){
        HashMap<String, Object> map = new HashMap<>();
        //获取课程
        Course course = courseService.getById(id);
        if (course==null){
            RespBean.error("该课程不存在!");
        }
        map.put("course",course);
        //获取课程章节
        List<CourseSectionVO> chaptSections = courseBusiness.queryCourseSection(id);
        map.put("chaptSections",chaptSections);
        return RespBean.ok("",map);

    }

    @PostMapping("/queryPage")
    public RespBean queryPage(@RequestBody CourseRequest courseRequest){
        System.out.println(courseRequest.toString());
        TailPage<Course> page = new TailPage<>();
        if (courseRequest.getSort().equals("pop")){
            page.descSortField("studyCount");
        }else {
            page.descSortField("id");
        }
        page.setPageNum(courseRequest.getCurrentPage());
        page.setPageSize(courseRequest.getPagesize());
        page = courseService.queryPage(courseRequest.getCourse(), page);
        return RespBean.ok("",page);
    }

}
