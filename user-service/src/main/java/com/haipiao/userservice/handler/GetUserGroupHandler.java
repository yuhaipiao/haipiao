package com.haipiao.userservice.handler;

import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.userservice.req.GetUserRequest;
import com.haipiao.userservice.req.UpdateFollowingRequest;
import com.haipiao.userservice.resp.GetGroupResponse;
import com.haipiao.userservice.resp.UpdateFollowingResponse;
import org.springframework.stereotype.Component;

/**
 * @author wangshun
 */
@Component
public class GetUserGroupHandler extends AbstractHandler<GetUserRequest, GetGroupResponse> {

    protected GetUserGroupHandler(SessionService sessionService) {
        super(GetGroupResponse.class, sessionService);
    }

    @Override
    public GetGroupResponse execute(GetUserRequest request) throws AppException {

        return null;
    }
}
