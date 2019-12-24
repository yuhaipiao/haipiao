package com.haipiao.articleservice.handler.factory;

import com.haipiao.articleservice.constants.RecommendationArticleTimeConstant;
import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.articleservice.enums.RecommendationArticleEnum;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.entity.ArticleTopic;
import com.haipiao.persist.repository.ArticleRepository;
import com.haipiao.persist.repository.ArticleTopicRepository;
import com.haipiao.persist.utils.DateTimeUtil;
import com.haipiao.persist.utils.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author wangjipeng
 */
@Component
public class RecommendationByTopicRelated extends AbstractRecommendationArticle{

    public static final Logger LOGGER = LoggerFactory.getLogger(RecommendationByTopicRelated.class);

    @Autowired
    private CommonRecommendationArticle commonRecommendationArticle;

    /**
     * my idea: 找出72小时内文章引用话题最多且点赞数最多的文章
     * 根据话题文章推荐
     * @param request
     * @return
     */
    @Override
    public List<Article> getArticlesByContext(RecommendationArticleRequest request) {
        LOGGER.info("当前场景为{}", RecommendationArticleEnum.getNameByValue(request.getContext()));
        return commonRecommendationArticle.getArticlesOfTopicCommonMethod(RecommendationArticleTimeConstant.THREE_DAYS, PageUtil.cursor(request.getCursor()), PageUtil.limit(request.getLimit()));
    }
}
