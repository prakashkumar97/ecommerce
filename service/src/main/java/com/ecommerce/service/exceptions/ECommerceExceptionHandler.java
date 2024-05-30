package com.ecommerce.service.exceptions;

import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ecommerce.common.ECommerceErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class ECommerceExceptionHandler {
    @ExceptionHandler(value = ECommerceException.class)
    public ResponseEntity<ErrorResponse> handleECommerceException(ECommerceException ecomEx) {
        ErrorResponse errResponse = new ErrorResponse(ecomEx.getMessage(), ecomEx.getECommerceErrorCode(),
                                                        ecomEx.getHttpStatus().value());
        return new ResponseEntity<>(errResponse, ecomEx.getHttpStatus());
    }

    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationException(Exception e) {
        return handleECommerceException(
                new ECommerceException(ECommerceErrorCode.INVALID_REQUEST.toString(), e, ECommerceErrorCode.INVALID_REQUEST));
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        return handleECommerceException(new ECommerceException(e.getMessage(), e, ECommerceErrorCode.NOT_FOUND));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(ConstraintViolationException e) {

        return handleECommerceException(new ECommerceException(e.getMessage(), ECommerceErrorCode.INVALID_REQUEST,
                                                                HttpStatus.BAD_REQUEST, e));
    }

    @ExceptionHandler(value = JpaSystemException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleJPASystemException(JpaSystemException e) {

        return handleECommerceException(new ECommerceException(e.getMessage(), ECommerceErrorCode.INTERNAL_ERROR,
                                                               HttpStatus.INTERNAL_SERVER_ERROR, e));
    }


    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationException(HttpMessageNotReadableException e) {
        String invalidReqErrMsg = "Invalid Request";
        if (e.getMostSpecificCause() instanceof JsonProcessingException) {
            invalidReqErrMsg = ((JsonProcessingException) e.getMostSpecificCause()).getOriginalMessage();
        }
        if (e.getMostSpecificCause() instanceof IllegalArgumentException) {
            invalidReqErrMsg = e.getMostSpecificCause().getMessage();
        }
        return handleECommerceException(new ECommerceException(invalidReqErrMsg, ECommerceErrorCode.INVALID_REQUEST,
                                                             HttpStatus.BAD_REQUEST, e));
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ErrorResponse> handleException(Throwable th) {
        return handleECommerceException(
                new ECommerceException(ECommerceErrorCode.INTERNAL_ERROR.toString(), th, ECommerceErrorCode.INTERNAL_ERROR));
    }

}
