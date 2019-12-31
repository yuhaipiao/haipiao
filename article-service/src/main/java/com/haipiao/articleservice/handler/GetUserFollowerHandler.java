package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.req.GetArticleCommentsRequest;
import com.haipiao.articleservice.dto.resp.AlbumResponse;
import com.haipiao.articleservice.dto.resp.FollowerResponse;
import com.haipiao.articleservice.dto.resp.vo.AlbumData;
import com.haipiao.articleservice.dto.resp.vo.FollowerData;
import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.Album;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wangshun
 */
@Component
public class GetUserFollowerHandler extends AbstractHandler<GetArticleCommentsRequest, FollowerResponse> {

    public static final Logger LOG = LoggerFactory.getLogger(GetUserFollowerHandler.class);

    @Autowired
    private UserRepository userRepository;

    protected GetUserFollowerHandler(SessionService sessionService,
                                     UserRepository userRepository) {
        super(FollowerResponse.class, sessionService);
        this.userRepository = userRepository;
    }

    @Override
    public FollowerResponse execute(GetArticleCommentsRequest request) throws AppException {
        Integer userId = request.getId();
        Pageable pageable = PageRequest.of(Integer.valueOf(request.getCursor()), request.getLimit());
        Page<User> userPage = userRepository.findFollowersByUserIdForPage(userId, pageable);
        List<User> users = userPage.getContent();
        if (CollectionUtils.isEmpty(users)) {
            FollowerResponse resp = new FollowerResponse(StatusCode.NOT_FOUND);
            resp.setErrorMessage(String.format("user %s not have followers", userId));
            return resp;
        }

        //TODO 粉丝数以及是否关注该粉丝
        List<FollowerData> followerList = users.stream()
                .filter(Objects::nonNull)
                .map(a -> new FollowerData(a.getUserId(),a.getUserName(),a.getProfileImgUrl(),0,false))
                .collect(Collectors.toList());

        FollowerResponse resp = new FollowerResponse(StatusCode.SUCCESS);
        resp.setData(new FollowerResponse.Data(followerList, (int) userPage.getTotalElements(),
                request.getCursor(), userPage.getTotalPages() > Integer.valueOf(request.getCursor())));
        return resp;
    }
}
