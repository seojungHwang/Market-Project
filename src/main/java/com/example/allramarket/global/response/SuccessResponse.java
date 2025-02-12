package com.example.allramarket.global.response;

import lombok.Data;

@Data
public class SuccessResponse<T> extends CommonResponse {

    private String time;
    private int code;
    private String status;
    private T data;

    public SuccessResponse(String time, int code, String status, T data) {
        this.time = time;
        this.status = status;
        this.code = code;
        this.data = data;
    }
}

