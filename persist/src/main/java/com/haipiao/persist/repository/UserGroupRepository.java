package com.haipiao.persist.repository;

import com.haipiao.persist.entity.UserGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wangshun
 */
public interface UserGroupRepository extends CrudRepository<UserGroup, Integer> {
    /**
     * 根据创建者id与类型查询
     *
     * @param ownerId
     * @param type
     * @return
     */
    List<UserGroup> findUserGroupsByOwnerIdAndType(Integer ownerId, String type);

    /**
     * 根据类型查询
     *
     * @param type
     * @return
     */
    List<UserGroup> findUserGroupsByType(String type);

}
