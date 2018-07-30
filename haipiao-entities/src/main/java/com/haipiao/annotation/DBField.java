package com.haipiao.annotation;

public @interface DBField {
    String name();
    boolean nonnull() default false;
    boolean primaryKey() default false;
    boolean foreignKey() default false;
}
