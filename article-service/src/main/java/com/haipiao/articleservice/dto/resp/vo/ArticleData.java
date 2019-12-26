package com.haipiao.articleservice.dto.resp.vo;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * @Author: wangshun
 */
public class ArticleData implements Serializable {
    @SerializedName("cover_image_url")
    private String coverImageUrl;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("likes")
    private int likes;

    @SerializedName("liked")
    private boolean liked;

    @SerializedName("author")
    private Author author;

    public ArticleData(String coverImageUrl, int id, String title, int likes, boolean liked, Author author) {
        this.coverImageUrl = coverImageUrl;
        this.id = id;
        this.title = title;
        this.likes = likes;
        this.liked = liked;
        this.author = author;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
