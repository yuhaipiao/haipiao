package com.haipiao.userservice.handler.interfaces;

import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.ArticleCollectRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangjipeng
 */
@Component
public class ArticleRecommended implements Recommended{

    @Autowired
    private ArticleCollectRelationRepository articleCollectRelationRepository;

    /**
     * 文章关注相似推荐
     * @param user
     * @return
     */
    @Override
    public List<User> recommendedUsers(User user) {

        return null;
    }
}
