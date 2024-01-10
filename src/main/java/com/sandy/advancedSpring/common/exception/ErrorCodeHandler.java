package com.sandy.advancedSpring.common.exception;

import com.sandy.advancedSpring.common.exception.error.InvalidValueException;
import com.sandy.advancedSpring.common.exception.error.NotFoundException;
import com.sandy.advancedSpring.common.exception.error.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class ErrorCodeHandler {


    // 개별 exception 설정할 때 사용
    @ExceptionHandler(InvalidValueException.class)
    private ResponseEntity<Object> handleInvalidValueException(final InvalidValueException e, final HttpServletRequest httpServletRequest) {
        ErrorResponse response = ErrorResponse.of(e.getErrorCode(), httpServletRequest.getRequestURI());
        for (StackTraceElement element : e.getStackTrace()) {
            log.error(element.toString());
        }
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<Object> handleNotFoundException(final NotFoundException e, final HttpServletRequest httpServletRequest) {
        ErrorResponse response = ErrorResponse.of(e.getErrorCode(), httpServletRequest.getRequestURI());
        for (StackTraceElement element : e.getStackTrace()) {
            log.error(element.toString());
        }
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }

    // 한번에 error 설정할 때 사용
    @ExceptionHandler(UserException.class)
    private ResponseEntity<ErrorCode> handleUserException(final UserException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.error("handleUserException... {}", errorCode);
        return new ResponseEntity<>(errorCode, errorCode.getHttpStatus());
    }


    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException e, final HttpServletRequest httpServletRequest) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.UNAUTHORIZED, httpServletRequest.getRequestURI());
        for (StackTraceElement element : e.getStackTrace()) {
            log.error(element.toString());
        }
        return new ResponseEntity<>(response, ErrorCode.UNAUTHORIZED.getHttpStatus());
    }

    @ExceptionHandler(BindException.class)
    private ResponseEntity<Object> handleBindException(final BindException e, final HttpServletRequest httpServletRequest) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID, httpServletRequest.getRequestURI(), e.getBindingResult());
        for (StackTraceElement element : e.getStackTrace()) {
            log.error(element.toString());
        }
        return new ResponseEntity<>(response, ErrorCode.INVALID.getHttpStatus());
    }


    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleException(final Exception e, final HttpServletRequest httpServletRequest) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.UNCAUGHT, httpServletRequest.getRequestURI());
        for (StackTraceElement element : e.getStackTrace()) {
            log.error(element.toString());
        }
        return new ResponseEntity<>(response, ErrorCode.UNCAUGHT.getHttpStatus());
    }

}
