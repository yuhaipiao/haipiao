package com.haipiao.userservice.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

/**
 * @author wangjipeng
 */
public class RecommendationRequest extends AbstractRequest {

    @SerializedName("context")
    private String context;

    @SerializedName("article")
    private int articleId;

    @SerializedName("user")
    private int userId;

    @SerializedName("limit")
    private int limit;

    @SerializedName("cursor")
    private int cursor;

    public RecommendationRequest() {
    }

    public RecommendationRequest(String context, int articleId, int userId, int limit, int cursor) {
        this.context = context;
        this.articleId = articleId;
        this.userId = userId;
        this.limit = limit;
        this.cursor = cursor;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }
}
