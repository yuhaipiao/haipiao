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
        private List<CategoryInfoDto> categories;

        public List<CategoryInfoDto> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoryInfoDto> categories) {
            this.categories = categories;
        }
    }
}
