package com.haipiao.articleservice.dto.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;
import com.haipiao.persist.vo.ArticleData;

import java.util.List;

/**
 * @Author: wangshun
 */
public class ArticleResponse extends AbstractResponse<ArticleResponse.Data> {

    public ArticleResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data {
        @SerializedName("articles")
        private List<ArticleData> articles;

        @SerializedName("total_count")
        private int totalCount;

        @SerializedName("cursor")
        private String cursor;

        @SerializedName("more_to_follow")
        private boolean moreToFollow;

        public Data(List<ArticleData> articles, int totalCount, String cursor, boolean moreToFollow) {
            this.articles = articles;
            this.totalCount = totalCount;
            this.cursor = cursor;
            this.moreToFollow = moreToFollow;
        }

        public List<ArticleData> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticleData> articles) {
            this.articles = articles;
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
