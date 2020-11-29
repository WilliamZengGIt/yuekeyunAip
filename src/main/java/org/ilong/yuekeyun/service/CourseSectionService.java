package org.ilong.yuekeyun.service;

import org.ilong.yuekeyun.bean.CourseSection;
import org.ilong.yuekeyun.bean.common.page.TailPage;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-23 16:13
 */
public interface CourseSectionService {
    /**
     *根据id获取
     **/
     CourseSection getById(Long id);

    /**
     *获取所有
     **/
     List<CourseSection> queryAll(CourseSection queryEntity);

    /**
     * 获取课程章最大的sort
     */
     Integer getMaxSort(Long courseId);

    /**
     *分页获取
     **/
     TailPage<CourseSection> queryPage(CourseSection queryEntity , TailPage<CourseSection> page);

    /**
     *创建
     **/
     void createSelectivity(CourseSection entity);

    /**
     *批量创建
     **/
     void createList(List<CourseSection> entityList);

    /**
     *根据id更新
     **/
     void update(CourseSection entity);

    /**
     *根据id 进行可选性更新
     **/
     void updateSelectivity(CourseSection entity);

    /**
     *物理删除
     **/
     void delete(CourseSection entity);

    /**
     *逻辑删除
     **/
     void deleteLogic(CourseSection entity);

    /**
     * 比当前sort大的，正序排序的第一个
     * @param curCourseSection
     * @return
     */
     CourseSection getSortSectionMax(CourseSection curCourseSection);

    /**
     * 比当前sort小的，倒序排序的第一个
     * @param curCourseSection
     * @return
     */
     CourseSection getSortSectionMin(CourseSection curCourseSection);
}
