package com.haipiao.articleservice.service;

import com.haipiao.persist.entity.ArticleLikeRelation;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.ArticleLikeRelationRepository;
import com.haipiao.persist.repository.UserRepository;
import com.haipiao.persist.vo.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: wangshun
 */
@Service
public class GetArticleCommonService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleLikeRelationRepository articleLikeRelationRepository;

    /**
     * 根据userId获取Author
     * @param authorId
     * @return
     */
    public Author assemblerAuthor(int authorId) {
        Optional<User> optionalUser = userRepository.findById(authorId);
        User user = optionalUser.isEmpty() ? null : optionalUser.get();
        return user == null ? null : new Author(user.getUserId(), user.getUserName(), user.getProfileImgUrl());
    }

    /**
     * 根据userId与articleId查询是否点赞
     * @param userId
     * @param articleId
     * @return
     */
    public boolean checkIsLike(int userId, int articleId) {
        List<ArticleLikeRelation> likeRelations = articleLikeRelationRepository.findByArticleIdAndAndLikeId(articleId, userId);
        return likeRelations.size() > 0;
    }
}
