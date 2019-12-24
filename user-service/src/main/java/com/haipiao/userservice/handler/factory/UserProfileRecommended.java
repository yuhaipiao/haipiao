package com.haipiao.userservice.handler.factory;

import com.haipiao.persist.entity.User;
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
public class UserProfileRecommended implements Recommended {

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户简介推荐
     * @param user
     * @return
     */
    @Override
    public List<User> recommendedUsers(User user, RecommendationRequest request) {
        Set<String> thisSet = new HashSet<>(Arrays.asList(user.getSignature().split(RecommendedConstant.FOREIGN)));
        List<User> all = userRepository.getUserByFansCount();
        Map<String, User> userMap = all.stream().filter(Objects::nonNull).collect(Collectors.toMap(User::getSignature, u -> u));

        List<User> userList = userMap.entrySet().stream()
                .map(e -> checkSet(thisSet, e))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return userList.size() == 0 ? all : userList;
    }

    private User checkSet(Set<String> thisSet, Map.Entry<String, User> entry){
        Set<String> thatSet = new HashSet<>(Arrays.asList(entry.getKey().split(RecommendedConstant.FOREIGN)));
        Set<String> resultSet = new HashSet<>(thisSet);
        resultSet.retainAll(thatSet);
        boolean flag = SimilarityComputationUtil.calculatorResult(thisSet.size(), resultSet.size(), RecommendedConstant.USER_PROFILE_THRESHOLD);
        return flag ? entry.getValue() : null;
    }
}
