package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.req.DisLikeArticleRequest;
import com.haipiao.articleservice.dto.resp.DisLikeArticleResponse;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import org.springframework.stereotype.Component;

/**
 * @author wangjipeng
 */
@Component
public class DisLikeArticleHandler extends AbstractHandler<DisLikeArticleRequest, DisLikeArticleResponse> {

    protected DisLikeArticleHandler(SessionService sessionService) {
        super(DisLikeArticleResponse.class, sessionService);
    }

    /**
     * myThinking: 用户对此文章不感兴趣 后端做不对改用户推荐此文章处理 前段做本次点击后隐藏此文章操作
     * @param request
     * @return
     * @throws AppException
     */
    @Override
    public DisLikeArticleResponse execute(DisLikeArticleRequest request) throws AppException {

        return null;
    }
}
