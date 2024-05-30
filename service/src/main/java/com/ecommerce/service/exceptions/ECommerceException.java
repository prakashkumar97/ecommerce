package com.ecommerce.service.exceptions;

import org.springframework.http.HttpStatus;

import com.ecommerce.common.ECommerceErrorCode;

import lombok.Getter;

@Getter
public class ECommerceException extends RuntimeException {

    private HttpStatus httpStatus;
    private ECommerceErrorCode eCommerceErrorCode;
    public ECommerceException(String msg, Throwable cause, ECommerceErrorCode eCommerceErrorCode) {
        super(msg, cause);
        this.eCommerceErrorCode = eCommerceErrorCode;
    }

    public ECommerceException(String message, ECommerceErrorCode eCommerceErrorCode,
                              HttpStatus httpStatus, Throwable cause) {

        super(message, cause);
        this.httpStatus = httpStatus;
        this.eCommerceErrorCode = eCommerceErrorCode;

    }
}
