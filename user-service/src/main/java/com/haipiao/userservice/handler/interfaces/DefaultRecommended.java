package com.haipiao.userservice.handler.interfaces;

import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.UserFollowingRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangjipeng
 */
@Component
public class DefaultRecommended implements Recommended{

    @Autowired
    private UserFollowingRelationRepository userFollowingRelationRepository;

    /**
     * 默认推荐 根据用户关注
     * @param user
     * @return
     */
    @Override
    public List<User> recommendedUsers(User user) {


        return null;
    }
}
