package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.req.GetArticleCommentsRequest;
import com.haipiao.articleservice.dto.resp.ArticleResponse;
import com.haipiao.articleservice.dto.resp.vo.ArticleData;
import com.haipiao.articleservice.service.GetArticleCommonService;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.repository.ArticleRepository;
import com.haipiao.persist.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wangshun
 */
@Component
public class GetUserArticleHandler extends AbstractHandler<GetArticleCommentsRequest, ArticleResponse> {

    public static final Logger LOG = LoggerFactory.getLogger(GetUserArticleHandler.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Resource
    private GetArticleCommonService getArticleCommonService;

    @Autowired
    private ImageRepository imageRepository;

    protected GetUserArticleHandler(SessionService sessionService,
                                    ArticleRepository articleRepository,
                                    ImageRepository imageRepository) {
        super(ArticleResponse.class, sessionService);
        this.articleRepository = articleRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public ArticleResponse execute(GetArticleCommentsRequest request) throws AppException {
        Integer userId = request.getId();
        Pageable pageable = PageRequest.of(Integer.valueOf(request.getCursor()), request.getLimit());
        Page<Article> articlesPage = articleRepository.findArticlesByAuthorIdAndStatus(userId, "", pageable);
        List<Article> articles = articlesPage.getContent();
        if (CollectionUtils.isEmpty(articles)) {
            ArticleResponse resp = new ArticleResponse(StatusCode.NOT_FOUND);
            resp.setErrorMessage(String.format("user %s not have article", userId));
            return resp;
        }

        List<ArticleData> articlesList = articles.stream()
                .filter(Objects::nonNull)
                .map(a -> new ArticleData(imageRepository.findFirstByArticleId(a.getArticleId(),0).getExternalUrl(),
                        a.getArticleId(), a.getTitle(), a.getLikes(), getArticleCommonService.checkIsLike(a.getArticleId(), userId),
                        getArticleCommonService.assemblerAuthor(a.getAuthorId())))
                .collect(Collectors.toList());

        ArticleResponse resp = new ArticleResponse(StatusCode.SUCCESS);
        resp.setData(new ArticleResponse.Data(articlesList, (int) articlesPage.getTotalElements(),
                request.getCursor(), articlesPage.getTotalPages() > Integer.valueOf(request.getCursor())));
        return resp;
    }
}
