package com.opticortes.utils;


public enum ResponseType {
    JSON("application/json"),
    HTML("text/html");

    private final String type;

    ResponseType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}