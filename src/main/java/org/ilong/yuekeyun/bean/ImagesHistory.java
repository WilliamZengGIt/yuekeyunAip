package org.ilong.yuekeyun.bean;

/**
 * 图片操作历史类
 *
 * @author long
 * @date 2020-12-04 11:22
 */
public class ImagesHistory extends BaseEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户头像
     */
    private String header;
    /**
     * 用户手机号（唯一）
     */
    private String userMobile;

    /**
     * 历史图片的类型
     *
     * TouXiang(0),//"头像路径" ; ;
     *
     * LunBoTu(1),//"轮播图存储路径",
     *
     * KeChengTu(2),//"课程图存储路径"
     *
     *KeChengVideo(3);//课程视频存储路径
     */
    private Integer type;
    public ImagesHistory(){

    }
    public ImagesHistory(Long id, Long userId, String header, String userMobile, Integer type) {
        this.id = id;
        this.userId = userId;
        this.header = header;
        this.userMobile = userMobile;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ImagesHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", header='" + header + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", type=" + type +
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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
