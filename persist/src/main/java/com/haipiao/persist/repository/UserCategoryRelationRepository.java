package com.haipiao.persist.repository;

import com.haipiao.persist.entity.UserCategoryRelation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wangjipeng
 */
public interface UserCategoryRelationRepository extends CrudRepository<UserCategoryRelation, Integer> {

    /**
     * 根据用户id查询改用户所有分类id
     * @param id
     * @return
     */
    List<Integer> findByUserId(int id);
}
