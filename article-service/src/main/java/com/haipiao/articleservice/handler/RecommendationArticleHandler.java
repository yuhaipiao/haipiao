package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.articleservice.dto.resp.RecommendationArticleResponse;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import org.springframework.stereotype.Component;

/**
 * @author wangjipeng
 */
@Component
public class RecommendationArticleHandler extends AbstractHandler<RecommendationArticleRequest, RecommendationArticleResponse> {

    protected RecommendationArticleHandler(SessionService sessionService) {
        super(RecommendationArticleResponse.class, sessionService);
    }

    /**
     * 根据场景推荐文章
     * @param request
     * @return
     * @throws AppException
     */
    @Override
    protected RecommendationArticleResponse execute(RecommendationArticleRequest request) throws AppException {

        return null;
    }
}
