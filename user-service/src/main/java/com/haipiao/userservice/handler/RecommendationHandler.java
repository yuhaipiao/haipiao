package com.haipiao.userservice.handler;

import com.google.gson.annotations.SerializedName;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.req.AbstractRequest;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.entity.UserFollowingRelation;
import com.haipiao.persist.repository.*;
import com.haipiao.userservice.enums.RecommendationContextEnum;
import com.haipiao.userservice.handler.constants.LimitNumConstant;
import com.haipiao.userservice.handler.interfaces.ChooseRecommended;
import com.haipiao.userservice.req.RecommendationRequest;
import com.haipiao.userservice.resp.RecommendationResponse;
import com.haipiao.userservice.resp.dto.RecommendationInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author wangjipeng
 */
@Component
public class RecommendationHandler extends AbstractHandler<RecommendationRequest, RecommendationResponse> {

    private static final Logger LOG = LoggerFactory.getLogger(RecommendationHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChooseRecommended chooseRecommended;

    @Autowired
    private UserFollowingRelationRepository userFollowingRelationRepository;

    protected RecommendationHandler(SessionService sessionService,
                                    UserRepository userRepository,
                                    ChooseRecommended chooseRecommended,
                                    UserFollowingRelationRepository userFollowingRelationRepository) {
        super(RecommendationResponse.class, sessionService);
        this.userRepository = userRepository;
        this.chooseRecommended = chooseRecommended;
        this.userFollowingRelationRepository = userFollowingRelationRepository;
    }

    /**
     * 推荐值得关注的用户
     * - `BAD_REQUEST`: query parameter名称或组合不合法。
     * - `UNAUTHORIZED`: 用户未登录或者session token不合法。
     * - `INTERNAL_SERVER_ERROR`: 未知服务器错误。
     * @param request
     * @return
     * @throws AppException
     */
    @Override
    protected RecommendationResponse execute(RecommendationRequest request) throws AppException {
        RecommendationResponse response = new RecommendationResponse(StatusCode.SUCCESS);
        int thisUserId = request.getLoggedInUserId();
        Optional<User> optionalUser = userRepository.findById(thisUserId);
        User thisUser = null;
        if (optionalUser.isPresent()){
            thisUser = optionalUser.get();
        }

        List<User> recommendedUserList = chooseRecommended
                .chooseRecommended(request.getContext())
                .recommendedUsers(thisUser, request);

        List<RecommendationInfoDto> responseList = recommendedUserList.stream()
                .filter(Objects::nonNull)
                .map(u -> new RecommendationInfoDto(u.getUserId(), u.getRealName(), u.getProfileImgUrl(), findUserFollowee(u.getUserId()), u.getSignature()))
                .collect(Collectors.toList());

        // TODO 根据文档更新修改
        int cursor = request.getCursor();
        int limit = request.getLimit() == 0 ? LimitNumConstant.RECOMMENDATION_LIMIT : request.getLimit();
        boolean moreToFollow = false;
        if (responseList.size() > limit * cursor){
            moreToFollow = true;
            responseList = nextList(cursor, limit, responseList);
        }
        RecommendationResponse.Data data = new RecommendationResponse.Data(responseList, cursor, moreToFollow);
        response.setData(data);
        return response;
    }

    private List<RecommendationInfoDto> nextList(int cursor, int limit, List<RecommendationInfoDto> all){
        List<RecommendationInfoDto> list = new ArrayList<>();
        for (int i = (cursor - 1) * limit; i <= cursor * limit - 1; i++){
            list.add(all.get(i));
        }
        return list;
    }

    private long findUserFollowee(int userId){
        return userFollowingRelationRepository.findByUserId(userId);
    }
}
