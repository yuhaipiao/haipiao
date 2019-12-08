package com.haipiao.userservice.resp;

import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;

/**
 * @author wangjipeng
 */
public class FolloweeUserResponse extends AbstractResponse {

    public FolloweeUserResponse(StatusCode statusCode) {
        super(statusCode);
    }
}
