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
public class RecommendationByArticleRelated extends AbstractRecommendationArticle{

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleTopicRepository articleTopicRepository;

    @Autowired
    private CommonRecommendationArticle commonRecommendationArticle;

    public static final Logger LOGGER = LoggerFactory.getLogger(RecommendationByArticleRelated.class);

    /**
     * my idea: 文章属于一个或多个话题，找出该文章所属最热话题的类似文章(感觉和话题推荐及话题热度推荐雷同)
     * @param request
     * @return
     */
    @Override
    public List<Article> getArticlesByContext(RecommendationArticleRequest request) {
        LOGGER.info("当前场景为{}", RecommendationArticleEnum.getNameByValue(request.getContext()));
        Optional<Article> optional = articleRepository.findById(request.getArticleId());
        if (optional.isEmpty()){
            LOGGER.info("{}此Id文章不存在！", request.getArticleId());
            return null;
        }
        List<ArticleTopic> articleTopics = articleTopicRepository.findAllByArticleId(request.getArticleId());
        if (articleTopics == null || articleTopics.size() <= 0){
            LOGGER.info("{}此Id文章没有所属话题！", request.getArticleId());
            return null;
        }
        List<Integer> topicIds = articleTopics.stream().map(ArticleTopic::getTopicId).collect(Collectors.toList());
        List<Integer> articleIds = articleTopicRepository.findByTopicIdIn(topicIds,
                DateTimeUtil.someHoursAgo(RecommendationArticleTimeConstant.THREE_DAYS),
                DateTimeUtil.dateToString(new Date()),
                PageUtil.cursor(request.getCursor()),
                PageUtil.limit(request.getLimit()));
        return commonRecommendationArticle.getArticlesCommonMethod(articleIds);
    }
}
