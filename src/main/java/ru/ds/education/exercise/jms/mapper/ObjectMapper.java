package ru.ds.education.exercise.jms.mapper;

import org.springframework.stereotype.Component;

@Component
public class ObjectMapper  extends com.fasterxml.jackson.databind.ObjectMapper {
    com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
}
