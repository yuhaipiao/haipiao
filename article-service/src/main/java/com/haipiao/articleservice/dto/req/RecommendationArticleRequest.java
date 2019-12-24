package com.haipiao.articleservice.dto.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

/**
 * @author wangjipeng
 */
public class RecommendationArticleRequest extends AbstractRequest {

    @SerializedName("context")
    private String context;

    @SerializedName("category")
    private String category;

    @SerializedName("article")
    private int articleId;

    @SerializedName("user")
    private int userId;

    @SerializedName("latitude")
    private float latitude;

    @SerializedName("longitude")
    private float longitude;

    @SerializedName("limit")
    private int limit;

    @SerializedName("cursor")
    private String cursor;

    public RecommendationArticleRequest() {
    }

    public RecommendationArticleRequest(String context, String category, int articleId, int userId, float latitude, float longitude, int limit, String cursor) {
        this.context = context;
        this.category = category;
        this.articleId = articleId;
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.limit = limit;
        this.cursor = cursor;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
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
