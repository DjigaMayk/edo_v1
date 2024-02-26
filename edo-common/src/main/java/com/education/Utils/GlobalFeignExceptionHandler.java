package com.education.Utils;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Класс для обработки исключений
 */
@Slf4j
@RestControllerAdvice
public class GlobalFeignExceptionHandler {

    /**
     * Метод для перехвата и обработки исключений типа FeignException и создания корректного ответа
     * @param ex - отлавливаемое исключение
     * @return возвращает корректный ответ на основе исключения
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(ex.status()));
    }

}
