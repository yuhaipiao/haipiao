package com.haipiao.userservice.handler.factory;

import com.haipiao.persist.entity.User;
import com.haipiao.userservice.req.RecommendationRequest;

import java.util.List;

/**
 * @author wangjipeng
 */
public interface Recommended {

    /**
     * Recommended users
     * @param user
     * @param request
     * @return
     */
    List<User> recommendedUsers(User user, RecommendationRequest request);
}
