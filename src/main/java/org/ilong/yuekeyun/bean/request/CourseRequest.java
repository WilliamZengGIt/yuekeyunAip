package org.ilong.yuekeyun.bean.request;

import org.ilong.yuekeyun.bean.Course;

import java.math.BigDecimal;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-17 16:00
 */
public class CourseRequest {
    //当前页数
    private Integer pagesize;
    //当前页
    private Integer currentPage;
    //排序  "pop" 最热 “last” 最新
    private String sort;
    private Course course;

    @Override
    public String toString() {
        return "CourseRequest{" +
                "pagesize=" + pagesize +
                ", currentPage=" + currentPage +
                ", sort='" + sort + '\'' +
                ", course=" + course +
                '}';
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
