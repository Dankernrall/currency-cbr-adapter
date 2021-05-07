package ru.ds.education.exercise.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.adapter.ListenerExecutionFailedException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import ru.ds.education.exercise.cbr.service.ServiceCbr;
import ru.ds.education.exercise.exceptions.ApiBadRequest;


import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.xml.crypto.Data;
import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

@Controller
public class JmsController {

    @Autowired
    ServiceCbr serviceCbr;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    JmsTemplate jmsTemplate;

    @Async
    @JmsListener(destination = "RU-DS-EDUCATION-CBR-REQUEST")
    public void getMessage(MapMessage message) throws JMSException, DatatypeConfigurationException, InterruptedException, ExecutionException {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            String correlationID = message.getJMSCorrelationID() == null ? message.getJMSCorrelationID() : null; //Если CorrelationID не пустой, записываем в переменную
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Форматируем дату из сообщения по паттерну
            String whatGet = message.getString("Date"); //Цепляем дату по ключу Date
            LocalDate Date = LocalDate.parse(whatGet, formatter); //Парсим дату через заранее созданый formatter
            message = serviceCbr.cbr(Date.atTime(12, 0), message).get(); //Берем дату и отправляем ее с временем 12:00, так как при 00:00 ЦБ отдает вчерашнюю валюту.
            if (correlationID != null)
                message.setJMSCorrelationID(correlationID);
            jmsTemplate.convertAndSend(message);
        }catch(JMSException | DatatypeConfigurationException | InterruptedException | ExecutionException | ListenerExecutionFailedException e){
        throw new ApiBadRequest(""); //Если получаем какую либо ошибку, тайм-аут, ошибка при считывании, то выкидываем ошибку
        }
    }
}
