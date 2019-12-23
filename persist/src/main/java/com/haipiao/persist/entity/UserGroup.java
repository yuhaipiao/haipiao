package com.haipiao.persist.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: wangshun
 */
@Entity
@Table(name = "user_group")
public class UserGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroup userGroup = (UserGroup) o;
        return groupId == userGroup.groupId &&
                ownerId == userGroup.ownerId &&
                Objects.equals(name, userGroup.name) &&
                Objects.equals(type, userGroup.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, ownerId, name, type);
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "groupId=" + groupId +
                ", ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
