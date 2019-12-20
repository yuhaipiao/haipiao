package com.haipiao.articleservice.dto.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

/**
 * @author wangjipeng
 */
public class GetArticleCommentsRequest extends AbstractRequest {

    @SerializedName("id")
    private int id;

    @SerializedName("cursor")
    private String cursor;

    @SerializedName("limit")
    private int limit;

    public GetArticleCommentsRequest() {
    }

    public GetArticleCommentsRequest(int id, String cursor, int limit) {
        this.id = id;
        this.cursor = cursor;
        this.limit = limit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
