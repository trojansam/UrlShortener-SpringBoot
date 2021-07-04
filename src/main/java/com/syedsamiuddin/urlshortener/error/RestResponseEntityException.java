package com.syedsamiuddin.urlshortener.error;

import com.syedsamiuddin.urlshortener.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class RestResponseEntityException extends InvalidUrlException{

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ErrorMessage> invalidUrl(InvalidUrlException exception, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ErrorMessage> urlNotFound(UrlNotFoundException exception, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
