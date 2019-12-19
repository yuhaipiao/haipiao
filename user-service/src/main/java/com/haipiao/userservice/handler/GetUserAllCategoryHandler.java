package com.haipiao.userservice.handler;

import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.userservice.req.GetUserAllCategoryRequest;
import com.haipiao.userservice.resp.GetUserAllCategoryResponse;
import org.springframework.stereotype.Component;

/**
 * @author wangjipeng
 */
@Component
public class GetUserAllCategoryHandler extends AbstractHandler<GetUserAllCategoryRequest, GetUserAllCategoryResponse> {

    protected GetUserAllCategoryHandler(SessionService sessionService) {
        super(GetUserAllCategoryResponse.class, sessionService);
    }

    @Override
    protected GetUserAllCategoryResponse execute(GetUserAllCategoryRequest request) throws AppException {
        // 当前被选中用户id
        Integer id = request.getId();
        // 当前被选中用户的分类、当前热门的分类、其他分类


        return null;
    }
}
