package com.haipiao.articleservice.dto.resp.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Author: wangshun
 */
public class AlbumData implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("cover_image_urls")
    private String coverImageUrls;

    @SerializedName("articles_count")
    private int articlesCount;

    @SerializedName("followers_count")
    private int followersCount;

    public AlbumData(int id, String title, String coverImageUrls, int articlesCount, int followersCount) {
        this.id = id;
        this.title = title;
        this.coverImageUrls = coverImageUrls;
        this.articlesCount = articlesCount;
        this.followersCount = followersCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImageUrls() {
        return coverImageUrls;
    }

    public void setCoverImageUrls(String coverImageUrls) {
        this.coverImageUrls = coverImageUrls;
    }

    public int getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(int articlesCount) {
        this.articlesCount = articlesCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }
}
