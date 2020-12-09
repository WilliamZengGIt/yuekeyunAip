package org.ilong.yuekeyun.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.ilong.yuekeyun.Enum.CourseEnum;
import org.ilong.yuekeyun.bean.*;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.dto.CourseQueryDto;
import org.ilong.yuekeyun.bean.request.CourseRequest;
import org.ilong.yuekeyun.bean.request.CurLeanInfoRequest;
import org.ilong.yuekeyun.bean.vo.CourseSectionVO;
import org.ilong.yuekeyun.business.CourseBusiness;
import org.ilong.yuekeyun.service.*;
import org.ilong.yuekeyun.utils.BeanUtil;
import org.ilong.yuekeyun.utils.HttpSessionUtil;
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
@Api(tags = "课程相关接口")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseBusiness courseBusiness;

    @Autowired
    CourseSectionService courseSectionService;

    @Autowired
    UserCourseSectionService userCourseSectionService;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    private UserFollowsService userFollowsService;

    @Autowired
    private UserCollectionsService userCollectionsService;

    //加载当前用户学习最新课程
    @PostMapping("/CurLeanInfo")
    @ApiOperation("加载当前用户学习最新课程接口")
    public RespBean getCurLeanInfo(@RequestBody CurLeanInfoRequest curLeanInfo){
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
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
    @GetMapping("/learn/{id}")
    @ApiOperation("根据id获取课程，课程章节，讲师，推荐课程接口")
    @ApiImplicitParam(name = "id", value = "课程id", defaultValue = "1", required = true)
    public RespBean CoureseInfo(@PathVariable Long id){
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
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
        //获取讲师
        AuthUser courseTeacher = this.authUserService.getByUsername(course.getUsername());
        map.put("courseTeacher",courseTeacher);

        //获取推荐课程
        CourseQueryDto queryEntity = new CourseQueryDto();
        queryEntity.descSortField("weight");
        queryEntity.setCount(5);//5门推荐课程
        queryEntity.setSubClassify(course.getSubClassify());
        List<Course> recomdCourseList = this.courseService.queryList(queryEntity);
        map.put("recomdCourseList", recomdCourseList);

        //获取用户是否关注
        Long curUserId = user.getId();
        UserFollows userFollows = new UserFollows();
        userFollows.setUserId(curUserId);
        userFollows.setFollowId(courseTeacher.getId());
        List<UserFollows> UserFollowslist = userFollowsService.queryAll(userFollows);
        if(CollectionUtils.isNotEmpty(UserFollowslist)){//已经关注
            map.put("isFollow", "已关注");
        }else{
            map.put("isFollow", "未关注");
        }
        //获取用户是否收藏
        UserCollections userCollections = new UserCollections();

        userCollections.setUserId(curUserId);
        userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());//课程收藏
        userCollections.setObjectId(course.getId());
        List<UserCollections> Collectionslist = userCollectionsService.queryAll(userCollections);
        if (CollectionUtils.isNotEmpty(Collectionslist)) {//已经收藏
             map.put("isCollect", true);
        } else {
            map.put("isCollect", false);
        }
        //加载当前用户学习最新课程
        UserCourseSection userCourseSection = new UserCourseSection();
        userCourseSection.setUserId(user.getId());
        userCourseSection.setCourseId(course.getId());
        userCourseSection = userCourseSectionService.queryLatest(userCourseSection);
        if(null != userCourseSection){
            CourseSection result = this.courseSectionService.getById(userCourseSection.getSectionId());
            map.put("result",result);
        }else {
            map.put("result",null);
        }


        return RespBean.ok("",map);

    }

    @PostMapping("/queryPage")
    @ApiOperation("分页获取课程接口")
    public RespBean queryPage(@RequestBody CourseRequest courseRequest){

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
    @GetMapping("/video/{courseId}/sectionId/{sectionId}")
    @ApiOperation("根据课程id与章节id进入视频学习接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id", defaultValue = "1", required = true
            ),
            @ApiImplicitParam(name = "courseId", value = "课程id", defaultValue = "6", required = true
            )
    }
    )
    public RespBean video(@PathVariable("courseId") Long courseId,
                          @PathVariable("sectionId") Long sectionId){
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        HashMap<String, Object> map = new HashMap<>();
        if (courseId==null){
            return RespBean.error("课程id不能为空!");
        }
        if (sectionId==null){
            return RespBean.error("章节id不能为空!");
        }
        //获取课程
        Course course = courseService.getById(courseId);
        if (course==null){
            RespBean.error("该课程不存在!");
        }
        map.put("course",course);
        //获取当前章节
        CourseSection section = courseSectionService.getById(sectionId);
        map.put("section",section);
        //课程所有章节 chaptSections
        List<CourseSectionVO> chaptSections =courseBusiness.queryCourseSection(courseId);
        map.put("chaptSections",chaptSections);
        //学习记录 result
        UserCourseSection userCourseSection = new UserCourseSection();
        userCourseSection.setUserId(user.getId());
        userCourseSection.setCourseId(courseId);
        userCourseSection.setSectionId(sectionId);
        UserCourseSection result = userCourseSectionService.queryLatest(userCourseSection);
        map.put("result",result);
        if(null == result){//如果没有，插入
            userCourseSection.setCreateTime(new Date());
            userCourseSection.setCreateUser(user.getUsername());
            userCourseSection.setUpdateTime(new Date());
            userCourseSection.setUpdateUser(user.getUsername());
            userCourseSectionService.createSelectivity(userCourseSection);
        }else{
            result.setUpdateTime(new Date());
            userCourseSectionService.update(result);
        }
        return RespBean.ok("",map);
    }

}
