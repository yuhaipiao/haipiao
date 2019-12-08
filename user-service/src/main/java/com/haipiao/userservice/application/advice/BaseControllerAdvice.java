package com.haipiao.userservice.application.advice;

import com.haipiao.common.enums.StatusCode;
import com.haipiao.userservice.resp.InternalServerErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wangjipeng
 */
@ControllerAdvice
public class BaseControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(BaseControllerAdvice.class);

    /**
     * 对其他异常进行处理
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<InternalServerErrorResponse> processGeneralException(Throwable e){
        LOG.error(e.getMessage());
        InternalServerErrorResponse response = new InternalServerErrorResponse(StatusCode.INTERNAL_SERVER_ERROR);
        response.setErrorMessage("网络繁忙, 稍等片刻哦...");
        return new ResponseEntity<InternalServerErrorResponse>(response, HttpStatus.OK);
    }
}
