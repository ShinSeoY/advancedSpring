package com.sandy.advancedSpring.exception.error;

import com.sandy.advancedSpring.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidValueException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidValueException() {
        super(ErrorCode.INVALID.getCode());
        this.errorCode = ErrorCode.INVALID;
    }
}
