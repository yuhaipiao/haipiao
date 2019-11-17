package com.haipiao.articleservice.dto.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.articleservice.dto.common.Tag;
import com.haipiao.articleservice.dto.common.Topic;
import com.haipiao.common.req.AbstractRequest;

import java.util.Arrays;

public class CreateArticleRequest extends AbstractRequest {
    @SerializedName("title")
    private String title;

    @SerializedName("text")
    private String text;

    @SerializedName("images")
    private Image[] images;

    @SerializedName("video_urls")
    private String[] videoUrls;

    @SerializedName("topics")
    private Topic[] topics;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public String[] getVideoUrls() { return videoUrls; }

    public void setVideoUrls(String[] videoUrls) { this.videoUrls = videoUrls; }

    public Topic[] getTopics() {
        return topics;
    }

    public void setTopics(Topic[] topics) {
        this.topics = topics;
    }

    public static class Image {
        @SerializedName("external_url")
        private String externalUrl;

        @SerializedName("tags")
        private Tag[] tags;

        @SerializedName("hash_digest")
        private byte[] hashDigest;

        public String getExternalUrl() {
            return externalUrl;
        }

        public void setExternalUrl(String externalUrl) {
            this.externalUrl = externalUrl;
        }

        public Tag[] getTags() {
            return tags;
        }

        public void setTags(Tag[] tags) {
            this.tags = tags;
        }

        public byte[] getHashDigest() {
            return hashDigest;
        }

        public void setHashDigest(byte[] hashDigest) {
            this.hashDigest = hashDigest;
        }

        @Override
        public String toString() {
            return "Image{" +
                    "externalUrl='" + externalUrl + '\'' +
                    ", tags=" + Arrays.toString(tags) +
                    ", hashDigest=" + Arrays.toString(hashDigest) +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CreateArticleRequest{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", images=" + Arrays.toString(images) +
                ", videoUrls=" + Arrays.toString(videoUrls) +
                ", topics=" + Arrays.toString(topics) +
                '}';
    }
}
