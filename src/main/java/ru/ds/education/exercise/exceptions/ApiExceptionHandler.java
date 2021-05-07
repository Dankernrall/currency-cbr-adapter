package ru.ds.education.exercise.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {
    @Autowired
    JmsTemplate jmsTemplate;

    @ExceptionHandler(value = {ApiBadRequest.class})
    public ResponseEntity<Object> handleBadRequest(ApiBadRequest e){
        jmsTemplate.convertAndSend("Error");
        return new ResponseEntity<>("", HttpStatus.BAD_GATEWAY);
    }

}
