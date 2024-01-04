package com.yonathan.carritocompras.controller;

import com.yonathan.carritocompras.dto.ErrorResponse;
import com.yonathan.carritocompras.excepciones.CarritoNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejadorExcepcionesGlobal(Exception ex) {

        ErrorResponse error = ErrorResponse.builder()
                .mensaje(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @ExceptionHandler(CarritoNullException.class)
    public ResponseEntity<ErrorResponse> registroNullException(CarritoNullException ex) {

        ErrorResponse error = ErrorResponse.builder()
                .mensaje(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
