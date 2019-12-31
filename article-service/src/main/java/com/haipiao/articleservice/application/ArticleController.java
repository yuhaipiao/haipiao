package com.haipiao.articleservice.application;

import com.google.common.base.Preconditions;
import com.haipiao.articleservice.constants.DeleteAndLikeConstants;
import com.haipiao.articleservice.dto.req.*;
import com.haipiao.articleservice.dto.resp.*;
import com.haipiao.articleservice.handler.*;
import com.haipiao.common.controller.HealthzController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ArticleController extends HealthzController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private GetArticleHandler getArticleHandler;

    @Autowired
    private CreateArticleHandler createArticleHandler;

    @Autowired
    private DeleteAndLikeArticleHandler deleteAndLikeArticleHandler;

    @Autowired
    private RecommendationArticleHandler recommendationArticleHandler;

    @Autowired
    private DisLikeArticleHandler disLikeArticleHandler;

    @Autowired
    private GetArticleCommentsHandler getArticleCommentsHandler;

    @Autowired
    private GetUserArticleHandler getUserArticleHandler;

    @Autowired
    private GetUserCollectionHandler getUserCollectionHandler;

    @Autowired
    private GetUserAlbumHandler getUserAlbumHandler;

    @Autowired
    private GetUserFollowerHandler getUserFollowerHandler;

    /**
     * API-23
     *
     * @param articleID
     * @param sessionToken
     * @return
     */
    @GetMapping("/article/{articleID}")
    public ResponseEntity<GetArticleResponse> getArticle(
            @CookieValue("session-token") String sessionToken,
            @PathVariable(value = "articleID") String articleID) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        logger.info("articleID={}", articleID);
        Preconditions.checkArgument(StringUtils.isNotEmpty(articleID));
        int aid = Integer.parseInt(articleID);
        GetArticleRequest req = new GetArticleRequest();
        req.setId(aid);
        return getArticleHandler.handle(sessionToken, req);
    }

    @PostMapping("/article")
    public ResponseEntity<CreateArticleResponse> createArticle(
            @CookieValue("session-token") String sessionToken,
            @RequestBody CreateArticleRequest req) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        return createArticleHandler.handle(sessionToken, req);
    }

    /**
     * API-9
     * 根据场景推荐文章
     *
     * @param sessionToken
     * @param context
     * @param category
     * @param article
     * @param user
     * @param latitude
     * @param longitude
     * @param limit
     * @param cursor
     * @return
     */
    @GetMapping("/recommendation/article")
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public ResponseEntity<RecommendationArticleResponse> recommendationArticle(@CookieValue("session-token") String sessionToken,
                                                                               @RequestParam("context") String context,
                                                                               @RequestParam("category") String category,
                                                                               @RequestParam("article") int article,
                                                                               @RequestParam("user") int user,
                                                                               @RequestParam("latitude") float latitude,
                                                                               @RequestParam("longitude") float longitude,
                                                                               @RequestParam("limit") int limit,
                                                                               @RequestParam("cursor") String cursor) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(StringUtils.isNotEmpty(context));
        return recommendationArticleHandler.handle(new RecommendationArticleRequest(context, category, article, user, latitude, longitude, limit, cursor));
    }

    @PostMapping("/article/{id}/like")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<LikeArticleResponse> likeArticle(@PathVariable(value = "id") int id,
                                                           @CookieValue("session-token") String sessionToken) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        StringUtils.isNotEmpty(String.valueOf(id));
        LikeArticleRequest request = new LikeArticleRequest(id, DeleteAndLikeConstants.LIKE_ARTICLE);
        return deleteAndLikeArticleHandler.handle(sessionToken, request);
    }


    /**
     * API-19
     * 获取用户笔记
     *
     * @param sessionToken
     * @param id
     * @param limit
     * @param cursor
     * @return
     */
    @GetMapping("/user/{id}/article")
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public ResponseEntity<ArticleResponse> getUserArticle(@CookieValue("session-token") String sessionToken,
                                                          @PathVariable(value = "id") Integer id,
                                                          @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                                          @RequestParam(value = "cursor", defaultValue = "0") String cursor) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(null != id);
        return getUserArticleHandler.handle(new GetArticleCommentsRequest(id, cursor, limit));
    }

    /**
     * API-20
     * 获取用户收藏笔记
     *
     * @param sessionToken
     * @param id
     * @param limit
     * @param cursor
     * @return
     */
    @GetMapping("/user/{id}/collection")
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public ResponseEntity<ArticleResponse> getUserCollection(@CookieValue("session-token") String sessionToken,
                                                             @PathVariable(value = "id") Integer id,
                                                             @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                                             @RequestParam(value = "cursor", defaultValue = "0") String cursor) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(null != id);
        return getUserCollectionHandler.handle(new GetArticleCommentsRequest(id, cursor, limit));
    }

    /**
     * API-21
     * 获取用户收藏的专辑
     *
     * @param sessionToken
     * @param id
     * @param limit
     * @param cursor
     * @return
     */
    @GetMapping("/user/{id}/album")
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public ResponseEntity<AlbumResponse> getUserAlbum(@CookieValue("session-token") String sessionToken,
                                                      @PathVariable(value = "id") Integer id,
                                                      @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                                      @RequestParam(value = "cursor", defaultValue = "0") String cursor) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(null != id);
        return getUserAlbumHandler.handle(new GetArticleCommentsRequest(id, cursor, limit));
    }

    /**
     * API-22
     * 获取用户粉丝列表
     *
     * @param sessionToken
     * @param id
     * @param limit
     * @param cursor
     * @return
     */
    @GetMapping("/user/{id}/follower")
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public ResponseEntity<FollowerResponse> getUserFollower(@CookieValue("session-token") String sessionToken,
                                                      @PathVariable(value = "id") Integer id,
                                                      @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                                      @RequestParam(value = "cursor", defaultValue = "0") String cursor) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(null != id);
        return getUserFollowerHandler.handle(new GetArticleCommentsRequest(id, cursor, limit));
    }

    /**
     * API-12
     * 用户取消文章点赞
     *
     * @param id
     * @param sessionToken
     */
    @DeleteMapping("/article/{id}/like")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<LikeArticleResponse> cancelLikeArticle(@PathVariable(value = "id") int id,
                                                                 @CookieValue("session-token") String sessionToken) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        StringUtils.isNotEmpty(String.valueOf(id));
        LikeArticleRequest request = new LikeArticleRequest(id, DeleteAndLikeConstants.DELETE_LIKE_ARTICLE);
        return deleteAndLikeArticleHandler.handle(sessionToken, request);
    }

    /**
     * API-13
     * 用户对文章不感兴趣
     *
     * @param id
     * @param sessionToken
     */
    @PostMapping("/article/{id}/dislike")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<DisLikeArticleResponse> disLikeArticle(@PathVariable(value = "id") int id,
                                                                 @CookieValue("session-token") String sessionToken) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(StringUtils.isNotEmpty(String.valueOf(id)));
        return disLikeArticleHandler.handle(sessionToken, new DisLikeArticleRequest(id));
    }

    /**
     * API-24
     * 加载评论列表
     */
    @GetMapping("/article/{id}/comment")
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public ResponseEntity<GetArticleCommentsResponse> getArticleComments(@CookieValue("session-token") String sessionToken,
                                                                         @PathVariable("id") int id,
                                                                         @RequestParam("cursor") String cursor,
                                                                         @RequestParam("limit") int limit) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(StringUtils.isNotEmpty(String.valueOf(id)));
        GetArticleCommentsRequest request = new GetArticleCommentsRequest(id, cursor, limit);
        return getArticleCommentsHandler.handle(sessionToken, request);
    }
}
