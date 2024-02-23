package com.dodo.bootpractice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ControllerAdvice 적용
 */
@RestControllerAdvice
@Slf4j
public class ExceptionController {
    /**
     * 어플리케이션에서 발생한 모든 예외를 모아서 처리
     */

    @ExceptionHandler({CustomMemberException.class})
    public ResponseEntity<String> memberException(CustomMemberException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Error", "CustomMemberException [ControllerAdvice]");

        log.info("CustomMemberException Exception 발생 [ControllerAdvice] : ", ex);

        return new ResponseEntity<>(ex.toString() + " [ControllerAdvice]", headers, HttpStatus.BAD_REQUEST);
    }
}
