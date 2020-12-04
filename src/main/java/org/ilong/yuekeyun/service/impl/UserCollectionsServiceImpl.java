package org.ilong.yuekeyun.service.impl;

import org.ilong.yuekeyun.bean.UserCollections;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.mapper.UserCollectionsMapper;
import org.ilong.yuekeyun.service.UserCollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-30 17:30
 */
@Service
public class UserCollectionsServiceImpl implements UserCollectionsService {
    @Autowired
    private UserCollectionsMapper entityDao;

    public UserCollections getById(Long id){
        return entityDao.getById(id);
    }

    public List<UserCollections> queryAll(UserCollections queryEntity){
        return entityDao.queryAll(queryEntity);
    }

    public TailPage<UserCollections> queryPage(UserCollections queryEntity ,TailPage<UserCollections> page){
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        List<UserCollections> items = entityDao.queryPage(queryEntity,page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    public void createSelectivity(UserCollections entity){
        entityDao.createSelectivity(entity);
    }

    public void update(UserCollections entity){
        entityDao.update(entity);
    }

    public void updateSelectivity(UserCollections entity){
        entityDao.updateSelectivity(entity);
    }

    public void delete(UserCollections entity){
        entityDao.delete(entity);
    }

    public void deleteLogic(UserCollections entity){
        entityDao.deleteLogic(entity);
    }
}
