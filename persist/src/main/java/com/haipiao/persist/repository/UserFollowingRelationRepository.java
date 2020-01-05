package com.haipiao.persist.repository;

import com.haipiao.persist.entity.UserFollowingRelation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wangjipeng
 */
public interface UserFollowingRelationRepository extends CrudRepository<UserFollowingRelation, Integer> {

    /**
     * 获取此用户有多少粉丝
     * @param userId
     * @return long
     */
    int countByUserId(int userId);


    /**
     * 根据userId与followingUserId查询数量
     * @param userId
     * @param followingUserId
     * @return
     */
    int countByUserIdAndFollowingUserId(int userId, int followingUserId);

    /**
     * 获取所有关注此用户的用户Id
     * @param userId
     * @param beginNo
     * @param pageSize
     * @return
     */
    @Query(value = "select following_user_id from user_following_relation where user_id = ?1 order by id desc limit ?2, ?3", nativeQuery = true)
    List<Integer> findUserFollowingIdsByUserId(int userId, int beginNo, int pageSize);

    /**
     * 根据userId，groupIdBefore修改groupIdAfter
     * @param userId
     * @param groupIdBefore
     * @param groupIdAfter
     * @return
     */
    @Query(value = "update user_following_relation set group_id = ?3,update_ts = now() where user_id = ?1 and group_id = ?2",nativeQuery = true)
    int updateGroupIdByGroupIdAndUserId(int userId, int groupIdBefore, int groupIdAfter);
}
