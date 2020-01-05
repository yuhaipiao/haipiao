package com.haipiao.articleservice.enums;

import java.util.Arrays;

/**
 * @author wangjipeng
 */
public enum ArticleStatusEnum {

    /**
     * 文章状态
     */
    PENDING(0, "pending"),
    PUBLISHED(1, "published"),
    INACTIVE(2, "inactive"),
    DELETED(3, "deleted");

    private int status;

    private String value;

    ArticleStatusEnum(int status, String value) {
        this.status = status;
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }

    public static ArticleStatusEnum getEnumByValue(String value){
        return Arrays.stream(ArticleStatusEnum.values())
                .filter(a -> a.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }

    public static ArticleStatusEnum getEnumByValue(int status){
        return Arrays.stream(ArticleStatusEnum.values())
                .filter(a -> a.getStatus() == status)
                .findFirst()
                .orElse(null);
    }
}
