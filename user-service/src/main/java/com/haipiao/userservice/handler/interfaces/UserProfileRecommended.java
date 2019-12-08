package com.haipiao.userservice.handler.interfaces;

import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.UserRepository;
import com.haipiao.userservice.handler.constants.RecommendedConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public List<User> recommendedUsers(User user) {
        Set<String> thisSet = new HashSet<>(Arrays.asList(user.getSignature().split(RecommendedConstant.FOREIGN)));
        Iterable<User> all = userRepository.findAll();
        Map<String, User> userMap = StreamSupport.stream(all.spliterator(), false)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(User::getSignature, u -> u));

        List<User> userList = userMap.entrySet().stream()
                .map(e -> checkSet(thisSet, e))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        // 如果此算法没有给用户匹配到相似用户则返回所有用户
        if (userList.size() == 0){
            return StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
        }
        return userList;
    }

    private User checkSet(Set<String> thisSet, Map.Entry<String, User> entry){
        Set<String> thatSet = new HashSet<>(Arrays.asList(entry.getKey().split(RecommendedConstant.FOREIGN)));
        Set<String> resultSet = new HashSet<>(thisSet);
        resultSet.retainAll(thatSet);

        BigDecimal thisSize = new BigDecimal(thisSet.size());
        BigDecimal thatSize = new BigDecimal(resultSet.size());
        BigDecimal divide = thatSize.divide(thisSize, 1, RoundingMode.HALF_UP);
        if (divide.compareTo(new BigDecimal(RecommendedConstant.USER_PROFILE_THRESHOLD)) >= 0){
            return entry.getValue();
        }
        return null;
    }
}
