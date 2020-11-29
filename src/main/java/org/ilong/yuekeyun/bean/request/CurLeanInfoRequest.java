package org.ilong.yuekeyun.bean.request;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-25 16:18
 */
public class CurLeanInfoRequest {
    /**
     *用户id
     **/
    private Long UserId;

    /**
     * 用户名
     */
    private String username;
    /**
     *课程id
     **/
    private Long CourseId;
    /**
     * 课程章节id
     */
    private Long CourseSectionId;

    @Override
    public String toString() {
        return "CurLeanInfoRequest{" +
                "UserId=" + UserId +
                ", username='" + username + '\'' +
                ", CourseId=" + CourseId +
                ", CourseSectionId=" + CourseSectionId +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getCourseId() {
        return CourseId;
    }

    public void setCourseId(Long courseId) {
        CourseId = courseId;
    }

    public Long getCourseSectionId() {
        return CourseSectionId;
    }

    public void setCourseSectionId(Long courseSectionId) {
        CourseSectionId = courseSectionId;
    }
}
