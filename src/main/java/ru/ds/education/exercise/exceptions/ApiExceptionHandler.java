package ru.ds.education.exercise.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.jms.JMSException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleJsonProccesingError(JsonProcessingException e) {
        ApiException apiException = new ApiException(
                "Ошибка при сериализации/десериализации!",
                ZonedDateTime.now(ZoneId.of("Europe/Astrakhan"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleJmsError(JMSException e) {
        ApiException apiException = new ApiException(
                "Ошибка при работе с TextMessage!",
                ZonedDateTime.now(ZoneId.of("Europe/Astrakhan"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleDatatypeConfigurationError(DatatypeConfigurationException e) {
        ApiException apiException = new ApiException(
                "Ошибка при конвертации даты в Gregorian Calendar!",
                ZonedDateTime.now(ZoneId.of("Europe/Astrakhan"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_GATEWAY);
    }
}
