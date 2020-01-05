package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.articleservice.dto.resp.RecommendationArticleResponse;
import com.haipiao.articleservice.enums.RecommendationArticleEnum;
import com.haipiao.articleservice.handler.factory.CommonRecommendationArticle;
import com.haipiao.articleservice.handler.factory.RecommendationArticle;
import com.haipiao.articleservice.handler.factory.RecommendationByTopicRelatedLatest;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangjipeng
 */
@Component
public class RecommendationArticleHandler extends AbstractHandler<RecommendationArticleRequest, RecommendationArticleResponse> {

    public static final Logger LOGGER = LoggerFactory.getLogger(RecommendationArticleHandler.class);

    @Autowired
    private CommonRecommendationArticle commonRecommendationArticle;

    protected RecommendationArticleHandler(SessionService sessionService) {
        super(RecommendationArticleResponse.class, sessionService);
    }

    /**
     * 根据场景推荐文章
     * @param request
     * @return
     * @throws AppException
     */
    @Override
    protected RecommendationArticleResponse execute(RecommendationArticleRequest request) throws AppException {
        RecommendationArticleResponse response = new RecommendationArticleResponse(StatusCode.SUCCESS);
        if (RecommendationArticleEnum.checkRecommendationArticleContext(request.getContext())){
            LOGGER.info(StatusCode.THIS_RECOMMENDED_ARTICLE_VALUE_IS_NOT_EXIST.getDefaultMessage());
            response = new RecommendationArticleResponse(StatusCode.THIS_RECOMMENDED_ARTICLE_VALUE_IS_NOT_EXIST);
            response.setErrorMessage(StatusCode.THIS_RECOMMENDED_ARTICLE_VALUE_IS_NOT_EXIST.getDefaultMessage());
            return response;
        }
        RecommendationArticleResponse.Data data = new RecommendationArticleResponse.Data(commonRecommendationArticle.getArticleDataList(request));
        response.setData(data);
        return response;
    }


}
