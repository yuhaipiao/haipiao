package com.haipiao.persist.entity;

import javax.persistence.*;
import java.util.Date;

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

    public UserCategoryRelation(Date createTs, Date updateTs, int userId, int categoryId) {
        super(createTs, updateTs);
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
    public String toString() {
        return "UserCategoryRelation{" +
                "id=" + id +
                "userId=" + userId +
                ", categoryId=" + categoryId + "}";
    }
}
