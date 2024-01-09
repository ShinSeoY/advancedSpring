package com.sandy.advancedSpring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;

    private String path;

    private String code;

    private String message;

    private List<FieldError> errors;


    public ErrorResponse(final ErrorCode code) {
        this.timestamp = LocalDateTime.now();
        this.message = code.getMessage();
        this.code = code.getCode();
    }

    public ErrorResponse(final ErrorCode code, final String path) {
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.message = code.getMessage();
        this.code = code.getCode();
    }

    public ErrorResponse(final ErrorCode code, final String path, final List<FieldError> errors) {
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.message = code.getMessage();
        this.code = code.getCode();
        this.errors = errors;
    }

    public static ErrorResponse of(final ErrorCode code, final String path) {
        return new ErrorResponse(code, path);
    }

    public static ErrorResponse of(final ErrorCode code, final String path, final BindingResult e) {
        return new ErrorResponse(code, path, FieldError.of(e));
    }

    @AllArgsConstructor
    public static class FieldError {
        private final String field;
        private final String value;
        private final String reason;

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream().map(error -> new FieldError(
                    error.getField(),
                    error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                    error.getDefaultMessage()
            )).collect(Collectors.toList());
        }
    }


}
