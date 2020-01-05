package com.haipiao.userservice.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

/**
 * @author wangjipeng
 */
public class UpdateFollowingRequest extends AbstractRequest {

    @SerializedName("type")
    private String type;

    public UpdateFollowingRequest() {
    }

    public UpdateFollowingRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
