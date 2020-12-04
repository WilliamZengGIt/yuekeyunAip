package org.ilong.yuekeyun.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.ilong.yuekeyun.bean.AuthUser;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.mapper.AuthUserMapper;
import org.ilong.yuekeyun.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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


    public void createSelectivity(AuthUser entity){
        authUserMapper.createSelectivity(entity);
    }

    /**
     *根据username获取
     **/
    public AuthUser getByUsername(String username){
        return authUserMapper.getByUsername(username);
    }



    public AuthUser getById(Long id){
        return authUserMapper.getById(id);
    }

    /**
     *根据username和password获取
     **/
    public AuthUser getByUsernameAndPassword(AuthUser authUser){
        return authUserMapper.getByUsernameAndPassword(authUser);
    }

    /**
     *获取首页推荐5个讲师
     **/
    public List<AuthUser> queryRecomd(){
        List<AuthUser> recomdList = authUserMapper.queryRecomd();
        return recomdList;
    }

    public TailPage<AuthUser> queryPage(AuthUser queryEntity ,TailPage<AuthUser> page){
        Integer itemsTotalCount = authUserMapper.getTotalItemsCount(queryEntity);
        List<AuthUser> items = authUserMapper.queryPage(queryEntity,page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }



    public void update(AuthUser entity){
        authUserMapper.update(entity);
    }

    public void updateSelectivity(AuthUser entity){
        authUserMapper.updateSelectivity(entity);
    }

    public void delete(AuthUser entity){
        authUserMapper.delete(entity);
    }

    public void deleteLogic(AuthUser entity){
        authUserMapper.deleteLogic(entity);
    }
}
