package com.haipiao.userservice.resp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * @author wangjipeng
 */
public class GetUserAllCategoryDto {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public GetUserAllCategoryDto() {
    }

    public GetUserAllCategoryDto(int id, String name) {
        this.id = id;
        this.name = name;
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
}
