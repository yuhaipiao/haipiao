package com.haipiao.articleservice.dto.req;

import com.haipiao.common.req.AbstractRequest;

/**
 * @author wangjipeng
 */
public class DisLikeArticleRequest extends GetArticleRequest {
    public DisLikeArticleRequest() {
    }

    public DisLikeArticleRequest(int id) {
        super(id);
    }
}
