package com.coursemanagement.studentmangement.exception;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
public class StudentNotFoundException extends RuntimeException{

    private static final Logger logger= LoggerFactory.getLogger(StudentNotFoundException.class);

    private String errorCode;

    public StudentNotFoundException(String message,String errorCode){
        super(message);
        this.errorCode=errorCode;
        logger.info("Within StudentNotFoundException Block");
    }


}
