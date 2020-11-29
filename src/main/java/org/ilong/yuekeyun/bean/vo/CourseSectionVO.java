package org.ilong.yuekeyun.bean.vo;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-23 15:58
 */

import org.ilong.yuekeyun.bean.CourseSection;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程章节
 */
public class CourseSectionVO extends CourseSection{


    //小节
    private List<CourseSection> sections = new ArrayList<CourseSection>();


    public List<CourseSection> getSections() {
        return sections;
    }

    public void setSections(List<CourseSection> sections) {
        this.sections = sections;
    }

}
