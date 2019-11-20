package ru.domclick.test.domclick.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountBadRequestException extends RuntimeException {

    public AccountBadRequestException(String message) {
        super(message);
    }
}
