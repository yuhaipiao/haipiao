package com.haipiao.articleservice.dto.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

/**
 * @author wangjipeng
 */
public class LikeArticleRequest extends AbstractRequest {

    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private int type;

    public LikeArticleRequest() {
    }

    public LikeArticleRequest(int id, int type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
