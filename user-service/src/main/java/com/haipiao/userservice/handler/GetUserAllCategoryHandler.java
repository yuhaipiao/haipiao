package com.haipiao.userservice.handler;

import com.haipiao.common.enums.StatusCode;
import com.haipiao.common.exception.AppException;
import com.haipiao.common.handler.AbstractHandler;
import com.haipiao.common.service.SessionService;
import com.haipiao.persist.entity.Category;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.CategoryRepository;
import com.haipiao.persist.repository.UserCategoryRelationRepository;
import com.haipiao.persist.repository.UserRepository;
import com.haipiao.userservice.enums.GetCategoryEnum;
import com.haipiao.userservice.req.GetUserAllCategoryRequest;
import com.haipiao.userservice.resp.GetUserAllCategoryResponse;
import com.haipiao.userservice.resp.dto.GetUserAllCategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author wangjipeng
 */
@Component
public class GetUserAllCategoryHandler extends AbstractHandler<GetUserAllCategoryRequest, GetUserAllCategoryResponse> {

    public static final Logger LOG = LoggerFactory.getLogger(GetUserAllCategoryHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserCategoryRelationRepository userCategoryRelationRepository;

    protected GetUserAllCategoryHandler(SessionService sessionService, UserRepository userRepository,
                                        CategoryRepository categoryRepository, UserCategoryRelationRepository userCategoryRelationRepository) {
        super(GetUserAllCategoryResponse.class, sessionService);
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.userCategoryRelationRepository = userCategoryRelationRepository;
    }

    @Override
    protected GetUserAllCategoryResponse execute(GetUserAllCategoryRequest request) throws AppException {
        GetUserAllCategoryResponse response = new GetUserAllCategoryResponse(StatusCode.SUCCESS);

        Integer chooseId = request.getId();
        Optional<User> chooseUser = userRepository.findById(chooseId);
        if (chooseUser.isEmpty()){
            response = new GetUserAllCategoryResponse(StatusCode.BAD_REQUEST);
            String errorMessage = String.format("%s 当前选中用户不存在...", chooseId);
            LOG.info(errorMessage);
            response.setErrorMessage(errorMessage);
            return response;
        }

        GetUserAllCategoryResponse.Data data = new GetUserAllCategoryResponse.Data();
        List<Integer> categoryIds = userCategoryRelationRepository.findByUserId(chooseId);
        if (categoryIds.size() > 0){
            Iterable<Category> allById = categoryRepository.findAllById(categoryIds);
            List<GetUserAllCategoryDto> user = StreamSupport.stream(allById.spliterator(), false)
                    .filter(Objects::nonNull)
                    .map(a -> new GetUserAllCategoryDto(a.getCategoryId(), a.getCategoryName()))
                    .collect(Collectors.toList());
            data.setUser(user);
        }

        Iterable<Category> all = categoryRepository.findAll();
        data.setHot(assemblerDto(all, GetCategoryEnum.HOT.getValue()));
        data.setOthers(assemblerDto(all, GetCategoryEnum.MISC.getValue()));
        response.setData(data);
        return response;
    }

    private List<GetUserAllCategoryDto> assemblerDto(Iterable<Category> all, String type){
        return StreamSupport.stream(all.spliterator(), false)
                .filter(a -> a.getType().equalsIgnoreCase(type))
                .map(a -> new GetUserAllCategoryDto(a.getCategoryId(), a.getCategoryName()))
                .collect(Collectors.toList());
    }
}
