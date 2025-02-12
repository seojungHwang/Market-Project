package com.example.allramarket.global.response;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CommonResponse {

    public static <T> SuccessResponse<T> success( T data) {
        String now = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return new SuccessResponse(now, 200, "OK", data);
    }


    public static ErrorResponse error(int status, String code){
        String now = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return new ErrorResponse(now, status, code);
    }
}
