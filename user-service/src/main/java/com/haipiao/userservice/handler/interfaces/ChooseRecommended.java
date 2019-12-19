package com.haipiao.userservice.handler.interfaces;

import com.haipiao.userservice.enums.RecommendationContextEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjipeng
 */
@Component
public class ChooseRecommended {

    @Autowired
    private ArticleRecommended articleRecommended;

    @Autowired
    private DefaultRecommended defaultRecommended;

    @Autowired
    private UserProfileRecommended userProfileRecommended;

    public Recommended chooseRecommended(String context){
        Map<String, Recommended> map = new HashMap<>(8);
        map.put(RecommendationContextEnum.DEFAULT.getValue(), defaultRecommended);
        map.put(RecommendationContextEnum.ARTICLE.getValue(), articleRecommended);
        map.put(RecommendationContextEnum.USER_PROFILE.getValue(), userProfileRecommended);
        return map.get(context);
    }
}
