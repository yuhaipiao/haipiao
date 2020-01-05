package com.haipiao.articleservice.dto.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;
import com.haipiao.persist.vo.FollowerData;

import java.util.List;

/**
 * @Author: wangshun
 */
public class FollowerResponse extends AbstractResponse<FollowerResponse.Data> {

    public FollowerResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data {
        @SerializedName("followers")
        private List<FollowerData> followers;

        @SerializedName("total_count")
        private int totalCount;

        @SerializedName("cursor")
        private String cursor;

        @SerializedName("more_to_follow")
        private boolean moreToFollow;

        public Data(List<FollowerData> followers, int totalCount, String cursor, boolean moreToFollow) {
            this.followers = followers;
            this.totalCount = totalCount;
            this.cursor = cursor;
            this.moreToFollow = moreToFollow;
        }

        public List<FollowerData> getFollowers() {
            return followers;
        }

        public void setFollowers(List<FollowerData> followers) {
            this.followers = followers;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public String getCursor() {
            return cursor;
        }

        public void setCursor(String cursor) {
            this.cursor = cursor;
        }

        public boolean isMoreToFollow() {
            return moreToFollow;
        }

        public void setMoreToFollow(boolean moreToFollow) {
            this.moreToFollow = moreToFollow;
        }
    }
}
