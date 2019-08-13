package com.example.check.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UsernameAlreadyExistsException extends RuntimeException {


    public UsernameAlreadyExistsException(String exception) {

        super(exception);
    }
}
