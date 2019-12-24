package com.haipiao.userservice.handler;

import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.entity.UserFollowingRelation;
import com.haipiao.persist.entity.UserGroup;
import com.haipiao.persist.repository.UserFollowingRelationRepository;
import com.haipiao.persist.repository.UserGroupRepository;
import com.haipiao.persist.repository.UserRepository;
import com.haipiao.userservice.enums.UserGroupTypeEnum;
import com.haipiao.userservice.req.CreateGroupRequest;
import com.haipiao.userservice.req.DeleteGroupRequest;
import com.haipiao.userservice.resp.OperateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static com.haipiao.common.enums.StatusCode.NOT_FOUND;
import static com.haipiao.common.enums.StatusCode.SUCCESS;


/**
 * @author wangshun
 */
@Component
public class CreateGroupHandler extends AbstractHandler<CreateGroupRequest, OperateResponse> {
    public static final Logger LOG = LoggerFactory.getLogger(CreateGroupHandler.class);

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFollowingRelationRepository userFollowingRelationRepository;


    public CreateGroupHandler(SessionService sessionService,
                              UserGroupRepository userGroupRepository,
                              UserRepository userRepository,
                              UserFollowingRelationRepository userFollowingRelationRepository) {
        super(OperateResponse.class, sessionService);
        this.userGroupRepository = userGroupRepository;
        this.userRepository = userRepository;
        this.userFollowingRelationRepository = userFollowingRelationRepository;
    }

    @Override
    public OperateResponse execute(CreateGroupRequest request) throws AppException {
        Integer userId = request.getUserId();
        if(null != userId) {
            if(!checkUser(userId)){
                OperateResponse resp = new OperateResponse(NOT_FOUND);
                resp.setErrorMessage(String.format("userId: %s not found in DB", userId));
                return resp;
            }
        }

        Integer followingUserId = request.getLoggedInUserId();
        UserGroup userGroup = new UserGroup();
        userGroup.setName(request.getGroupName());
        userGroup.setOwnerId(followingUserId);
        userGroup.setType(UserGroupTypeEnum.CUSTOM.getValue());
        userGroup.setCreateTs(new Date());
        LOG.info("sava userGroup start " + userGroup.toString());
        userGroup = userGroupRepository.save(userGroup);
        LOG.info("sava userGroup end");

        if(null != userId){
            UserFollowingRelation userFollowingRelation = new UserFollowingRelation();
            userFollowingRelation.setGroupId(userGroup.getGroupId());
            userFollowingRelation.setFollowingUserId(followingUserId);
            userFollowingRelation.setUserId(userId);
            userFollowingRelation.setCreateTs(new Date());
            LOG.info("sava userFollowingRelation start" + userFollowingRelation.toString());
            userFollowingRelationRepository.save(userFollowingRelation);
            LOG.info("sava userFollowingRelation end");
        }

        OperateResponse resp = new OperateResponse(SUCCESS);
        return resp;
    }

    private boolean checkUser(int userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            return true;
        }
        return false;
    }

    private boolean checkUserGroup(int userId,int groupId){
        int count = userGroupRepository.countByGroupIdAndOwnerId(userId,groupId);
        if(count == 1) {
            return true;
        }
        return false;
    }

}
