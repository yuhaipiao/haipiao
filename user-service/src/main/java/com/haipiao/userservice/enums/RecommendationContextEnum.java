package com.haipiao.userservice.enums;


import java.util.Arrays;

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

    public static RecommendationContextEnum findByValue(String code) {
        return Arrays.stream(RecommendationContextEnum.values())
                .filter(re -> code.equalsIgnoreCase(re.getValue()))
                .findFirst()
                .orElse(null);
    }
}
