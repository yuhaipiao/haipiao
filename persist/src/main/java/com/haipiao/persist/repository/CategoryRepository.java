package com.haipiao.persist.repository;

import com.haipiao.persist.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wangjipeng
 */
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    /**
     * 根据分类标签获取数据
     * @param type
     * @return
     */
    List<Category> findByType(String type);
}
