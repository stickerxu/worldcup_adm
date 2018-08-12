package com.worldcup.adm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public String globalException(HttpServletRequest request, Exception e) {
        log.info(request.getRequestURI());
        log.error(e.getMessage(),e);
        return "error";
    }
}
