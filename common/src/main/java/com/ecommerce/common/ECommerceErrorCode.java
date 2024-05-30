package com.ecommerce.common;

public enum ECommerceErrorCode {
    FAILED_TO_SAVE_SHOPPER_DETAILS("FAILED_TO_SAVE_SHOPPER_DETAILS"),
    FAILED_TO_SAVE_PRODUCT_METADATA("FAILED_TO_SAVE_PRODUCT_METADATA"),
    FAILED_TO_GET_PRODUCTS("FAILED_TO_GET_PRODUCTS"),
    INVALID_REQUEST("INVALID_REQUEST"),
    INTERNAL_ERROR("INTERNAL_SERVER_ERROR"),
    NOT_FOUND("NOT_FOUND");
    private final String errorCode;
    ECommerceErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

