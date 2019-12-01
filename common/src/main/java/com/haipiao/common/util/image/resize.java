package com.haipiao.common.util.image;

// Predefined some image style in aliyun oss to resize image.
public class resize {
    private String imageUrl;

    public resize(String url) {
        this.imageUrl = url;
    }

    public String resizeToLarge() {
        return this.imageUrl +"?x-oss-process=style/large";
    }

    public String resizeToMedium() {
        return this.imageUrl +"?x-oss-process=style/medium";
    }

    public String resizeToSmall() {
        return this.imageUrl +"?x-oss-process=style/small";
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
