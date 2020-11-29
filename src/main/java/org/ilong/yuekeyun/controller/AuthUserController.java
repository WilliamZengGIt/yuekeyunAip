package org.ilong.yuekeyun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author long
 * @date 2020-10-10 16:18
 */
@RestController
public class AuthUserController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
