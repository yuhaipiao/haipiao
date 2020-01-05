package com.haipiao.articleservice.dto.resp;

import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;

/**
 * @author wangjipeng
 */
public class DisLikeArticleResponse extends AbstractResponse {

    public DisLikeArticleResponse(StatusCode statusCode) {
        super(statusCode);
    }
}
