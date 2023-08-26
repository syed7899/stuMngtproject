package com.coursemanagement.studentmangement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final Logger logger= LoggerFactory.getLogger(UserNotFoundException.class);
    public UserNotFoundException(String message) {
             super(message);
        logger.info("Within UserNotFoundException Block");
    }
}
