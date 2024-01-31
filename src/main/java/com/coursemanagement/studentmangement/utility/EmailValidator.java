package com.coursemanagement.studentmangement.utility;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@Log4j2
public class EmailValidator {

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        log.info("Email Validation being performed*****");
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
