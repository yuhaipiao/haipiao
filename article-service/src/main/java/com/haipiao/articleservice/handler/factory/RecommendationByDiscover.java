package com.haipiao.articleservice.handler.factory;

import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.repository.ArticleRepository;
import com.haipiao.persist.utils.DateTimeUtil;
import com.haipiao.persist.utils.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author wangjipeng
 */
@Component
public class RecommendationByDiscover extends AbstractRecommendationArticle{

    public static final Logger LOGGER = LoggerFactory.getLogger(RecommendationByDiscover.class);

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * my idea：根据当前时间戳，获取和当前时间相差12小时且点赞数最多的前6条
     * 首页发现文章推荐
     * @param request
     * @return
     */
    @Override
    public List<Article> getArticlesByContext(RecommendationArticleRequest request) {
        LOGGER.info("当前场景为{}", request.getContext());
        return articleRepository.findByCreateTsBetween(DateTimeUtil.twelveHoursAgo(), DateTimeUtil.dateToString(new Date()), PageUtil.cursor(request.getCursor()), PageUtil.limit(request.getLimit()));
    }
}
