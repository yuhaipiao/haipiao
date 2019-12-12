package com.haipiao.persist.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author wangjipeng
 */
@Entity
@Table(name = "article_collect_relation")
public class ArticleCollectRelation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "collector_id")
    private int collectorId;

    @Column(name = "article_id")
    private int articleId;

    @Column(name = "collector_follower_count_approximate")
    private int collectorFollowerCountApproximate;

    public ArticleCollectRelation() {
    }

    public ArticleCollectRelation(int collectorId, int articleId, int collectorFollowerCountApproximate) {
        this.collectorId = collectorId;
        this.articleId = articleId;
        this.collectorFollowerCountApproximate = collectorFollowerCountApproximate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getCollectorFollowerCountApproximate() {
        return collectorFollowerCountApproximate;
    }

    public void setCollectorFollowerCountApproximate(int collectorFollowerCountApproximate) {
        this.collectorFollowerCountApproximate = collectorFollowerCountApproximate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleCollectRelation)) return false;
        ArticleCollectRelation that = (ArticleCollectRelation) o;
        return getId() == that.getId() &&
                getCollectorId() == that.getCollectorId() &&
                getArticleId() == that.getArticleId() &&
                getCollectorFollowerCountApproximate() == that.getCollectorFollowerCountApproximate();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCollectorId(), getArticleId(), getCollectorFollowerCountApproximate());
    }

    @Override
    public String toString() {
        return "ArticleCollectRelation{" +
                "id=" + id +
                ", collectorId=" + collectorId +
                ", articleId=" + articleId +
                ", collectorFollowerCountApproximate=" + collectorFollowerCountApproximate +
                '}';
    }
}
