package com.haipiao.articleservice.handler.factory;

import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.articleservice.enums.RecommendationArticleEnum;
import com.haipiao.persist.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangjipeng
 */
@Component
public class RecommendationByNearby extends AbstractRecommendationArticle{

    public static final Logger LOGGER = LoggerFactory.getLogger(RecommendationByNearby.class);

    /**
     * my idea: 根据当前经纬度、范围(30，000m)、时间(暂定48小时)获取点赞数最多的前6篇文章
     * 附近文章推荐
     * @param request
     * @return
     */
    @Override
    public List<Article> getArticlesByContext(RecommendationArticleRequest request) {
        LOGGER.info("当前场景为{}", RecommendationArticleEnum.getNameByValue(request.getContext()));
        // TODO location待增加
        float longitude = request.getLongitude();
        float latitude = request.getLatitude();

        return null;
    }
}
