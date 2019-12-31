package com.haipiao.articleservice.dto.resp.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wangshun
 */
public class FollowerData implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_image_url")
    private String profileImageUrl;

    @SerializedName("followersCount")
    private int followersCount;

    @SerializedName("followered")
    private Boolean followered;

    public FollowerData(int id, String name, String profileImageUrl, int followersCount, Boolean followered) {
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
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

    public Boolean getFollowered() {
        return followered;
    }

    public void setFollowered(Boolean followered) {
        this.followered = followered;
    }
}
