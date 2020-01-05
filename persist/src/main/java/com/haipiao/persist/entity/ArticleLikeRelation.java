package com.haipiao.persist.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author wangjipeng
 */
@Entity
@Table(name = "article_like_relation")
public class ArticleLikeRelation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "like_id")
    private int likeId;

    @Column(name = "article_id")
    private int articleId;

    @Column(name = "liker_follower_count_approximate")
    private int likerFollowerCountApproximate;

    public ArticleLikeRelation() {
    }

    public ArticleLikeRelation(int likeId, int articleId, int likerFollowerCountApproximate) {
        this.likeId = likeId;
        this.articleId = articleId;
        this.likerFollowerCountApproximate = likerFollowerCountApproximate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getLikerFollowerCountApproximate() {
        return likerFollowerCountApproximate;
    }

    public void setLikerFollowerCountApproximate(int likerFollowerCountApproximate) {
        this.likerFollowerCountApproximate = likerFollowerCountApproximate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleLikeRelation)) return false;
        ArticleLikeRelation that = (ArticleLikeRelation) o;
        return getLikeId() == that.getLikeId() &&
                getArticleId() == that.getArticleId() &&
                getLikerFollowerCountApproximate() == that.getLikerFollowerCountApproximate() &&
                Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLikeId(), getArticleId(), getLikerFollowerCountApproximate());
    }

    @Override
    public String toString() {
        return "ArticleLikeRelation{" +
                "id=" + id +
                ", likeId=" + likeId +
                ", articleId=" + articleId +
                ", likerFollowerCountApproximate=" + likerFollowerCountApproximate +
                '}';
    }
}
