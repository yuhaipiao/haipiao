package com.haipiao.userservice.resp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * @author wangjipeng
 */
public class CategoryInfoDto {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("cover_image_url")
    private String coverImageUrl;

    public CategoryInfoDto() {
    }

    public CategoryInfoDto(Integer id, String name, String coverImageUrl) {
        this.id = id;
        this.name = name;
        this.coverImageUrl = coverImageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
