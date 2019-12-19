package com.haipiao.userservice.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

/**
 * @author wnagjipeng
 */
public class GetCategoryRequest extends AbstractRequest {

    @SerializedName("type")
    private String type;

    public GetCategoryRequest() {
    }

    public GetCategoryRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
