package com.ecommerce.service.exceptions;

import com.ecommerce.common.ECommerceErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse {
    private String message;
    private ECommerceErrorCode errorCode;
    private int httpStatusCode;

}
