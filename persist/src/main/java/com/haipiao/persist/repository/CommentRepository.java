package com.haipiao.persist.repository;

import com.haipiao.persist.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    /**
     * 通过文章id分页查询该文章评论
     * @param id
     * @param limit
     * @param cursor
     * @return
     */
    @Query("")
    List<Comment> findByArticleIdAndLimit(int id, int limit, String cursor);

    /**
     * 根据文章id获取问上评论总数量
     * @param id
     * @return
     */
    long findAllByArticleId(int id);
}
