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

    @Autowired
    private GetUserAllCategoryHandler getUserAllCategoryHandler;

    @Autowired
    private UpdateFollowingHandler updateFollowingHandler;

    @Autowired
    private GetUserFollowerHandler getUserFollowerHandler;

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
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        GetCategoryRequest request = new GetCategoryRequest(type);
        Preconditions.checkArgument(StringUtils.isNotEmpty(type));
        return getCategoryHandler.handle(sessionToken, request);
    }

    @PostMapping("/user/category")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<SaveUserCategoryResponse> saveUserCategory(@CookieValue("session-token") String sessionToken,
                                                                     @RequestBody SaveUserCategoryRequest request){
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        return saveCategoryHandler.handle(sessionToken, request);
    }

    @GetMapping("/recommendation/user")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<RecommendationResponse> recommendation(@CookieValue("session-token") String sessionToken,
                                                                 @RequestParam("context") String context,
                                                                 @RequestParam("article") int article,
                                                                 @RequestParam("user") int user,
                                                                 @RequestParam("limit") int limit,
                                                                 @RequestParam("cursor") int cursor){
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(StringUtils.isNotEmpty(context));
        RecommendationRequest request = new RecommendationRequest(context, article, user, limit, cursor);
        return recommendationHandler.handle(sessionToken, request);
    }

    @PostMapping("/group/{group_id}/user/{followee_id}")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<FolloweeUserResponse> followeeUser(@CookieValue("session-token") String sessionToken,
                                         @PathVariable(value="group_id") int groupId,
                                         @PathVariable(value="followee_id") int followeeId){
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(StringUtils.isNotEmpty(String.valueOf(groupId)));
        Preconditions.checkArgument(StringUtils.isNotEmpty(String.valueOf(followeeId)));
        FolloweeUserRequest request = new FolloweeUserRequest(groupId, followeeId);
        return followeeUserHandler.handle(sessionToken, request);
    }

    @GetMapping("/user/{id}/category")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<GetUserAllCategoryResponse> getUserAllCategory(@CookieValue("session-token") String sessionToken,
                                   @PathVariable(value="id") int id){
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(StringUtils.isNotEmpty(String.valueOf(id)));
        return getUserAllCategoryHandler.handle(sessionToken, new GetUserAllCategoryRequest(id));
    }

    /**
     * API-14
     * 获取关注用户的更新
     * 检查当前用户所关注的用户是否有更新
     * check：用户的关注是否有更新。用于显示蓝湖图10关注标签上的小红点。
     * pull： 获取用户的关注的对象们的更新列表。
     * @param sessionToken
     * @param type
     */
    @GetMapping("/update/following")
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<UpdateFollowingResponse> updateFollowing(@CookieValue("session-token") String sessionToken,
                                                                   @RequestParam(value="type") String type){
        Preconditions.checkArgument(StringUtils.isNotEmpty(sessionToken));
        Preconditions.checkArgument(StringUtils.isNotEmpty(type));
        return updateFollowingHandler.handle(sessionToken, new UpdateFollowingRequest(type));
    }

    /**
     * API-22
     * 获取用户所有粉丝
     * @param sessionToken
     * @param id
     * @param limit
     * @param cursor
     */
    @GetMapping("/user/{id}/follower")
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public ResponseEntity<GetUserFollowerResponse> getUserFollower(@CookieValue("session-token") String sessionToken,
                             @PathVariable("id") int id,
                             @RequestParam("limit") int limit,
                             @RequestParam("cursor") String cursor){
        GetUserFollowerRequest request = new GetUserFollowerRequest(id, limit, cursor);
        return getUserFollowerHandler.handle(sessionToken, request);
    }
}
