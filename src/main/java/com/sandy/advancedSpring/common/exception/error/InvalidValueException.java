package com.sandy.advancedSpring.common.exception.error;

import com.sandy.advancedSpring.common.exception.ErrorCode;
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
