package com.ecommerce.service.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ECommerceExceptionHandler {

//    @ExceptionHandler(value = ProductNotfoundException.class)
//    public ResponseEntity<Object> exception(ProductNotfoundException exception) {
//        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
//    }



}
