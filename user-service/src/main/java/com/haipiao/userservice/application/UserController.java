package com.haipiao.userservice.application;

import com.google.common.base.Preconditions;
import com.haipiao.userservice.handler.CreateUserHandler;
import com.haipiao.userservice.handler.GetUserHandler;
import com.haipiao.userservice.req.CreateUserRequest;
import com.haipiao.userservice.req.GetUserRequest;
import com.haipiao.userservice.resp.CreateUserResponse;
import com.haipiao.userservice.resp.GetUserResponse;
import com.haipiao.common.controller.HealthzController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends HealthzController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private GetUserHandler getUserHandler;

    @Autowired
    private CreateUserHandler createUserHandler;

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
}
