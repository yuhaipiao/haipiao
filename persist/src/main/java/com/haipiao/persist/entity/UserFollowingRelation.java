package com.haipiao.persist.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author wangjipeng
 */
@Entity
@Table(name = "user_following_relation")
public class UserFollowingRelation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "following_user_id")
    private int followingUserId;

    @Column(name = "group_id")
    private int groupId;

    public UserFollowingRelation() {
    }

    public UserFollowingRelation(int userId, int followingUserId, int groupId) {
        this.userId = userId;
        this.followingUserId = followingUserId;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFollowingRelation)) return false;
        UserFollowingRelation that = (UserFollowingRelation) o;
        return getId() == that.getId() &&
                getUserId() == that.getUserId() &&
                getFollowingUserId() == that.getFollowingUserId() &&
                getGroupId() == that.getGroupId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getFollowingUserId(), getGroupId());
    }

    @Override
    public String toString() {
        return "UserFollowingRelation{" +
                "id=" + id +
                ", userId=" + userId +
                ", followingUserId=" + followingUserId +
                ", groupId=" + groupId +
                '}';
    }
}
