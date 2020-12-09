package org.ilong.yuekeyun.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.ilong.yuekeyun.Enum.CourseEnum;
import org.ilong.yuekeyun.bean.AuthUser;
import org.ilong.yuekeyun.bean.RespBean;
import org.ilong.yuekeyun.bean.UserCollections;
import org.ilong.yuekeyun.service.UserCollectionsService;
import org.ilong.yuekeyun.utils.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 课程收藏
 *
 * @author long
 * @date 2020-11-30 17:32
 */
@RestController
@RequestMapping("/collections")
@Api(tags = "课程收藏相关接口")
public class CollectionsController {

    @Autowired
    private UserCollectionsService userCollectionsService;

    @GetMapping("/doCollection/{courseId}")
    @ApiOperation("课程收藏接口")
    @ApiImplicitParam(name = "courseId", value = "被收藏课程id", defaultValue = "2", required = true)
    public RespBean doCollection(@PathVariable Long courseId) {
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        Long curUserId = user.getId();
        UserCollections userCollections = new UserCollections();
        userCollections.setUserId(curUserId);
        userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());//课程收藏
        userCollections.setObjectId(courseId);
        List<UserCollections> list = userCollectionsService.queryAll(userCollections);
        if (CollectionUtils.isNotEmpty(list)) {
            userCollectionsService.delete(list.get(0));
            return RespBean.ok("取消收藏！", false);
        } else {
            userCollections.setCreateTime(new Date());
            userCollectionsService.createSelectivity(userCollections);
            return RespBean.ok("收藏成功！", true);//已经收藏
        }
    }

    @GetMapping("/isCollection/{courseId}")
    @ApiOperation("课程是否收藏接口")
    @ApiImplicitParam(name = "courseId", value = "被收藏课程id", defaultValue = "2", required = true)
    public RespBean isCollection(@PathVariable Long courseId) {
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        Long curUserId = user.getId();
        UserCollections userCollections = new UserCollections();
        userCollections.setUserId(curUserId);
        userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());//课程收藏
        userCollections.setObjectId(courseId);
        List<UserCollections> list = userCollectionsService.queryAll(userCollections);

        if (CollectionUtils.isNotEmpty(list)) {//已经收藏
            return RespBean.ok("已收藏", true);
        } else {
            return RespBean.ok("未收藏", false);
        }


    }
}
