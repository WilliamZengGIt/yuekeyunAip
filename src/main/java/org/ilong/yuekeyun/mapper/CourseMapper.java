package org.ilong.yuekeyun.mapper;

import org.ilong.yuekeyun.bean.Course;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.dto.CourseDto;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-10-14 15:01
 */
public interface CourseMapper {
    /**
     *根据id获取
     **/
     Course getById(Long id);



    /**
     *根据条件获取所有，
     *queryEntity：查询条件；
     **/
  /*  public List<Course> queryList(CourseQueryDto queryEntity);*/

    /**
     *获取总数量
     **/
     Integer getTotalItemsCount(Course queryEntity);

    /**
     *分页获取 按update_time DESC排序
     **/
     List<Course> queryPage(Course queryEntity , TailPage<Course> page);

    /**
     * 按条件获取指定数量的课程 按权重 weight排序
     */
     List<Course> queryCourses(CourseDto queryEntity);
    /**
     *创建新记录
     **/
     void create(Course entity);
     void createSelectivity(Course entity);

    /**
     *根据id更新
     **/
     void update(Course entity);

    /**
     *根据id选择性更新自动
     **/
     void updateSelectivity(Course entity);

    /**
     *物理删除
     **/
     void delete(Course entity);

    /**
     *逻辑删除
     **/
     void deleteLogic(Course entity);
}
