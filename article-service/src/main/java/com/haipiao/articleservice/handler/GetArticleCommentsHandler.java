package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.req.GetArticleCommentsRequest;
import com.haipiao.articleservice.dto.resp.GetArticleCommentsResponse;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.constants.CommentLimitConstant;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.entity.Comment;
import com.haipiao.persist.entity.CommentReply;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.ArticleRepository;
import com.haipiao.persist.repository.CommentReplyRepository;
import com.haipiao.persist.repository.CommentRepository;
import com.haipiao.persist.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wangjipeng
 */
@Component
public class GetArticleCommentsHandler extends AbstractHandler<GetArticleCommentsRequest, GetArticleCommentsResponse> {

    public static final Logger LOG = LoggerFactory.getLogger(GetArticleCommentsHandler.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentReplyRepository commentReplyRepository;

    protected GetArticleCommentsHandler(SessionService sessionService, CommentRepository commentRepository,
                                        CommentReplyRepository commentReplyRepository,
                                        ArticleRepository articleRepository, UserRepository userRepository) {
        super(GetArticleCommentsResponse.class, sessionService);
        this.commentRepository = commentRepository;
        this.commentReplyRepository = commentReplyRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GetArticleCommentsResponse execute(GetArticleCommentsRequest request) throws AppException {
        GetArticleCommentsResponse response = new GetArticleCommentsResponse(StatusCode.SUCCESS);
        Optional<Article> article = articleRepository.findById(request.getId());
        if (article.isEmpty()){
            String errorMessage = String.format("Id为%s的文章不存在!", request.getId());
            LOG.info(errorMessage);
            response = new GetArticleCommentsResponse(StatusCode.BAD_REQUEST);
            response.setErrorMessage(errorMessage);
            return response;
        }

        // TODO 分页查询wangshun  cursor上次返回位置 具体概念...
        int limit = request.getLimit() == 0 ? CommentLimitConstant.LIMIT : request.getLimit();
        List<Comment> commentList = commentRepository.findByArticleIdAndLimit(request.getId(), limit, request.getCursor());
        List<GetArticleCommentsResponse.Data.CommentResponse> comments = commentList.stream().map(this::assemblerComment).collect(Collectors.toList());
        long totalCount = commentRepository.findAllByArticleId(request.getId());
        boolean moreToFollow = comments.size() >= totalCount;
        response.setData(new GetArticleCommentsResponse.Data(comments, (int) totalCount, "", moreToFollow));
        return response;
    }

    /**
     * 评论信息
     * @param comment
     * @return
     */
    private GetArticleCommentsResponse.Data.CommentResponse assemblerComment(Comment comment){
        List<GetArticleCommentsResponse.Data.CommentResponse.Replie> replies = assemblerReplies(comment.getCommentId());
        int repliesCount = replies.size();
        return new GetArticleCommentsResponse.Data.CommentResponse(comment.getCommentId(), comment.getTextBody(),
                comment.getCreateTs().getTime(), comment.getLikes(), assemblerCommenter(comment.getCommentId()),
                repliesCount, replies);
    }

    /**
     * 评论者
     * @param commentId
     * @return
     */
    private GetArticleCommentsResponse.Data.CommentResponse.Commenter assemblerCommenter(int commentId){
        Optional<User> userOptional = userRepository.findById(commentId);
        User user = userOptional.get();
        return new GetArticleCommentsResponse.Data.CommentResponse.Commenter(commentId, user.getProfileImgUrl(), user.getUserName());
    }

    /**
     * 回复信息
     * @param commentId
     * @return
     */
    private List<GetArticleCommentsResponse.Data.CommentResponse.Replie> assemblerReplies(int commentId){
        List<CommentReply> commentReplyList = commentReplyRepository.findByCommentId(commentId);
        return commentReplyList.stream()
                .filter(Objects::nonNull)
                .map(c -> new GetArticleCommentsResponse.Data.CommentResponse.Replie(assemblerReplier(c.getReplierId()), c.getTextBody()))
                .collect(Collectors.toList());
    }

    /**
     * 回复者
     * @param replierId
     * @return
     */
    private GetArticleCommentsResponse.Data.CommentResponse.Replie.Replier assemblerReplier(int replierId){
        Optional<User> userOptional = userRepository.findById(replierId);
        User user = userOptional.get();
        return new GetArticleCommentsResponse.Data.CommentResponse.Replie.Replier(user.getUserId(), user.getUserName());
    }
}
