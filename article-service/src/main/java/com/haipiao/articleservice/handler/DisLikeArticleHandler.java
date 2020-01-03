package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.req.DisLikeArticleRequest;
import com.haipiao.articleservice.dto.resp.DisLikeArticleResponse;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.ArticleDislikeRelation;
import com.haipiao.persist.repository.ArticleDislikeRelationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjipeng
 */
@Component
public class DisLikeArticleHandler extends AbstractHandler<DisLikeArticleRequest, DisLikeArticleResponse> {

    public static final Logger LOGGER = LoggerFactory.getLogger(DisLikeArticleHandler.class);

    @Autowired
    private ArticleDislikeRelationRepository articleDislikeRelationRepository;

    protected DisLikeArticleHandler(SessionService sessionService, ArticleDislikeRelationRepository articleDislikeRelationRepository) {
        super(DisLikeArticleResponse.class, sessionService);
        this.articleDislikeRelationRepository = articleDislikeRelationRepository;
    }

    /**
     * my idea: 用户对此文章不感兴趣 后端做不对改用户推荐此文章处理 前段做本次点击后隐藏此文章操作
     * @param request
     * @return
     * @throws AppException
     */
    @Override
    public DisLikeArticleResponse execute(DisLikeArticleRequest request) throws AppException {
        ArticleDislikeRelation dislikeRelation = new ArticleDislikeRelation();
        dislikeRelation.setArticleId(request.getId());
        dislikeRelation.setUserId(request.getLoggedInUserId());

        ArticleDislikeRelation save = articleDislikeRelationRepository.save(dislikeRelation);
        LOGGER.info("用户{}对文章{}不感兴趣保存成功！", save.getUserId(), save.getArticleId());
        return new DisLikeArticleResponse(StatusCode.SUCCESS);
    }
}
