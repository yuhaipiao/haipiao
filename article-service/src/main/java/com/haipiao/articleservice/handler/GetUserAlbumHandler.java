package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.req.GetArticleCommentsRequest;
import com.haipiao.articleservice.dto.resp.AlbumResponse;
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
public class GetUserAlbumHandler extends AbstractHandler<GetArticleCommentsRequest, AlbumResponse> {

    public static final Logger LOG = LoggerFactory.getLogger(GetUserAlbumHandler.class);

    @Autowired
    private UserRepository userRepository;

    protected GetUserAlbumHandler(SessionService sessionService,
                                  UserRepository userRepository) {
        super(AlbumResponse.class, sessionService);
        this.userRepository = userRepository;
    }

    @Override
    public AlbumResponse execute(GetArticleCommentsRequest request) throws AppException {
        AlbumResponse resp = new AlbumResponse(StatusCode.SUCCESS);
        return resp;
    }
}
