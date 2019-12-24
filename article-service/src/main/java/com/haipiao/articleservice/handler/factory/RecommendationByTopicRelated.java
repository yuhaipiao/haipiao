package com.haipiao.articleservice.handler.factory;

import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.entity.ArticleTopic;
import com.haipiao.persist.repository.ArticleRepository;
import com.haipiao.persist.repository.ArticleTopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author wangjipeng
 */
@Component
public class RecommendationByTopicRelated extends AbstractRecommendationArticle{

    public static final Logger LOGGER = LoggerFactory.getLogger(RecommendationByTopicRelated.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleTopicRepository articleTopicRepository;

    /**
     * my idea: 根据当前文章所属的所有话题，找出其中最热话题下点赞数最多的6篇文章
     * 根据话题文章推荐
     * @param request
     * @return
     */
    @Override
    public List<Article> getArticlesByContext(RecommendationArticleRequest request) {
        Optional<Article> optional = articleRepository.findById(request.getArticleId());
        if (optional.isEmpty()){
            return null;
        }
        List<ArticleTopic> articleTopics = articleTopicRepository.findAllByArticleId(request.getArticleId());
        return null;
    }
}
