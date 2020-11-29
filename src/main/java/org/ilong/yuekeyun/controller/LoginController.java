package org.ilong.yuekeyun.controller;

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
public class LoginController {
    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录");
    }
}
