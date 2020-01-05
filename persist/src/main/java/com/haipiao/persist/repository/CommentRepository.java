package com.haipiao.persist.repository;

import com.haipiao.persist.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    /**
     * 通过文章id分页查询该文章评论
     * @param id
     * @param beingNo
     * @param pageSize
     * @return
     */
    @Query(value = "select * from comment where article_id = ?1 order by comment_id limit ?2, ?3", nativeQuery = true)
    List<Comment> findByArticleIdAndLimit(int id, int beingNo, int pageSize);

    /**
     * 根据文章id获取问上评论总数量
     * @param id
     * @return
     */
    long findAllByArticleId(int id);
}
