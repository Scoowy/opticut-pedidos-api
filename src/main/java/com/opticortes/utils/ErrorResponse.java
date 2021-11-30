package com.opticortes.utils;

public class ErrorResponse extends BasicResponse {
    public ErrorResponse(int code, String message) {
        super(code, message, "ERROR");
    }
}