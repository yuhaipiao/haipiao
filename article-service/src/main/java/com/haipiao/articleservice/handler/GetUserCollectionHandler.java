package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.req.GetArticleCommentsRequest;
import com.haipiao.articleservice.dto.resp.ArticleResponse;
import com.haipiao.articleservice.dto.resp.vo.ArticleData;
import com.haipiao.articleservice.dto.resp.vo.Author;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.Article;
import com.haipiao.persist.entity.ArticleLikeRelation;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.ArticleLikeRelationRepository;
import com.haipiao.persist.repository.ArticleRepository;
import com.haipiao.persist.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wangshun
 */
@Component
public class GetUserCollectionHandler extends AbstractHandler<GetArticleCommentsRequest, ArticleResponse> {

    public static final Logger LOG = LoggerFactory.getLogger(GetUserCollectionHandler.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleLikeRelationRepository articleLikeRelationRepository;


    protected GetUserCollectionHandler(SessionService sessionService,
                                       ArticleRepository articleRepository,
                                       UserRepository userRepository,
                                       ArticleLikeRelationRepository articleLikeRelationRepository) {
        super(ArticleResponse.class, sessionService);
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.articleLikeRelationRepository = articleLikeRelationRepository;
    }

    @Override
    public ArticleResponse execute(GetArticleCommentsRequest request) throws AppException {
        Integer userId = request.getId();
        Pageable pageable = PageRequest.of(Integer.valueOf(request.getCursor()),request.getLimit());
        Page<Article> articlesPage = articleRepository.findArticlesByCollectorIdAndStatus(userId,"",pageable);
        List<Article> articles = articlesPage.getContent();
        if(CollectionUtils.isEmpty(articles)){
            ArticleResponse resp = new ArticleResponse(StatusCode.NOT_FOUND);
            resp.setErrorMessage(String.format("user %s not have collection article", userId));
            return resp;
        }

        List<ArticleData> articlesList = articles.stream()
                .filter(Objects::nonNull)
                .map(a -> new ArticleData("", a.getArticleId(), a.getTitle(), a.getLikes(), checkIsLike(a.getArticleId(), userId), assemblerAuthor(userId)))
                .collect(Collectors.toList());

        ArticleResponse resp = new ArticleResponse(StatusCode.SUCCESS);
        resp.setData(new ArticleResponse.Data(articlesList,(int) articlesPage.getTotalElements(),
                request.getCursor(),articlesPage.getTotalPages() > Integer.valueOf(request.getCursor())));
        return resp;
    }

    private Author assemblerAuthor(int authorId){
        Optional<User> optionalUser = userRepository.findById(authorId);
        User user = optionalUser.isEmpty() ? null : optionalUser.get();
        return user == null ? null : new Author(user.getUserId(), user.getUserName(), user.getProfileImgUrl());
    }

    private boolean checkIsLike(int userId, int articleId){
        List<ArticleLikeRelation> likeRelations = articleLikeRelationRepository.findByArticleIdAndAndLikeId(articleId, userId);
        return likeRelations.size() > 0;
    }

}
