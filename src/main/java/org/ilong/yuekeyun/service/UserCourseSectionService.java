package org.ilong.yuekeyun.service;

import org.ilong.yuekeyun.bean.UserCourseSection;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.dto.UserCourseSectionDto;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-25 16:00
 */
public interface UserCourseSectionService {
    /**
     *根据id获取
     **/
     UserCourseSection getById(Long id);

    /**
     *获取所有
     **/
     List<UserCourseSection> queryAll(UserCourseSection queryEntity);

    /**
     * 获取最新的
     */
     UserCourseSection queryLatest(UserCourseSection queryEntity);

    /**
     *分页获取
     **/
     TailPage<UserCourseSectionDto> queryPage(UserCourseSection queryEntity , TailPage<UserCourseSectionDto> page);

    /**
     *创建
     **/
     void createSelectivity(UserCourseSection entity);

    /**
     *根据id更新
     **/
     void update(UserCourseSection entity);

    /**
     *根据id 进行可选性更新
     **/
     void updateSelectivity(UserCourseSection entity);

    /**
     *物理删除
     **/
     void delete(UserCourseSection entity);

    /**
     *逻辑删除
     **/
     void deleteLogic(UserCourseSection entity);

}
