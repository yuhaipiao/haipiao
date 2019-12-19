package com.haipiao.userservice.req;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.req.AbstractRequest;

import java.util.List;

/**
 * @author wangjipeng
 */
public class SaveUserCategoryRequest extends AbstractRequest {

    @SerializedName("categories")
    private List<Integer> categories;

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }
}
