package org.ilong.yuekeyun.service.impl;

import org.ilong.yuekeyun.bean.AuthUser;
import org.ilong.yuekeyun.mapper.AuthUserMapper;
import org.ilong.yuekeyun.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * 用户登录
 *
 * @author long
 * @date 2020-10-10 16:03
 */
@Service
public class AuthUserServiceImpl implements UserDetailsService ,AuthUserService{

    @Autowired
    AuthUserMapper authUserMapper;

    //spring security 登录方法
    @Override
    public UserDetails loadUserByUsername(String mobile)
            throws UsernameNotFoundException {
        AuthUser authUser = authUserMapper.loadUserByUsername(mobile);
        if(authUser==null){
            throw new UsernameNotFoundException("用户不存在");
        }


        return authUser;
    }



}
