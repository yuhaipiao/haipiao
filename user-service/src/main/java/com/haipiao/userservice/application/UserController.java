package com.haipiao.userservice.application;

import com.google.common.base.Preconditions;
import com.haipiao.common.resp.AbstractResponse;
import com.haipiao.userservice.handler.*;
import com.haipiao.userservice.req.*;
import com.haipiao.userservice.resp.*;
import com.haipiao.common.controller.HealthzController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends HealthzController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private GetUserHandler getUserHandler;

    @Autowired
    private CreateUserHandler createUserHandler;

    @Autowired
    private GetCategoryHandler getCategoryHandler;

    @Autowired
    private SaveCategoryHandler saveCategoryHandler;

    @Autowired
    private RecommendationHandler recommendationHandler;

    @Autowired
    private FolloweeUserHandler followeeUserHandler;

    @GetMapping("/user/{userID}/summary")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable(value="userID") String userID) {
        logger.info("userID={}", userID);
        Preconditions.checkArgument(StringUtils.isNotEmpty(userID));

        GetUserRequest req = new GetUserRequest().setId(Integer.parseInt(userID));
        return getUserHandler.handle(req);
    }

    @GetMapping("/user/summary")
    public ResponseEntity<GetUserResponse> getLoggedInUser(@CookieValue("session-token") String sessionToken) {

        GetUserRequest req = new GetUserRequest();
        return getUserHandler.handle(sessionToken, req);
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUserResponse> createUser(@CookieValue("session-token") String temporarySessionToken,
                                                         @RequestBody CreateUserRequest req) {
        req.setOldSessionToken(temporarySessionToken);
        Preconditions.checkArgument(StringUtils.isNotEmpty(req.getName()));
        Preconditions.checkArgument(StringUtils.isNotEmpty(req.getGender()));
        Preconditions.checkArgument(StringUtils.isNotEmpty(req.getBirthday()));
        // profile image url is optional
        return createUserHandler.handle(req);
    }

    @GetMapping("/category")
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public ResponseEntity<GetCategoryResponse> category(@CookieValue("session-token") String sessionToken, @RequestParam("type") String type){
        GetCategoryRequest request = new GetCategoryRequest(type);
        Preconditions.checkArgument(StringUtils.isNotEmpty(type));
        return getCategoryHandler.handle(sessionToken, request);
    }

    @PostMapping("/user/category")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<SaveUserCategoryResponse> saveUserCategory(@CookieValue("session-token") String sessionToken,
                                                                     @RequestBody SaveUserCategoryRequest request){
        return saveCategoryHandler.handle(sessionToken, request);
    }

    @GetMapping("/recommendation/user")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<RecommendationResponse> recommendation(@CookieValue("session-token") String sessionToken,
                                                                 @RequestParam("context") String context,
                                                                 @RequestParam("limit") int limit,
                                                                 @RequestParam("cursor") int cursor){
        Preconditions.checkArgument(StringUtils.isNotEmpty(context));
        RecommendationRequest request = new RecommendationRequest(context, limit, cursor);
        return recommendationHandler.handle(sessionToken, request);
    }

    @PostMapping("/group/{group_id}/user/{followee_id}")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<FolloweeUserResponse> followeeUser(@CookieValue("session-token") String sessionToken,
                                         @PathVariable(value="group_id") int groupId,
                                         @PathVariable(value="followee_id") int followeeId){
        Preconditions.checkArgument(StringUtils.isNotEmpty(String.valueOf(groupId)));
        Preconditions.checkArgument(StringUtils.isNotEmpty(String.valueOf(followeeId)));
        FolloweeUserRequest request = new FolloweeUserRequest(groupId, followeeId);
        return followeeUserHandler.handle(sessionToken, request);
    }
}
