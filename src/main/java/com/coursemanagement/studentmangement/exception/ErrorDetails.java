package com.coursemanagement.studentmangement.exception;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ErrorDetails {

   // private LocalDateTime timestamp;
    private String errorMessage;
    private String errorCode;

}
