package org.ilong.yuekeyun.service;

import org.ilong.yuekeyun.bean.Course;
import org.ilong.yuekeyun.bean.common.page.PageBean;
import org.ilong.yuekeyun.bean.common.page.TailPage;
import org.ilong.yuekeyun.bean.dto.CourseDto;
import org.ilong.yuekeyun.bean.dto.CourseQueryDto;

import java.util.List;


/**
 * 课程服务层
 *
 * @author long
 * @date 2020-10-14 15:38
 */
public interface CourseService {
    /**
     *根据id获取
     **/
     Course getById(Long id);

    /**
     *获取所有
     **/
     List<Course> queryList(CourseQueryDto queryEntity);
    /**
     * 按条件获取指定数量的课程 按权重 weight排序
     */
     List<Course> queryCourses(CourseDto queryEntity);
    /**
     *分页获取
     **/
     TailPage<Course> queryPage(Course queryEntity ,TailPage<Course> page);

    /**
     *创建
     **/
     void createSelectivity(Course entity);

    /**
     *根据id 进行可选性更新
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
