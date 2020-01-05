package com.haipiao.articleservice.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wangjipeng
 */
public enum RecommendationArticleEnum {

    /**
     * 根据场景推荐文章
     */
    DISCOVER("discover", "发现(首页)"),
    NEARBY("nearby", "附近"),
    ARTICLE_RELATED("article_related", "文章相关"),
    TOPIC_RELATED("topic_related", "话题相关"),
    TOPIC_RELATED_LATEST("topic_related_latest", "话题相关最新");

    private String value;

    private String name;

    RecommendationArticleEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static boolean checkRecommendationArticleContext(String context){
        RecommendationArticleEnum[] values = RecommendationArticleEnum.values();
        List<String> valueList = Arrays.stream(values).map(v -> v.value).collect(Collectors.toList());
        return valueList.contains(context);
    }

    public static String getNameByValue(String value){
        RecommendationArticleEnum[] values = RecommendationArticleEnum.values();
        RecommendationArticleEnum articleEnum = Arrays.stream(values)
                .filter(v -> v.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
        return articleEnum == null ? null : articleEnum.name;
    }
}
