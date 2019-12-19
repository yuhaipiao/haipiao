package com.haipiao.common.enums;

public enum StatusCode {

    /**
     * 响应状态码
     */
    SUCCESS("success"),
    BAD_REQUEST("bad request body"),
    UNAUTHORIZED("unauthorized request"),
    FORBIDDEN("forbidden"),
    INTERNAL_SERVER_ERROR("internal server error"),
    THROTTLED("request throttled"),
    NOT_FOUND("entity requested not found"),

    /**
     * Article Codes
     * @param defaultMessage
     */
    THIS_ARTICLE_IS_NOT_EXIST("the current article no longer exists");

    StatusCode(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    private String defaultMessage;

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
