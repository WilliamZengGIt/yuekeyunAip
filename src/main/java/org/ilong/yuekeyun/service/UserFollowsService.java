package org.ilong.yuekeyun.service;

import org.ilong.yuekeyun.bean.UserFollows;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.dto.UserFollowStudyRecord;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-30 11:36
 */
public interface UserFollowsService {
    /**
     *根据id获取
     **/
     UserFollows getById(Long id);

    /**
     *获取所有
     **/
     List<UserFollows> queryAll(UserFollows queryEntity);

    /**
     *分页获取
     **/
     TailPage<UserFollows> queryPage(UserFollows queryEntity , TailPage<UserFollows> page);

    /**
     *分页获取
     **/
     TailPage<UserFollowStudyRecord> queryUserFollowStudyRecordPage(UserFollowStudyRecord queryEntity , TailPage<UserFollowStudyRecord> page);

    /**
     *创建
     **/
     void createSelectivity(UserFollows entity);

    /**
     *根据id更新
     **/
     void update(UserFollows entity);

    /**
     *根据id 进行可选性更新
     **/
     void updateSelectivity(UserFollows entity);

    /**
     *物理删除
     **/
     void delete(UserFollows entity);

    /**
     *逻辑删除
     **/
     void deleteLogic(UserFollows entity);

}
