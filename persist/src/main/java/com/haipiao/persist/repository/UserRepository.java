package com.haipiao.persist.repository;

import com.haipiao.persist.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User getUserByPhone(String phone);

    /**
     * 获取粉丝最多的前100位
     * @return
     */
    List<User> getUserByFansCount();

}