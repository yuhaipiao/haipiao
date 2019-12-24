package com.haipiao.articleservice.handler.factory;

import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.persist.entity.Article;

import java.util.List;

/**
 * @author wangjipeng
 */
public interface RecommendationArticle {

    /**
     * 根据情景context推荐文章
     * @param request
     * @return
     */
    List<Article> getArticlesByContext(RecommendationArticleRequest request);
}
