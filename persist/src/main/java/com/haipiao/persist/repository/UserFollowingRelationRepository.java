package com.haipiao.persist.repository;

import com.haipiao.persist.entity.UserFollowingRelation;
import org.springframework.data.repository.CrudRepository;

/**
 * @author wangjipeng
 */
public interface UserFollowingRelationRepository extends CrudRepository<UserFollowingRelation, Integer> {

    /**
     * 获取此用户有多少粉丝
     * @param userId
     * @return long
     */
    long findByUserId(int userId);
}
