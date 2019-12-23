package com.haipiao.userservice.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;
import com.haipiao.userservice.resp.dto.GetGroupDto;

import java.util.List;

/**
 * @author wangshun
 */
public class GetGroupResponse extends AbstractResponse<GetGroupResponse.Data> {

    public GetGroupResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data {

        @SerializedName("groups")
        private List<GetGroupDto> groups;

        public List<GetGroupDto> getFollowers() {
            return groups;
        }

        public void setFollowers(List<GetGroupDto> followers) {
            this.groups = followers;
        }
    }
}
