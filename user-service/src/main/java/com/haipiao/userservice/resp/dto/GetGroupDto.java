package com.haipiao.userservice.resp.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author wangshun
 */
@Data
public class GetGroupDto {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

}
