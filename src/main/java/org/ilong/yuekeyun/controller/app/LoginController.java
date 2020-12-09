package org.ilong.yuekeyun.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ilong.yuekeyun.bean.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TOOD
 *
 * @author long
 * @date 2020-10-10 23:01
 */
@RestController
@Api(tags = "未登录时，后端提示相关接口")
public class LoginController {

    @GetMapping("/login")
    @ApiOperation("提醒用户前去登录")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录");
    }
}
