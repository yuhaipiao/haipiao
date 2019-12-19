package com.haipiao.articleservice.application;

import com.google.common.base.Preconditions;
import com.haipiao.articleservice.constants.DeleteAndLikeConstants;
import com.haipiao.articleservice.dto.req.LikeArticleRequest;
import com.haipiao.articleservice.dto.req.RecommendationArticleRequest;
import com.haipiao.articleservice.dto.resp.LikeArticleResponse;
import com.haipiao.articleservice.dto.resp.RecommendationArticleResponse;
import com.haipiao.articleservice.handler.DeleteAndLikeArticleHandler;
import com.haipiao.articleservice.handler.RecommendationArticleHandler;
import com.haipiao.common.controller.HealthzController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.haipiao.articleservice.handler.CreateArticleHandler;
import com.haipiao.articleservice.handler.GetArticleHandler;
import com.haipiao.articleservice.dto.req.GetArticleRequest;
import com.haipiao.articleservice.dto.req.CreateArticleRequest;
import com.haipiao.articleservice.dto.resp.CreateArticleResponse;
import com.haipiao.articleservice.dto.resp.GetArticleResponse;
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

    /**
     * API-16
     * @param sessionToken
     * @param articleID
     * @return
     */
    @GetMapping("/article/{articleID}")
    public ResponseEntity<GetArticleResponse> getArticle(
            @CookieValue("session-token") String sessionToken,
            @PathVariable(value="articleID") String articleID) {
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
                                                                               @RequestParam("cursor") int cursor){
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(StringUtils.isNotEmpty(context));
        return recommendationArticleHandler.handle(new RecommendationArticleRequest(context, category, article, user, latitude, longitude, limit, cursor));
    }

    /**
     * API-11
     * 用户点赞
     * @param id
     * @param sessionToken
     * @return
     */
    @PostMapping("/article/{id}/like")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<LikeArticleResponse> likeArticle(@PathVariable(value = "id") int id,
                                                           @CookieValue("session-token") String sessionToken) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        StringUtils.isNotEmpty(String.valueOf(id));
        return deleteAndLikeArticleHandler.handle(new LikeArticleRequest(id, DeleteAndLikeConstants.LIKE_ARTICLE));
    }

    /**
     * API-12
     * 用户取消文章点赞
     * @param id
     * @param sessionToken
     */
    @DeleteMapping("/article/{id}/like")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<LikeArticleResponse> cancelLikeArticle(@PathVariable(value = "id") int id,
                                  @CookieValue("session-token") String sessionToken){
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        StringUtils.isNotEmpty(String.valueOf(id));
        return deleteAndLikeArticleHandler.handle(new LikeArticleRequest(id, DeleteAndLikeConstants.DELETE_LIKE_ARTICLE));
    }

    /**
     * API-13
     * 用户对文章不感兴趣
     * @param id
     * @param sessionToken
     */
    @PostMapping("/article/{id}/dislike")
    @Transactional(rollbackFor = Throwable.class)
    public void disLikeArticle(@PathVariable(value = "id") int id,
                                  @CookieValue("session-token") String sessionToken){
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        StringUtils.isNotEmpty(String.valueOf(id));
        // TODO 用户对此文章不感兴趣 后端做不对改用户推荐此文章处理 前段做本次点击后隐藏此文章操作
    }


}
