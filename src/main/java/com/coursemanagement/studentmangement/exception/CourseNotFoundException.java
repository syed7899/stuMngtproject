package com.coursemanagement.studentmangement.exception;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
public class CourseNotFoundException extends RuntimeException{

    private static final Logger logger= LoggerFactory.getLogger(CourseNotFoundException.class);
    private String errorCode;


    public CourseNotFoundException(String message,String errorCode){
        super(message);
        this.errorCode=errorCode;
        logger.info("Within CourseNotFoundException Block");
    }


}
