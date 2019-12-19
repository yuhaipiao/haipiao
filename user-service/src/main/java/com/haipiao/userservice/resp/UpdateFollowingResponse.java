package com.haipiao.userservice.resp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangjipeng
 */
public class UpdateFollowingResponse extends AbstractResponse<UpdateFollowingResponse.Data> {

    public UpdateFollowingResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data {

        @SerializedName("object")
        private Object object;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public Data() {
        }

        public Data(Object object) {
            this.object = object;
        }
    }
}
