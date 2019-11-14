package com.haipiao.imageservice.dto;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.resp.AbstractResponse;

public class GetSecurityTokenResponse extends AbstractResponse<GetSecurityTokenResponse.Data> {
    public GetSecurityTokenResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public static class Data {
        @SerializedName("endpoint")
        private String endpoint;

        @SerializedName("access_key_id")
        private String accessKeyID;

        @SerializedName("access_key_secret")
        private String accessKeySecret;

        @SerializedName("security_token")
        private String securityToken;

        @SerializedName("expire_time")
        private String expireTime;

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getAccessKeyID() {
            return accessKeyID;
        }

        public void setAccessKeyID(String accessKeyID) {
            this.accessKeyID = accessKeyID;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }

        public String getSecurityToken() {
            return securityToken;
        }

        public void setSecurityToken(String securityToken) {
            this.securityToken = securityToken;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }
    }
}
