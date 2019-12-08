package com.haipiao.persist.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wangjipeng
 */
@Entity
@Table(name = "user_following_relation")
public class UserFollowingRelation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "following_user_id")
    private int followingUserId;

    public UserFollowingRelation() {
    }

    public UserFollowingRelation(Date createTs, Date updateTs, int userId, int followingUserId) {
        super(createTs, updateTs);
        this.userId = userId;
        this.followingUserId = followingUserId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(int followingUserId) {
        this.followingUserId = followingUserId;
    }
}
