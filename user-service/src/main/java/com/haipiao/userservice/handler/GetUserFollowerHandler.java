package com.haipiao.userservice.handler;

import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.UserFollowingRelationRepository;
import com.haipiao.persist.repository.UserRepository;
import com.haipiao.persist.utils.PageUtil;
import com.haipiao.userservice.req.GetUserFollowerRequest;
import com.haipiao.userservice.resp.GetUserFollowerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author wangjipeng
 */
@Component
public class GetUserFollowerHandler extends AbstractHandler<GetUserFollowerRequest, GetUserFollowerResponse> {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetUserFollowerHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFollowingRelationRepository userFollowingRelationRepository;

    protected GetUserFollowerHandler(SessionService sessionService, UserFollowingRelationRepository userFollowingRelationRepository) {
        super(GetUserFollowerResponse.class, sessionService);
        this.userFollowingRelationRepository = userFollowingRelationRepository;
    }

    @Override
    public GetUserFollowerResponse execute(GetUserFollowerRequest request) throws AppException {
        GetUserFollowerResponse response = new GetUserFollowerResponse(StatusCode.SUCCESS);

        int cursor = PageUtil.cursor(request.getCursor());
        int limit = PageUtil.limit(request.getLimit());
        List<Integer> followingIds = userFollowingRelationRepository.findUserFollowingIdsByUserId(request.getId(), cursor, limit);
        if (followingIds == null || followingIds.size() <= 0){
            String errorMessage = String.format("%s: 当前用户无粉丝", request.getId());
            LOGGER.info(errorMessage);
            response = new GetUserFollowerResponse(StatusCode.NOT_FOUND);
            response.setErrorMessage(errorMessage);
            return response;
        }

        int totalCount = (int)getTotalCount(request.getId());
        response.setData(new GetUserFollowerResponse.Data(getFollowerList(followingIds), totalCount, String.valueOf(limit + cursor), cursor < totalCount));
        return response;
    }

    private List<GetUserFollowerResponse.Data.Follower> getFollowerList(List<Integer> followingIds){
        Iterable<User> users = userRepository.findAllById(followingIds);
        return StreamSupport.stream(users.spliterator(), false)
                .filter(Objects::nonNull)
                .map(u -> new GetUserFollowerResponse.Data.Follower(u.getUserId(), u.getUserName(), u.getProfileImgUrl(), (int)getTotalCount(u.getUserId()), true))
                .collect(Collectors.toList());
    }

    private long getTotalCount(int id){
        return userFollowingRelationRepository.findByUserId(id);
    }
}
