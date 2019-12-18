package com.haipiao.persist.repository;

import com.haipiao.persist.entity.ArticleCollectRelation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wangjipeng
 */
public interface ArticleCollectRelationRepository extends CrudRepository<ArticleCollectRelation, Integer> {

    /**
     * 通过文章id获取数据
     * @param articleId
     * @return
     */
    List<ArticleCollectRelation> getArticleCollectRelationsByArticleId(int articleId);
}
