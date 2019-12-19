package com.haipiao.userservice.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.req.AbstractRequest;
import com.haipiao.common.resp.AbstractResponse;

import java.util.List;

/**
 * @author wangjipeng
 */
public class SaveUserCategoryResponse extends AbstractResponse {

    public SaveUserCategoryResponse(StatusCode statusCode) {
        super(statusCode);
    }
}
