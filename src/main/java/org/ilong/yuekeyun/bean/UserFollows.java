package org.ilong.yuekeyun.bean;

/**
 * 用户关注
 *
 * @author long
 * @date 2020-11-25 15:30
 */
public class UserFollows extends BaseEntity{

    private  Long id;
    /**
     *用户id
     **/
    private Long userId;

    /**
     *关注的用户id
     **/
    private Long followId;

    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getFollowId(){
        return followId;
    }
    public void setFollowId(Long followId){
        this.followId = followId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserFollows{" +
                "id=" + id +
                ", userId=" + userId +
                ", followId=" + followId +
                '}';
    }
}


