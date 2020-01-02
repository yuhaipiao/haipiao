package com.haipiao.userservice.resp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * @author wangjipeng
 */
public class RecommendationInfoDto {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_image_url")
    private String profileImageUrl;

    @SerializedName("followers_count")
    private int followersCount;

    @SerializedName("description")
    private String description;

    public RecommendationInfoDto() {
    }

    public RecommendationInfoDto(int id, String name, String profileImageUrl, int followersCount, String description) {
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.followersCount = followersCount;
        this.description = description;
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

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
