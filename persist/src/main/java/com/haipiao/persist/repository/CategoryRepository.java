package com.haipiao.persist.repository;

import com.haipiao.persist.entity.Category;
import com.haipiao.persist.entity.CommentReply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author wangjipeng
 */
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
