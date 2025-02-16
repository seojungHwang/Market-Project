package com.example.allramarket.global.exception;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomException extends RuntimeException{
    private final JsonNode err;

    public JsonNode getErr() {
        return err;
    }
}
