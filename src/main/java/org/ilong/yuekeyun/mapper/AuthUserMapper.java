package org.ilong.yuekeyun.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ilong.yuekeyun.bean.AuthUser;
import org.ilong.yuekeyun.bean.common.page.TailPage;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-10-10 15:19
 */
public interface AuthUserMapper {
    AuthUser loadUserByUsername(String mobile);

    /**
     *根据id获取
     **/
     AuthUser getById(Long id);

    /**
     * 根据username获取
     */
     AuthUser getByUsername(String username);

    /**
     * 根据username 和 password获取
     * @param authUser
     * @return
     */
     AuthUser getByUsernameAndPassword(AuthUser authUser);

    /**
     *获取首页推荐5个讲师
     **/
     List<AuthUser> queryRecomd();

    /**
     *获取总数量
     **/
     Integer getTotalItemsCount(AuthUser queryEntity);

    /**
     *分页获取
     **/
     List<AuthUser> queryPage(AuthUser queryEntity , TailPage<AuthUser> page);

    /**
     *创建新记录
     **/
     void createSelectivity(AuthUser entity);

    /**
     *根据id更新
     **/
     void update(AuthUser entity);

    /**
     *根据id选择性更新自动
     **/
     void updateSelectivity(AuthUser entity);

    /**
     *物理删除
     **/
     void delete(AuthUser entity);

    /**
     *逻辑删除
     **/
     void deleteLogic(AuthUser entity);

}
