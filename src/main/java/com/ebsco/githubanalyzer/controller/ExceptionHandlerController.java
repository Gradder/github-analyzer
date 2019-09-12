package com.ebsco.githubanalyzer.controller;

import com.ebsco.githubanalyzer.model.ErrorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eclipse.egit.github.core.client.RequestException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Log4j2
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {


    @ExceptionHandler(RequestException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorDto handleGithubRequestExceptionException(RequestException ex,
                                                          HttpServletRequest request) {
        return handle(ex, request, ex.getMessage());
    }


    @ExceptionHandler(IOException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorDto handleIOException(IOException ex,
                                      HttpServletRequest request) {
        return handle(ex, request, ex.getMessage());
    }

    private ErrorDto handle(Exception exception, HttpServletRequest hsr, String message) {
        log.error("Request [{} {}] threw exception. {}",
                hsr.getMethod(), hsr.getRequestURI(), message);
        return new ErrorDto(hsr.getRequestURI(), message);
    }
}
