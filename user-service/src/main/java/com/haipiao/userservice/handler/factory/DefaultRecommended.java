package com.haipiao.userservice.handler.factory;

import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.UserCategoryRelationRepository;
import com.haipiao.persist.repository.UserRepository;
import com.haipiao.userservice.handler.constants.RecommendedConstant;
import com.haipiao.userservice.handler.utils.SimilarityComputationUtil;
import com.haipiao.userservice.req.RecommendationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangjipeng
 */
@Component
public class DefaultRecommended implements Recommended{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCategoryRelationRepository userCategoryRelationRepository;

    /**
     * 默认推荐 根据用户感兴趣分类匹配
     * @param user
     * @return
     */
    @Override
    public List<User> recommendedUsers(User user, RecommendationRequest request) {
        List<Integer> thisCategoryIds = userCategoryRelationRepository.findByUserId(user.getUserId());
        List<User> otherUsers = userRepository.getUserByFansCount();

        Map<List<Integer>, User> otherMap = otherUsers.stream()
                .collect(Collectors.toMap(f -> userCategoryRelationRepository.findByUserId(f.getUserId()), f -> f));

        List<User> userList = otherMap.entrySet().stream()
                .map(f -> checkSet(thisCategoryIds, f))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return userList.size() == 0 ? otherUsers : userList;
    }

    private User checkSet(List<Integer> thisList, Map.Entry<List<Integer>, User> otherEntry){
        Set<Integer> otherSet = new HashSet<>(otherEntry.getKey());
        Set<Integer> resultSet = new HashSet<>(thisList);
        resultSet.retainAll(otherSet);
        boolean flag =  SimilarityComputationUtil.calculatorResult(thisList.size(), resultSet.size(), RecommendedConstant.USER_CATEGORY_THRESHOLD);
        return flag ? otherEntry.getValue() : null;
    }
}
