package com.haipiao.userservice.resp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * @author wangshun
 */
public class GetGroupDto {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public GetGroupDto() {
    }

    public GetGroupDto(int id, String name) {
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
