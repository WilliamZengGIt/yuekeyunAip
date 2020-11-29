package org.ilong.yuekeyun.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ilong.yuekeyun.bean.AuthUser;

/**
 * TOOD
 *
 * @author long
 * @date 2020-10-10 15:19
 */
public interface AuthUserMapper {
    AuthUser loadUserByUsername(String mobile);
}
