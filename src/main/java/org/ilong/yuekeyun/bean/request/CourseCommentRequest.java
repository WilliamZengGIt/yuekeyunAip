package org.ilong.yuekeyun.bean.request;

import org.ilong.yuekeyun.bean.CourseComment;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-25 11:08
 */
public class CourseCommentRequest {
    //当前页数
    private Integer pagesize;
    //当前页
    private Integer currentPage;
    //排序  "pop" 最热 “last” 最新
    private String sort;
    private CourseComment courseComment;

    @Override
    public String toString() {
        return "CourseCommentRequest{" +
                "pagesize=" + pagesize +
                ", currentPage=" + currentPage +
                ", sort='" + sort + '\'' +
                ", courseComment=" + courseComment +
                '}';
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public CourseComment getCourseComment() {
        return courseComment;
    }

    public void setCourseComment(CourseComment courseComment) {
        this.courseComment = courseComment;
    }
}
