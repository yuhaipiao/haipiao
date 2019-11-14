package com.haipiao.articleservice.application;

import com.google.common.base.Preconditions;
import com.haipiao.common.controller.HealthzController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/article/new")
    public ResponseEntity<CreateArticleResponse> createArticle(
            @CookieValue("session-token") String sessionToken,
            @RequestBody CreateArticleRequest req) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        return createArticleHandler.handle(sessionToken, req);
    }
}
