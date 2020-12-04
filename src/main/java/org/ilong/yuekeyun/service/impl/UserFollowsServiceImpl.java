package org.ilong.yuekeyun.service.impl;

import org.ilong.yuekeyun.bean.UserFollows;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.dto.UserFollowStudyRecord;
import org.ilong.yuekeyun.mapper.UserFollowsMapper;
import org.ilong.yuekeyun.service.UserFollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-30 11:39
 */
@Service
public class UserFollowsServiceImpl implements UserFollowsService {
    @Autowired
    private UserFollowsMapper entityDao;

    public UserFollows getById(Long id){
        return entityDao.getById(id);
    }

    public List<UserFollows> queryAll(UserFollows queryEntity){
        return entityDao.queryAll(queryEntity);
    }

    public TailPage<UserFollows> queryPage(UserFollows queryEntity ,TailPage<UserFollows> page){
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        List<UserFollows> items = entityDao.queryPage(queryEntity,page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    public TailPage<UserFollowStudyRecord> queryUserFollowStudyRecordPage(UserFollowStudyRecord queryEntity ,TailPage<UserFollowStudyRecord> page){
        Integer itemsTotalCount = entityDao.getFollowStudyRecordCount(queryEntity);
        List<UserFollowStudyRecord> items = entityDao.queryFollowStudyRecord(queryEntity,page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    public void createSelectivity(UserFollows entity){
        entityDao.createSelectivity(entity);
    }

    public void update(UserFollows entity){
        entityDao.update(entity);
    }

    public void updateSelectivity(UserFollows entity){
        entityDao.updateSelectivity(entity);
    }

    public void delete(UserFollows entity){
        entityDao.delete(entity);
    }

    public void deleteLogic(UserFollows entity){
        entityDao.deleteLogic(entity);
    }

}
