package com.haipiao.articleservice.handler.factory;

import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.articleservice.dto.resp.RecommendationArticleResponse;
import com.haipiao.articleservice.enums.RecommendationArticleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjipeng
 */
@Component
public class CommonRecommendationArticle {

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
}
