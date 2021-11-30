package com.opticortes.utils;

public class OkResponse extends BasicResponse{
    public OkResponse(int code, String message) {
        super(code, message, "OK");
    }
}
