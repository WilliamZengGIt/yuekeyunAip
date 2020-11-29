package org.ilong.yuekeyun.service.impl;

import org.ilong.yuekeyun.bean.UserCourseSection;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.dto.UserCourseSectionDto;
import org.ilong.yuekeyun.mapper.UserCourseSectionMapper;
import org.ilong.yuekeyun.service.UserCourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-25 16:01
 */
@Service
public class UserCourseSectionServiceImpl implements UserCourseSectionService {
    @Autowired
    private UserCourseSectionMapper entityDao;

    @Override
    public UserCourseSection getById(Long id){
        return entityDao.getById(id);
    }
    @Override
    public List<UserCourseSection> queryAll(UserCourseSection queryEntity){
        return entityDao.queryAll(queryEntity);
    }
    @Override
    public UserCourseSection queryLatest(UserCourseSection queryEntity){
        return entityDao.queryLatest(queryEntity);
    }
    @Override
    public TailPage<UserCourseSectionDto> queryPage(UserCourseSection queryEntity , TailPage<UserCourseSectionDto> page){
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        List<UserCourseSectionDto> items = entityDao.queryPage(queryEntity,page);
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }
    @Override
    public void createSelectivity(UserCourseSection entity){
        entityDao.createSelectivity(entity);
    }
    @Override
    public void update(UserCourseSection entity){
        entityDao.update(entity);
    }
    @Override
    public void updateSelectivity(UserCourseSection entity){
        entityDao.updateSelectivity(entity);
    }
    @Override
    public void delete(UserCourseSection entity){
        entityDao.delete(entity);
    }
    @Override
    public void deleteLogic(UserCourseSection entity){
        entityDao.deleteLogic(entity);
    }
}
