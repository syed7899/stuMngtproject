package com.coursemanagement.studentmangement.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    private static final Logger logger= LoggerFactory.getLogger(CustomisedResponseEntityExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleALLExceptions(Exception ex, WebRequest request) throws Exception {
        logger.error("Within handleAllExceptions ");
        return new ResponseEntity<>(new ErrorDetails().builder()
                .errorMessage(ex.getMessage())
                .errorCode(ex.getMessage())
                .build(),HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(InstructorNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleInstructorNotFoundExceptionExceptions(InstructorNotFoundException exception)  {
        logger.error("Within handleUserNotFoundExceptionExceptions ");
               return new ResponseEntity<>(new ErrorDetails().builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(),HttpStatus.NOT_FOUND);
              }

/*  ?? Look into this later on priority
    protected ResponseEntity<ErrorDetails> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

//        ErrorDetails errordetails= new ErrorDetails(LocalDateTime.now()," "+ex.getErrorCount()+"" +ex.getFieldError().getDefaultMessage(),request.getDescription(false));
  //      return new ResponseEntity(errordetails, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ErrorDetails().builder()
                .errorMessage(ex.getMessage())
                .errorCode(ex.getMessage())
                .build(),HttpStatus.BAD_REQUEST);
    }


} */

    @ExceptionHandler(StudentNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleStudentNotFoundExceptionExceptions(StudentNotFoundException exception) throws Exception {
        logger.error("Within handleStudentNotFoundException ");
      //  ErrorDetails errordetails= new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
        //StudentManagementHandler smh= new StudentManagementHandler();
        //logger.error("***************$$$$$$$$$$$"+ex.getMessage());
        //return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new ErrorDetails().builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(),HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(CourseNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleCourseNotFoundExceptionExceptions(CourseNotFoundException exception) {
        logger.error("Within handleCourseNotFoundException ");
        //return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ErrorDetails().builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(),HttpStatus.NOT_FOUND);

    }
}