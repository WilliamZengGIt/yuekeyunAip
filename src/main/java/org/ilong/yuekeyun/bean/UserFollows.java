package org.ilong.yuekeyun.bean;

/**
 * 用户关注
 *
 * @author long
 * @date 2020-11-25 15:30
 */
public class UserFollows extends BaseEntity{


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



}


