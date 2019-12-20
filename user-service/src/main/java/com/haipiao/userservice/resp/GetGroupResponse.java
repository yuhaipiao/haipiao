package com.haipiao.userservice.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;
import com.haipiao.userservice.resp.dto.GetGroupDto;
import lombok.Data;

import java.util.List;

/**
 * @author wangshun
 */
@Data
public class GetGroupResponse extends AbstractResponse<GetGroupResponse.Data> {

    public GetGroupResponse(StatusCode statusCode) {
        super(statusCode);
    }

    @lombok.Data
    public static class Data {
        @SerializedName("followers")
        private List<GetGroupDto> followers;
    }
}
