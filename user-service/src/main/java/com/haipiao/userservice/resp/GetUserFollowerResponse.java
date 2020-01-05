package com.haipiao.userservice.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;

import java.util.List;

/**
 * @author wangjipeng
 */
public class GetUserFollowerResponse extends AbstractResponse<GetUserFollowerResponse.Data> {

    public GetUserFollowerResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data{

        @SerializedName("followers")
        private List<Follower> followers;

        @SerializedName("total_count")
        private int totalCount;

        @SerializedName("cursor")
        private String cursor;

        @SerializedName("more_to_follow")
        private boolean moreToFollow;

        public static class Follower{

            @SerializedName("id")
            private int id;

            @SerializedName("name")
            private String name;

            @SerializedName("profile_image_urls")
            private String profileImageUrls;

            @SerializedName("followers_count")
            private int followersCount;

            @SerializedName("followered")
            private boolean followered;

            public Follower() {
            }

            public Follower(int id, String name, String profileImageUrls, int followersCount, boolean followered) {
                this.id = id;
                this.name = name;
                this.profileImageUrls = profileImageUrls;
                this.followersCount = followersCount;
                this.followered = followered;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProfileImageUrls() {
                return profileImageUrls;
            }

            public void setProfileImageUrls(String profileImageUrls) {
                this.profileImageUrls = profileImageUrls;
            }

            public int getFollowersCount() {
                return followersCount;
            }

            public void setFollowersCount(int followersCount) {
                this.followersCount = followersCount;
            }

            public boolean isFollowered() {
                return followered;
            }

            public void setFollowered(boolean followered) {
                this.followered = followered;
            }
        }

        public Data() {
        }

        public Data(List<Follower> followers, int totalCount, String cursor, boolean moreToFollow) {
            this.followers = followers;
            this.totalCount = totalCount;
            this.cursor = cursor;
            this.moreToFollow = moreToFollow;
        }

        public List<Follower> getFollowers() {
            return followers;
        }

        public void setFollowers(List<Follower> followers) {
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
