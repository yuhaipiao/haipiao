package com.haipiao.userservice.resp;

import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;

/**
 * @author wangshun
 */
public class OperateResponse extends AbstractResponse {

    public OperateResponse(StatusCode statusCode) {
        super(statusCode);
    }
}
