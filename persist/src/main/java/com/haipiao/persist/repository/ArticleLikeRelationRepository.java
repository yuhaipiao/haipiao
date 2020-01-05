package com.haipiao.persist.repository;

import com.haipiao.persist.entity.Article;
import com.haipiao.persist.entity.ArticleLikeRelation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wangjipeng
 */
public interface ArticleLikeRelationRepository extends CrudRepository<ArticleLikeRelation, Integer> {

    /**
     * 根据文章id、用户id查询是否有记录
     * @param articleId
     * @param likeId
     * @return
     */
    List<ArticleLikeRelation> findByArticleIdAndAndLikeId(int articleId, int likeId);

    /**
     * 根据文章id及点赞者id删除点赞记录
     * @param id
     * @param likeId
     */
    void deleteByArticleIdAndLikeId(int id, int likeId);
}
