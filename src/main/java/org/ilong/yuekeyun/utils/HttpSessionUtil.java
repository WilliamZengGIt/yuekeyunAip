package org.ilong.yuekeyun.utils;

import org.ilong.yuekeyun.bean.AuthUser;
import org.ilong.yuekeyun.service.impl.AuthUserServiceImpl;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-30 11:49
 */
@Component
public class HttpSessionUtil {

    public static AuthUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
              try {
                  AuthUser authUser=(AuthUser)authentication.getPrincipal();
                  if (authUser != null) {
                      return authUser;
                  }else {
                      throw  new AccessDeniedException("当前用户已失效，请重新登录！");
                  }
              }catch (ClassCastException e){
                  throw  new AccessDeniedException("当前用户已失效，请重新登录！");
              }



    }
}
