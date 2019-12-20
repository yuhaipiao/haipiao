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

        @SerializedName("followers")
        private List<GetGroupDto> followers;

        public Data() {
        }

        public Data(List<GetGroupDto> followers) {
            this.followers = followers;
        }

        public List<GetGroupDto> getFollowers() {
            return followers;
        }

        public void setFollowers(List<GetGroupDto> followers) {
            this.followers = followers;
        }
    }
}
