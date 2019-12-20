package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.req.GetArticleCommentsRequest;
import com.haipiao.articleservice.dto.resp.GetArticleCommentsResponse;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import org.springframework.stereotype.Component;

/**
 * @author wangjipeng
 */
@Component
public class GetArticleCommentsHandler extends AbstractHandler<GetArticleCommentsRequest, GetArticleCommentsResponse> {

    protected GetArticleCommentsHandler(SessionService sessionService) {
        super(GetArticleCommentsResponse.class, sessionService);
    }

    @Override
    public GetArticleCommentsResponse execute(GetArticleCommentsRequest request) throws AppException {


        return null;
    }
}
