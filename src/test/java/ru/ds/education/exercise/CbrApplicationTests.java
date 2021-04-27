package ru.ds.education.exercise;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ds.education.exercise.jms.controller.JmsController;

import javax.jms.TextMessage;


@SpringBootTest
class CbrApplicationTests {

	@Autowired
	private JmsController controller;

	@Test
	public void test() throws Exception{
		TextMessage message = null;
		message.setText("{ \"onDate\" : \"2021.03.15\" }");
		//controller.getMessage(message);
		//Пока что не понял как делать тесты с JMS
	}

}
