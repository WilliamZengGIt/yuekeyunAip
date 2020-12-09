package org.ilong.yuekeyun.bean;

/**
 * 视频操作历史类
 *
 * @author long
 * @date 2020-12-06 16:40
 */
public class VideoHistory extends BaseEntity{
    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户手机号
     */
    private String userMobile;
    /**
     * 视频对应的课程id
     */
    private Long courseId;
    /**
     * 视频对应课程的章节id
     */
    private Long sectionId;
    /**
     * 视频地址
     */
    private String videoUrl;
    /**
     * 视频名字
     */
    private String videoName;
    /**
     * 视频时间
     */
    private String time;

    @Override
    public String toString() {
        return "VideoHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", userMobile='" + userMobile + '\'' +
                ", courseId=" + courseId +
                ", sectionId=" + sectionId +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoName='" + videoName + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
