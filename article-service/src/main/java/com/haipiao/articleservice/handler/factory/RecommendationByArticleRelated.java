package com.haipiao.articleservice.handler.factory;

import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.persist.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangjipeng
 */
@Component
public class RecommendationByArticleRelated extends AbstractRecommendationArticle{

    public static final Logger LOGGER = LoggerFactory.getLogger(RecommendationByArticleRelated.class);

    /**
     * my idea: 文章属于一个或多个话题，找出改文章所属最热话题的类似文章(感觉和话题推荐及话题热度推荐雷同)
     * @param request
     * @return
     */
    @Override
    public List<Article> getArticlesByContext(RecommendationArticleRequest request) {
        int articleId = request.getArticleId();

        return null;
    }
}
