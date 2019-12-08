package com.haipiao.userservice.handler;

import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.Category;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.CategoryRepository;
import com.haipiao.persist.repository.UserRepository;
import com.haipiao.userservice.req.GetCategoryRequest;
import com.haipiao.userservice.resp.GetCategoryResponse;
import com.haipiao.userservice.resp.dto.CategoryInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author wangjipeng
 */
@Component
public class GetCategoryHandler extends AbstractHandler<GetCategoryRequest, GetCategoryResponse> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public GetCategoryHandler(SessionService sessionService,
                              CategoryRepository categoryRepository,
                              UserRepository userRepository) {
        super(GetCategoryResponse.class, sessionService);
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    /**
     * 结合之前用户设置的个人基本信息生成感兴趣的主题列表
     - `BAD_REQUEST`: query parameter的类型不合法。
     - `INTERNAL_SERVER_ERROR`: 未知服务器错误。
     * @param request
     * @return
     */
    @Override
    protected GetCategoryResponse execute(GetCategoryRequest request) {
        GetCategoryResponse.Data data = new GetCategoryResponse.Data();

        int userId = 0;
        User thisUser = userRepository.findById(userId).get();

        // TODO 根据type获取对应推荐主题  装配CategoryInfoDto
        String type = request.getType();
        Iterable<Category> all = categoryRepository.findAll();

        //TODO 根据当前用户 thisUser 性别、个性签名 分配对应主题(主题属性没有分配标签)
        List<CategoryInfoDto> list = StreamSupport.stream(all.spliterator(), false)
                .filter(Objects::nonNull)
                .map(c -> new CategoryInfoDto(c.getCategoryId(), c.getCategoryName(), ""))
                .collect(Collectors.toList());
        data.setList(list);
        GetCategoryResponse response = new GetCategoryResponse(StatusCode.SUCCESS);
        response.setData(data);
        return response;
    }
}
