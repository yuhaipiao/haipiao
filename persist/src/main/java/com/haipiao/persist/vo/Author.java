package com.haipiao.persist.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Author: wangshun
 */
public class Author implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_image_url")
    private String profileImageUrl;

    public Author(int id, String name, String profileImageUrl) {
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
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
}
