package com.haipiao.userservice.enums;

import java.util.Arrays;

/**
 * @author wangjipeng
 */
public enum GetCategoryEnum {

    /**
     * 获取分类映射分类TYpe
     */
    ALL("all", 1),
    HOT("hot", 2),
    MISC("misc", 3);

    private String value;

    private int type;

    GetCategoryEnum(String value, int type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    public static GetCategoryEnum findByValue(String value){
        GetCategoryEnum[] values = GetCategoryEnum.values();
        return Arrays.stream(values)
                .filter(v -> v.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
