package com.haipiao.userservice.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;
import com.haipiao.userservice.resp.dto.CategoryInfoDto;

import java.util.List;

public class GetCategoryResponse extends AbstractResponse<GetCategoryResponse.Data> {

    public GetCategoryResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data {

        @SerializedName("categories")
        private List<CategoryInfoDto> list;

        public List<CategoryInfoDto> getList() {
            return list;
        }

        public void setList(List<CategoryInfoDto> list) {
            this.list = list;
        }
    }
}
