package com.mongo.impl;

public enum JsonConstant {

    EMPTY_STRING(""),
    DOT_OPERATOR(".");

    JsonConstant(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }
}
