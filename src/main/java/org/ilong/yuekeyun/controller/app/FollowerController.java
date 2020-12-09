package org.ilong.yuekeyun.controller.app;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.collections.CollectionUtils;
import org.ilong.yuekeyun.bean.AuthUser;
import org.ilong.yuekeyun.bean.RespBean;
import org.ilong.yuekeyun.bean.UserFollows;
import org.ilong.yuekeyun.service.UserFollowsService;
import org.ilong.yuekeyun.utils.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


/**
 * TOOD
 *
 * @author long
 * @date 2020-11-30 11:32
 */
@RestController
@RequestMapping("/follow")
@Api(tags = "用户关注操作相关接口")
public class FollowerController {
    @Autowired
    private UserFollowsService userFollowsService;

    @GetMapping(value = "/doFollow/{followId}")
    @ApiOperation("用户关注接口")
    @ApiImplicitParam(name = "followId", value = "被关注用户id", defaultValue = "2", required = true)
    public RespBean doFollow(@PathVariable Long followId){
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        Long curUserId = user.getId();
        UserFollows userFollows = new UserFollows();
        userFollows.setUserId(curUserId);
        userFollows.setFollowId(followId);
        List<UserFollows> list = userFollowsService.queryAll(userFollows);
        if (CollectionUtils.isNotEmpty(list)){
            userFollowsService.delete(list.get(0));
            return RespBean.ok("取消关注！","未关注");
        }else {
            userFollows.setCreateTime(new Date());
            userFollows.setUpdateTime(new Date());
            userFollowsService.createSelectivity(userFollows);
            return RespBean.ok("关注成功！","已关注");
        }

    }

    /**
     * 是否已经关注
     */
    @GetMapping(value = "/isFollow/{followId}")
    @ApiOperation("用户是否关注接口")
    @ApiImplicitParam(name = "followId", value = "被关注用户id", defaultValue = "2", required = true)
    public RespBean isFollow(@PathVariable Long followId){
        //获取当前用户
        AuthUser user = HttpSessionUtil.getUser();
        Long curUserId = user.getId();
        UserFollows userFollows = new UserFollows();
        userFollows.setUserId(curUserId);
        userFollows.setFollowId(followId);
        List<UserFollows> list = userFollowsService.queryAll(userFollows);

        if(CollectionUtils.isNotEmpty(list)){//已经关注
            return RespBean.ok("已关注",0);
        }else{
            return RespBean.ok("未关注",1);
        }

    }

}
