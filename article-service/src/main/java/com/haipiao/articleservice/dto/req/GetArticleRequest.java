package com.haipiao.articleservice.dto.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

public class GetArticleRequest extends AbstractRequest {

    @SerializedName("id")
    private int id;

    public GetArticleRequest() {
    }

    public GetArticleRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
