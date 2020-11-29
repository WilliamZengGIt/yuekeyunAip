package org.ilong.yuekeyun.mapper;

import org.ilong.yuekeyun.bean.UserCourseSection;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.dto.UserCourseSectionDto;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-25 16:03
 */
public interface UserCourseSectionMapper {
    /**
     *根据id获取
     **/
     UserCourseSection getById(Long id);

    /**
     *获取所有
     **/
     List<UserCourseSection> queryAll(UserCourseSection queryEntity);

    /**
     * 获取最新的学习记录
     */
     UserCourseSection queryLatest(UserCourseSection queryEntity);

    /**
     *获取总数量
     **/
     Integer getTotalItemsCount(UserCourseSection queryEntity);

    /**
     *分页获取
     **/
     List<UserCourseSectionDto> queryPage(UserCourseSection queryEntity , TailPage<UserCourseSectionDto> page);

    /**
     *创建新记录
     **/
     void createSelectivity(UserCourseSection entity);

    /**
     *根据id更新
     **/
     void update(UserCourseSection entity);

    /**
     *根据id选择性更新自动
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
