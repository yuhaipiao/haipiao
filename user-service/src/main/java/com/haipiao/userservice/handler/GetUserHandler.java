package com.haipiao.userservice.handler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haipiao.persist.entity.User;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.persist.repository.UserRepository;
import com.haipiao.userservice.req.GetUserRequest;
import com.haipiao.userservice.resp.GetUserResponse;

@Component
public class GetUserHandler extends AbstractHandler<GetUserRequest, GetUserResponse>  {
    @Autowired
    private final UserRepository userRepository;

    public GetUserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetUserResponse handle(GetUserRequest req) {
        GetUserResponse resp = new GetUserResponse();
        GetUserResponse.Data data = new GetUserResponse.Data();

        int userID = req.getId();
        Optional<User> optionalUser = userRepository.findById(userID);
        User user;
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            resp.setSuccess(false);
            resp.setError(String.format("user %s not found in DB", userID));
            return resp;
        }

        data.setId(user.getUserId());
        data.setName(user.getUserName());
        data.setGender(user.getGender().toString());
        data.setDescription(user.getSignature());
        data.setProfileImageUrl(user.getProfileImgUrl());
        data.setProfileImageUrlSmall(user.getProfileImgUrlSmall());
        data.setOrganization(user.getOrganization());
        // TODO: show better location
        data.setLocation(user.getCountry()+user.getCity());

        // TODO
        // data.setFollowersCount();
        // data.setTotalLikes();
        // data.setTotalCollects();

        // TODO: set level
        // data.setLevel();

        resp.setSuccess(true);
        resp.setData(data);
        return resp;
    }
}