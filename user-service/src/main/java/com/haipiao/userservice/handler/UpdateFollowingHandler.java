package com.haipiao.userservice.handler;

import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.userservice.req.UpdateFollowingRequest;
import com.haipiao.userservice.resp.UpdateFollowingResponse;
import org.springframework.stereotype.Component;

/**
 * @author wangjipeng
 */
@Component
public class UpdateFollowingHandler extends AbstractHandler<UpdateFollowingRequest, UpdateFollowingResponse> {

    protected UpdateFollowingHandler(SessionService sessionService) {
        super(UpdateFollowingResponse.class, sessionService);
    }

    @Override
    public UpdateFollowingResponse execute(UpdateFollowingRequest request) throws AppException {


        return null;
    }
}
