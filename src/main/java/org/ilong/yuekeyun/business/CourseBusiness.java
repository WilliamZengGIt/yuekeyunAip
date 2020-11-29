package org.ilong.yuekeyun.business;

import org.ilong.yuekeyun.bean.vo.CourseSectionVO;

import java.util.List;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-23 16:20
 */
public interface CourseBusiness {
    /**
     * 获取课程章节
     */
    List<CourseSectionVO> queryCourseSection(Long courseId);
}
