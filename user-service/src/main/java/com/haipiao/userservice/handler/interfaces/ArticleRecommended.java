package com.haipiao.userservice.handler.interfaces;

import com.haipiao.persist.entity.ArticleCollectRelation;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.ArticleCollectRelationRepository;
import com.haipiao.persist.repository.UserRepository;
import com.haipiao.userservice.req.RecommendationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author wangjipeng
 */
@Component
public class ArticleRecommended implements Recommended{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleCollectRelationRepository articleCollectRelationRepository;

    /**
     * 文章关注相似推荐 这些人也关注啦这篇文章
     * @param user
     * @return
     */
    @Override
    public List<User> recommendedUsers(User user, RecommendationRequest request) {
        int articleId = request.getArticleId();
        List<ArticleCollectRelation> articles = articleCollectRelationRepository.getListByArticleId(articleId);
        if (articles.size() == 0){
            return userRepository.getUserByFansCount();
        }
        Iterable<User> users = userRepository.findAllById(articles.stream().map(ArticleCollectRelation::getCollectorId).collect(Collectors.toList()));
        return StreamSupport.stream(users.spliterator(), false).collect(Collectors.toList());
    }
}
