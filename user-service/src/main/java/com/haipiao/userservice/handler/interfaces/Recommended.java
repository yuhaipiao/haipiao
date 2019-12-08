package com.haipiao.userservice.handler.interfaces;

import com.haipiao.persist.entity.User;

import java.util.List;

/**
 * @author wangjipeng
 */
public interface Recommended {

    /**
     * Recommended users
     * @param user
     * @return
     */
    List<User> recommendedUsers(User user);

    /**
     * Similar users
     */
}
