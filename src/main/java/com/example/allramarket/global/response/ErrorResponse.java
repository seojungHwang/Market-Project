package com.example.allramarket.global.response;

import lombok.Data;

@Data
public class ErrorResponse extends CommonResponse {
    String time;
    int code;
    String status;


    public ErrorResponse(String time, int code, String status) {
        this.time = time;
        this.status = status;
        this.code = code;
    }
}
