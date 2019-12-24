package com.haipiao.articleservice.handler.factory;

import com.haipiao.articleservice.constants.RecommendationArticleTimeConstant;
import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.articleservice.enums.RecommendationArticleEnum;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.repository.ArticleTopicRepository;
import com.haipiao.persist.utils.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangjipeng
 */
@Component
public class RecommendationByTopicRelatedLatest extends AbstractRecommendationArticle{

    @Autowired
    private CommonRecommendationArticle commonRecommendationArticle;

    public static final Logger LOGGER = LoggerFactory.getLogger(RecommendationByTopicRelatedLatest.class);

    /**
     *
     * 根据最新话题推荐文章
     * @param request
     * @return
     */
    @Override
    public List<Article> getArticlesByContext(RecommendationArticleRequest request) {
        LOGGER.info("当前场景为{}", RecommendationArticleEnum.getNameByValue(request.getContext()));
        return commonRecommendationArticle.getArticlesOfTopicCommonMethod(RecommendationArticleTimeConstant.ONE_DAY, PageUtil.cursor(request.getCursor()), PageUtil.limit(request.getLimit()));
    }
}
