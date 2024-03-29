package com.sandy.advancedSpring.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID("M001", "invalid", HttpStatus.BAD_REQUEST),
    NOT_FOUND("M002", "not-found", HttpStatus.NOT_FOUND),
    LOGIN_NOT_MATCHED("M003", "not-found", HttpStatus.NOT_FOUND),
    UNAUTHORIZED("M001", "unauthorized", HttpStatus.UNAUTHORIZED),
    UNCAUGHT("M001", "uncaught-error", HttpStatus.INTERNAL_SERVER_ERROR),
    DUPLICATION("M001", "duplicated", HttpStatus.BAD_REQUEST),
    ALREADY_DELETED("M001", "already-deleted", HttpStatus.BAD_REQUEST),
    NOT_FOUND_ROLE("M001", "not-found-role", HttpStatus.BAD_REQUEST),
    DUPLICATION_NICK("M001", "duplication-nick", HttpStatus.OK),
    DUPLICATION_USER("M001", "duplication-user", HttpStatus.BAD_REQUEST),
    NO_MATCH_PASSWORD("M001", "no-match-password", HttpStatus.BAD_REQUEST),
    SAME_PASSWORD("M001", "same-password", HttpStatus.BAD_REQUEST);

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;

}
