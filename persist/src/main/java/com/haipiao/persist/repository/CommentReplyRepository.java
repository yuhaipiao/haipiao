package com.haipiao.persist.repository;

import com.haipiao.persist.entity.CommentReply;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentReplyRepository extends CrudRepository<CommentReply, Integer> {

    /**
     * 根据评论id获取所有回复数据
     * @param commentId
     * @return
     */
    List<CommentReply> findByCommentId(int commentId);
}
