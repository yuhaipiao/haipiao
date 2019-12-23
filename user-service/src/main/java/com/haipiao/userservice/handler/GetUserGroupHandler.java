package com.haipiao.userservice.handler;

import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.Category;
import com.haipiao.persist.entity.UserGroup;
import com.haipiao.persist.repository.UserGroupRepository;
import com.haipiao.userservice.enums.UserGroupTypeEnum;
import com.haipiao.userservice.req.GetUserGroupRequest;
import com.haipiao.userservice.req.GetUserRequest;
import com.haipiao.userservice.resp.GetCategoryResponse;
import com.haipiao.userservice.resp.GetGroupResponse;
import com.haipiao.userservice.resp.dto.GetGroupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author wangshun
 */
@Component
public class GetUserGroupHandler extends AbstractHandler<GetUserGroupRequest, GetGroupResponse> {
    public static final Logger LOG = LoggerFactory.getLogger(GetUserGroupHandler.class);

    @Autowired
    private UserGroupRepository userGroupRepository;

    public GetUserGroupHandler(SessionService sessionService,
                               UserGroupRepository userGroupRepository) {
        super(GetGroupResponse.class, sessionService);
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public GetGroupResponse execute(GetUserGroupRequest request) throws AppException {
        GetGroupResponse groupResponse = new GetGroupResponse(StatusCode.SUCCESS);

        List<UserGroup> userGroupList = UserGroupTypeEnum.CUSTOM.getValue().equals(request.getType()) ? findByOwnerAndType(request) : findByType(request);

        if(userGroupList.size() <= 0){
            String errorMessage = String.format("%s: 该用户没有分组数据, 数据异常!", request.getId());
            LOG.info(errorMessage);
            groupResponse = new GetGroupResponse(StatusCode.NOT_FOUND);
            groupResponse.setErrorMessage(errorMessage);
            return groupResponse;
        }
        List<GetGroupDto> groups = userGroupList.stream()
                .filter(Objects::nonNull)
                .map(userGroup -> new GetGroupDto(userGroup.getGroupId(),userGroup.getName()))
                .collect((Collectors.toList()));
        groupResponse.setData(new GetGroupResponse.Data(groups));
        return groupResponse;
    }

    private List<UserGroup> findByOwnerAndType(GetUserGroupRequest request){
        return userGroupRepository.findUserGroupsByOwnerIdAndType(request.getId(), UserGroupTypeEnum.CUSTOM.getValue());
    }

    private List<UserGroup> findByType(GetUserGroupRequest request){
        return userGroupRepository.findUserGroupsByType(request.getType());
    }
}
