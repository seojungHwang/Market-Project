package com.example.allramarket.global.response;

import lombok.Getter;

@Getter
public enum StatusCode {
    ;

    int code;
    String status;

    StatusCode(int code, String status) {
        this.status = status;
        this.code = code;
    }
}