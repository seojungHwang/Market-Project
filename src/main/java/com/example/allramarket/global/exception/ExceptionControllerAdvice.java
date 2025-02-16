package com.example.allramarket.global.exception;

import com.example.allramarket.global.response.CommonResponse;
import com.example.allramarket.global.response.StatusCode;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Arrays;

@Log4j2
@RestControllerAdvice(annotations = {RestController.class}, basePackages = {"com.example.allramarket"})
public class ExceptionControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public CommonResponse runTimeException(RuntimeException e) {
        log.info("Exception Message : " + e);

        return CommonResponse.error(StatusCode.SERVER_ERROR.getCode(), StatusCode.SERVER_ERROR.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse exception(Exception e) {
        log.info("Exception Message : " + e);

        return CommonResponse.error(StatusCode.SERVER_ERROR.getCode(), StatusCode.SERVER_ERROR.getStatus());
    }

    @ExceptionHandler(UnexpectedRollbackException.class)
    public CommonResponse UnexpectedRollbackException(UnexpectedRollbackException e) {
        log.info("Exception Message : " + e);

        return CommonResponse.error(StatusCode.SERVER_ERROR.getCode(), StatusCode.SERVER_ERROR.getStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    public CommonResponse nullPointTimeException(NullPointerException e) {
        log.error("Exception Message : " + e);
        return CommonResponse.error(StatusCode.NOT_FOUND.getCode(), StatusCode.NOT_FOUND.getStatus());
    }

    @ExceptionHandler(CustomException.class)
    public CommonResponse customException(CustomException e) {
        log.error("Custom Exception Message : " + e.getMessage());
        JsonNode error = e.getErr();

        return CommonResponse.success(error);
    }
}
