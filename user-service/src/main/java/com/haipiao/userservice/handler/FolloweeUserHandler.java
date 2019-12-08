package com.haipiao.userservice.handler;

import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.entity.UserFollowingRelation;
import com.haipiao.persist.repository.UserFollowingRelationRepository;
import com.haipiao.persist.repository.UserRepository;
import com.haipiao.userservice.req.FolloweeUserRequest;
import com.haipiao.userservice.resp.FolloweeUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * @author wangjipeng
 */
@Component
public class FolloweeUserHandler extends AbstractHandler<FolloweeUserRequest, FolloweeUserResponse> {

    private static final Logger LOG = LoggerFactory.getLogger(FolloweeUserHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFollowingRelationRepository userFollowingRelationRepository;

    protected FolloweeUserHandler(SessionService sessionService, UserRepository userRepository) {
        super(FolloweeUserResponse.class, sessionService);
        this.userRepository = userRepository;
    }

    /**
     * - `BAD_REQUEST`: group_id或followee_id不存在。
     * - `UNAUTHORIZED`: 用户未登录或者session token不合法。
     * - `INTERNAL_SERVER_ERROR`: 未知服务器错误。
     * @param request
     * @return
     */
    @Override
    protected FolloweeUserResponse execute(FolloweeUserRequest request) {
        // TODO 获取当前用户id 关注用户分组 数据库中暂无分组信息 当前用户为关注者  请求id用户为被关注用户
        int followingUserId = 0;
        Optional<User> user = userRepository.findById(request.getFolloweeId());
        if (user.isEmpty()){
            String errorMessage = String.format("当前关注用户不存在, Id:%s", request.getFolloweeId());
            LOG.info(errorMessage);
            FolloweeUserResponse response = new FolloweeUserResponse(StatusCode.BAD_REQUEST);
            response.setErrorMessage(errorMessage);
            return response;
        }
        userFollowingRelationRepository.save(new UserFollowingRelation(new Date(), null, user.get().getUserId(), followingUserId));

        //TODO redis set结构存储被关注用户+关注者 供用户推荐使用
        saveFolloweeUserToRedis(followingUserId, user.get().getUserId());
        return new FolloweeUserResponse(StatusCode.SUCCESS);
    }

    private void saveFolloweeUserToRedis(int followeeWithId, int userId){
        // TODO 当前redisClient配置不支持
    }
}
