package com.haipiao.articleservice.dto.resp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wangjipeng
 */
public class RecommendationArticleResponse extends AbstractResponse<RecommendationArticleResponse.Data> {

    public RecommendationArticleResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data {

        @SerializedName("articles")
        private List<ArticleData> articles;

        public static class ArticleData {

            @SerializedName("cover_image_url")
            private String coverImageUrl;

            @SerializedName("id")
            private int id;

            @SerializedName("tittle")
            private String tittle;

            @SerializedName("likes")
            private int likes;

            @SerializedName("liked")
            private String liked;

            @SerializedName("author")
            private Author author;

            public static class Author {
                @SerializedName("id")
                private int id;

                @SerializedName("name")
                private String name;

                @SerializedName("profile_image_url")
                private String profileImageUrl;

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

                public void setProfileImageUrl(String profile_image_url) {
                    this.profileImageUrl = profile_image_url;
                }
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

            public String getTittle() {
                return tittle;
            }

            public void setTittle(String tittle) {
                this.tittle = tittle;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }

            public String getLiked() {
                return liked;
            }

            public void setLiked(String liked) {
                this.liked = liked;
            }

            public Author getAuthor() {
                return author;
            }

            public void setAuthor(Author author) {
                this.author = author;
            }
        }

        public List<ArticleData> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticleData> articles) {
            this.articles = articles;
        }
    }
}
