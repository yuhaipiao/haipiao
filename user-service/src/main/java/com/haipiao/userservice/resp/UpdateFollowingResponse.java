package com.haipiao.userservice.resp;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;

/**
 * @author wangjipeng
 */
public class UpdateFollowingResponse extends AbstractResponse<UpdateFollowingResponse.Data> {

    public UpdateFollowingResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data {

        @SerializedName("object")
        private Object updated;

        public Data() {
        }

        public Data(Object updated) {
            this.updated = updated;
        }

        public Object getUpdated() {
            return updated;
        }

        public void setUpdated(Object updated) {
            this.updated = updated;
        }
    }
}
