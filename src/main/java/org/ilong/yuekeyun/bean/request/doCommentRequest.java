package org.ilong.yuekeyun.bean.request;

import org.ilong.yuekeyun.bean.CourseComment;

/**
 * 评论前端请求实体类
 *
 * @author long
 * @date 2020-12-02 15:50
 */
public class doCommentRequest {
    //课程评论实体类
    private CourseComment courseComment;
    //前端验证码字符串
    private String indeityCode;
    //验证码唯一认证码
    private String cToken;

    @Override
    public String toString() {
        return "doCommentRequest{" +
                "courseComment=" + courseComment +
                ", indeityCode='" + indeityCode + '\'' +
                ", cToken='" + cToken + '\'' +
                '}';
    }

    public CourseComment getCourseComment() {
        return courseComment;
    }

    public void setCourseComment(CourseComment courseComment) {
        this.courseComment = courseComment;
    }

    public String getIndeityCode() {
        return indeityCode;
    }

    public void setIndeityCode(String indeityCode) {
        this.indeityCode = indeityCode;
    }

    public String getcToken() {
        return cToken;
    }

    public void setcToken(String cToken) {
        this.cToken = cToken;
    }
}
