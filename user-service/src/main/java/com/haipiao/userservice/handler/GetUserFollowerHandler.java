package com.haipiao.userservice.handler;

import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.userservice.req.GetUserFollowerRequest;
import com.haipiao.userservice.resp.GetUserFollowerResponse;
import org.springframework.stereotype.Component;

/**
 * @author wangjipeng
 */
@Component
public class GetUserFollowerHandler extends AbstractHandler<GetUserFollowerRequest, GetUserFollowerResponse> {

    protected GetUserFollowerHandler(SessionService sessionService) {
        super(GetUserFollowerResponse.class, sessionService);
    }

    @Override
    public GetUserFollowerResponse execute(GetUserFollowerRequest request) throws AppException {

        return null;
    }
}
