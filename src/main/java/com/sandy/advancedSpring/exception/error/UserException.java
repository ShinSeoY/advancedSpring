package com.sandy.advancedSpring.exception.error;

import com.sandy.advancedSpring.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserException extends RuntimeException {

    private final ErrorCode errorCode;

}
