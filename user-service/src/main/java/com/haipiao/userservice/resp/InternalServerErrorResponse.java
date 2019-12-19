package com.haipiao.userservice.resp;

import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;

/**
 * @author wangjipeng
 */
public class InternalServerErrorResponse extends AbstractResponse {
    public InternalServerErrorResponse(StatusCode statusCode) {
        super(statusCode);
    }
}
