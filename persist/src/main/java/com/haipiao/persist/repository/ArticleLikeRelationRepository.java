package com.haipiao.persist.repository;

import com.haipiao.persist.entity.Article;
import com.haipiao.persist.entity.ArticleLikeRelation;
import org.springframework.data.repository.CrudRepository;

/**
 * @author wangjipeng
 */
public interface ArticleLikeRelationRepository extends CrudRepository<ArticleLikeRelation, Integer> {


}
