package org.ilong.yuekeyun.mapper;

import org.ilong.yuekeyun.bean.CourseSection;
import org.ilong.yuekeyun.bean.common.page.TailPage;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-23 16:02
 */
public interface CourseSectionMapper {
    /**
     *根据id获取
     **/
     CourseSection getById(Long id);

    /**
     *获取所有
     **/
     List<CourseSection> queryAll(CourseSection queryEntity);

    /**
     *
     */
     Integer getMaxSort(Long courseId);

    /**
     *获取总数量
     **/
     Integer getTotalItemsCount(CourseSection queryEntity);

    /**
     *分页获取
     **/
     List<CourseSection> queryPage(CourseSection queryEntity , TailPage<CourseSection> page);

    /**
     *创建新记录
     **/
     void createSelectivity(CourseSection entity);

    /**
     * 批量创建
     */
     void createList(List<CourseSection> entityList);

    /**
     *根据id更新
     **/
     void update(CourseSection entity);

    /**
     *根据id选择性更新自动
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
     *物理删除课程对应的章节
     **/
     void deleteByCourseId(CourseSection entity);

    /**
     *逻辑删除课程对应的章节
     **/
     void deleteLogicByCourseId(CourseSection entity);


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
