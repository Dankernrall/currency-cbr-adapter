package ru.ds.education.exercise.jms.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import ru.ds.education.exercise.cbr.model.CurrencyModel;
import ru.ds.education.exercise.cbr.service.ServiceCbr;
import ru.ds.education.exercise.jms.mapper.DefaultMapper;
import ru.ds.education.exercise.jms.model.RequestMessage;
import ru.ds.education.exercise.jms.model.ResponseMessage;

import javax.jms.JMSException;;
import javax.jms.TextMessage;
import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;

@Component
public class JmsController {

    @Autowired
    DefaultMapper defaultMapper;
    @Autowired
    ServiceCbr serviceCbr;
    @Autowired
    ObjectMapper objectMapper;


    @JmsListener(destination = "RU-DS-EDUCATION-CBR-REQUEST")
    @SendTo("RU-SD-EDUCATION-CBR-RESPONSE")
    public TextMessage getMessage(TextMessage message) throws JMSException, JsonProcessingException, DatatypeConfigurationException {
        String correlationId = message.getJMSCorrelationID(); //Вытаскиваем CorrelationID
        objectMapper.registerModule(new JavaTimeModule());
        RequestMessage requestMessage = objectMapper.readValue(message.getText(), RequestMessage.class);
        List<CurrencyModel> getCurrency = serviceCbr.cbr(requestMessage.getOnDate().atTime(12, 00)); //Берем дату и отправляем ее с временем 12:00.
        ResponseMessage responseMessage = defaultMapper.map(requestMessage, ResponseMessage.class); //  Так как ЦБ дает вчерашнюю валюту при времени 00:00.
        responseMessage.setCurrencyList(getCurrency); //добавляем все валюты, полученные от ЦБ
        String response = objectMapper.writeValueAsString(responseMessage);
        if (correlationId != null) //При наличии correlationID, устанавливем его в ответном сообщении
            message.setJMSCorrelationID(correlationId);
        message.clearBody(); //Очищаем все, чтобы мы смогли записать свою информацию
        message.setText(response); //Запись нашей информации
        return message;
    }
}
