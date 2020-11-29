package org.ilong.yuekeyun.bean;

/**
 * 用户学习章节
 *
 * @author long
 * @date 2020-11-25 15:25
 */
public class UserCourseSection extends BaseEntity{


    private Long id;

    @Override
    public String toString() {
        return "UserCourseSection{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", sectionId=" + sectionId +
                ", status=" + status +
                ", rate=" + rate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     *用户id
     **/
    private Long userId;

    /**
     *课程id
     **/
    private Long courseId;

    /**
     *章节id
     **/
    private Long sectionId;

    /**
     *状态：0-学习中；1-学习结束
     **/
    private Integer status;

    /**
     * 进度
     */
    private Integer rate;

    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getCourseId(){
        return courseId;
    }
    public void setCourseId(Long courseId){
        this.courseId = courseId;
    }

    public Long getSectionId(){
        return sectionId;
    }
    public void setSectionId(Long sectionId){
        this.sectionId = sectionId;
    }

    public Integer getStatus(){
        return status;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    public Integer getRate() {
        return rate;
    }
    public void setRate(Integer rate) {
        this.rate = rate;
    }

}


