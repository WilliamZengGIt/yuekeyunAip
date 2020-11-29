package org.ilong.yuekeyun.mapper;

import org.ilong.yuekeyun.bean.CourseComment;
import org.ilong.yuekeyun.bean.common.page.TailPage;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-25 10:39
 */
public interface CourseCommentMapper {
    /**
     *根据id获取
     **/
     CourseComment getById(Long id);

    /**
     *获取所有
     **/
     List<CourseComment> queryAll(CourseComment queryEntity);

    /**
     *获取总数量
     **/
     Integer getTotalItemsCount(CourseComment queryEntity);

    /**
     *分页获取
     **/
     List<CourseComment> queryPage(CourseComment queryEntity , TailPage<CourseComment> page);


    /**
     *获取总数量
     **/
     Integer getMyQAItemsCount(CourseComment queryEntity);

    /**
     *分页获取
     **/
     List<CourseComment> queryMyQAItemsPage(CourseComment queryEntity , TailPage<CourseComment> page);

    /**
     *创建新记录
     **/
     void create(CourseComment entity);

    /**
     * 创建新记录
     */
     void createSelectivity(CourseComment entity);

    /**
     *根据id更新
     **/
     void update(CourseComment entity);

    /**
     *根据id选择性更新自动
     **/
     void updateSelectivity(CourseComment entity);

    /**
     *物理删除
     **/
     void delete(CourseComment entity);

    /**
     *逻辑删除
     **/
     void deleteLogic(CourseComment entity);
}
