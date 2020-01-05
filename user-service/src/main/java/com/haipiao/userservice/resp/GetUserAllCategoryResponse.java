package com.haipiao.userservice.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;
import com.haipiao.userservice.resp.dto.GetUserAllCategoryDto;

import java.util.List;

/**
 * @author wangjipeng
 */
public class GetUserAllCategoryResponse extends AbstractResponse<GetUserAllCategoryResponse.Data> {

    public GetUserAllCategoryResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data{

        @SerializedName("user")
        private List<GetUserAllCategoryDto> user;

        @SerializedName("hot")
        private List<GetUserAllCategoryDto> hot;

        @SerializedName("others")
        private List<GetUserAllCategoryDto> others;

        public Data() {
        }

        public Data(List<GetUserAllCategoryDto> user, List<GetUserAllCategoryDto> hot, List<GetUserAllCategoryDto> others) {
            this.user = user;
            this.hot = hot;
            this.others = others;
        }

        public List<GetUserAllCategoryDto> getUser() {
            return user;
        }

        public void setUser(List<GetUserAllCategoryDto> user) {
            this.user = user;
        }

        public List<GetUserAllCategoryDto> getHot() {
            return hot;
        }

        public void setHot(List<GetUserAllCategoryDto> hot) {
            this.hot = hot;
        }

        public List<GetUserAllCategoryDto> getOthers() {
            return others;
        }

        public void setOthers(List<GetUserAllCategoryDto> others) {
            this.others = others;
        }
    }
}
