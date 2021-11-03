package com.melih.sinik.springbootaop.advice;

import com.melih.sinik.springbootaop.dto.ApiError;
import javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Melih ŞİNİK
 * @since 22.10.2021
 */
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({NotFoundException.class})
    public final ResponseEntity<ApiError> createApiError(NotFoundException ex) {

        return new ResponseEntity<>(new ApiError("NotFoundException", ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AuthorizationServiceException.class})
    public final ResponseEntity<ApiError> createApiError(AuthorizationServiceException ex) {

        return new ResponseEntity<>(new ApiError("Forbidden", ex.getMessage()), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}
