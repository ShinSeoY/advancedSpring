package com.sandy.advancedSpring.exception.error;

import com.sandy.advancedSpring.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public NotFoundException() {
        super(ErrorCode.NOT_FOUND.getCode());
        this.errorCode = ErrorCode.NOT_FOUND;
    }
}
