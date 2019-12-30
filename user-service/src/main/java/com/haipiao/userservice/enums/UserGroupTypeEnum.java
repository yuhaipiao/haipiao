package com.haipiao.userservice.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wangshun
 */
public enum UserGroupTypeEnum {

    CUSTOM("custom"),
    DEFAULT("default"),
    MISC("misc"),
    HOT("hot"),
    ALL("all"),
    ;
    private String value;

    UserGroupTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static boolean checkType(String type){
        UserGroupTypeEnum[] values = UserGroupTypeEnum.values();
        List<String> collect = Arrays.stream(values).map(UserGroupTypeEnum::getValue).collect(Collectors.toList());
        return collect.contains(type);
    }
}
