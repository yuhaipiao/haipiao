package com.haipiao.userservice.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

/**
 * @author wangjipeng
 */
public class GetUserFollowerRequest extends AbstractRequest {

    @SerializedName("id")
    private int id;

    @SerializedName("limit")
    private int limit;

    @SerializedName("cursor")
    private String cursor;

    public GetUserFollowerRequest() {
    }

    public GetUserFollowerRequest(int id, int limit, String cursor) {
        this.id = id;
        this.limit = limit;
        this.cursor = cursor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }
}
