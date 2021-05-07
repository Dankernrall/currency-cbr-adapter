package ru.ds.education.exercise.exceptions;

import javax.jms.JMSException;

public class ApiBadRequest extends RuntimeException {
    public ApiBadRequest(String message) throws JMSException {
        super(message);
    }
}
