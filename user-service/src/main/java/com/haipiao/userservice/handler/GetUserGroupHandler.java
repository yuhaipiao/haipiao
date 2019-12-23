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

        List<UserGroup> userGroupList = new ArrayList<>();
        if(UserGroupTypeEnum.CUSTOM.getValue().equals(request.getType())){
            userGroupList = userGroupRepository.findUserGroupByOwnerIdAndType(request.getId(), UserGroupTypeEnum.CUSTOM.getValue());
        }else {
            userGroupList = userGroupRepository.findUserGroupByType(request.getType());
        }

        if(!CollectionUtils.isEmpty(userGroupList)){
            List<GetGroupDto> groups = userGroupList.stream()
                    .map(userGroup -> new GetGroupDto(userGroup.getGroupId(),userGroup.getName()))
                    .collect((Collectors.toList()));

            if(!CollectionUtils.isEmpty(groups)){
                GetGroupResponse.Data groupResponseData = new GetGroupResponse.Data();
                groupResponse.setData(groupResponseData);
            }
        }
        return groupResponse;
    }
}
