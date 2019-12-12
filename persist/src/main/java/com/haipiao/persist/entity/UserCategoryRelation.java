package com.haipiao.persist.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author wangjipeng
 */
@Entity
@Table(name = "user_category_relation")
public class UserCategoryRelation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "category_id")
    private int categoryId;

    public UserCategoryRelation() {
    }

    public UserCategoryRelation(int userId, int categoryId) {
        this.userId = userId;
        this.categoryId = categoryId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCategoryRelation)) return false;
        UserCategoryRelation that = (UserCategoryRelation) o;
        return getId() == that.getId() &&
                getUserId() == that.getUserId() &&
                getCategoryId() == that.getCategoryId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getCategoryId());
    }

    @Override
    public String toString() {
        return "UserCategoryRelation{" +
                "id=" + id +
                "userId=" + userId +
                ", categoryId=" + categoryId + "}";
    }
}
