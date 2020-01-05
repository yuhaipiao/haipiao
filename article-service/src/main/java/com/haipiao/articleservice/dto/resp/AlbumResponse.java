package com.haipiao.articleservice.dto.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;
import com.haipiao.persist.vo.AlbumData;

import java.util.List;

/**
 * @Author: wangshun
 */
public class AlbumResponse extends AbstractResponse<AlbumResponse.Data> {

    public AlbumResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data {
        @SerializedName("albums")
        private List<AlbumData> albums;

        @SerializedName("total_count")
        private int totalCount;

        @SerializedName("cursor")
        private String cursor;

        @SerializedName("more_to_follow")
        private boolean moreToFollow;

        public Data(List<AlbumData> albums, int totalCount, String cursor, boolean moreToFollow) {
            this.albums = albums;
            this.totalCount = totalCount;
            this.cursor = cursor;
            this.moreToFollow = moreToFollow;
        }

        public List<AlbumData> getAlbums() {
            return albums;
        }

        public void setAlbums(List<AlbumData> albums) {
            this.albums = albums;
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
