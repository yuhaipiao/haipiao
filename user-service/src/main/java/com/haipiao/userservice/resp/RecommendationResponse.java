package com.haipiao.userservice.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.req.AbstractRequest;
import com.haipiao.common.resp.AbstractResponse;
import com.haipiao.userservice.resp.dto.RecommendationInfoDto;

import java.util.List;

/**
 * @author wangjipeng
 */
public class RecommendationResponse extends AbstractResponse<RecommendationResponse.Data> {

    public RecommendationResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data{

        @SerializedName("users")
        private List<RecommendationInfoDto> users;

        @SerializedName("cursor")
        private long cursor;

        @SerializedName("more_to_follow")
        private boolean moreToFollow;

        public Data() {
        }

        public Data(List<RecommendationInfoDto> users, long cursor, boolean moreToFollow) {
            this.users = users;
            this.cursor = cursor;
            this.moreToFollow = moreToFollow;
        }

        public List<RecommendationInfoDto> getUsers() {
            return users;
        }

        public void setUsers(List<RecommendationInfoDto> users) {
            this.users = users;
        }

        public long getCursor() {
            return cursor;
        }

        public void setCursor(long cursor) {
            this.cursor = cursor;
        }

        public boolean getMoreToFollow() {
            return moreToFollow;
        }

        public void setMoreToFollow(boolean moreToFollow) {
            this.moreToFollow = moreToFollow;
        }
    }
}
