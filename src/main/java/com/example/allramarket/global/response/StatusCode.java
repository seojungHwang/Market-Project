package com.example.allramarket.global.response;

import lombok.Getter;

@Getter
public enum StatusCode {
    SERVER_ERROR(500, "INTERNAL SERVER ERROR"),
    NOT_FOUND(404, "NOT FOUND"),
    OUT_OF_STOCK(0, "재고가 없습니다."),
    OVERSTOCK(1, "수량을 초과하였습니다."),
    ;

    int code;
    String status;

    StatusCode(int code, String status) {
        this.code = code;
        this.status = status;
    }
}