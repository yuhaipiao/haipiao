package com.haipiao.userservice.enums;

/**
 * @author wangjipeng
 */
public enum RecommendationContextEnum {

    /**
     * 推荐用户场景
     */
    DEFAULT("default"),
    ARTICLE("article"),
    USER_PROFILE("user_profile");

    RecommendationContextEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
