package com.haipiao.articleservice.dto.resp;

import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;

/**
 * @author wangjipeng
 */
public class LikeArticleResponse extends AbstractResponse {
    public LikeArticleResponse(StatusCode statusCode) {
        super(statusCode);
    }
}
