package com.haipiao.articleservice.handler.factory;

import com.haipiao.articleservice.constants.RecommendationArticleTimeConstant;
import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.articleservice.dto.resp.RecommendationArticleResponse;
import com.haipiao.articleservice.enums.RecommendationArticleEnum;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.repository.ArticleRepository;
import com.haipiao.persist.repository.ArticleTopicRepository;
import com.haipiao.persist.utils.DateTimeUtil;
import com.haipiao.persist.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author wangjipeng
 */
@Component
public class CommonRecommendationArticle {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleTopicRepository articleTopicRepository;

    @Autowired
    private RecommendationByNearby recommendationByNearby;

    @Autowired
    private RecommendationByDiscover recommendationByDiscover;

    @Autowired
    private RecommendationByArticleRelated recommendationByArticleRelated;

    @Autowired
    private RecommendationByTopicRelated recommendationByTopicRelated;

    @Autowired
    private RecommendationByTopicRelatedLatest recommendationByTopicRelatedLatest;

    public List<RecommendationArticleResponse.Data.ArticleData> assemblerDataByContext(RecommendationArticleRequest request){
        Map<String, List<RecommendationArticleResponse.Data.ArticleData>> map = new HashMap<>(8);
        map.put(RecommendationArticleEnum.DISCOVER.getValue(), recommendationByDiscover.assemblerDate(request));
        map.put(RecommendationArticleEnum.NEARBY.getValue(), recommendationByNearby.assemblerDate(request));
        map.put(RecommendationArticleEnum.ARTICLE_RELATED.getValue(), recommendationByArticleRelated.assemblerDate(request));
        map.put(RecommendationArticleEnum.TOPIC_RELATED.getValue(), recommendationByTopicRelated.assemblerDate(request));
        map.put(RecommendationArticleEnum.TOPIC_RELATED_LATEST.getValue(), recommendationByTopicRelatedLatest.assemblerDate(request));
        return map.get(request.getContext());
    }

    public List<Article> getArticlesOfTopicCommonMethod(int days, int cursor, int limit){
        List<Integer> articleIds = articleTopicRepository.getArticlesOfHotTopic(DateTimeUtil.someHoursAgo(days), DateTimeUtil.dateToString(new Date()), cursor, limit);
        return getArticlesCommonMethod(articleIds);
    }

    public List<Article> getArticlesCommonMethod(List<Integer> articleIds){
        return StreamSupport.stream(articleRepository.findAllById(articleIds).spliterator(), false)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
