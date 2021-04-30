package ru.ds.education.exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ds.education.exercise.cbr.model.CurrencyModel;
import ru.ds.education.exercise.cbr.service.ServiceCbr;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
class CbrApplicationTests {

	@Autowired
	private ServiceCbr serviceCbr;

	@Test
	public void test() throws Exception{
		LocalDateTime Date = LocalDateTime.of(2021,4,30,12,0);
		List<CurrencyModel> actual = serviceCbr.cbr(Date);
		CurrencyModel expected = new CurrencyModel("AUD",57.85);
		Assertions.assertEquals(expected.getCurrencyModelValue(),actual.get(0).getCurrencyModelValue());
	}

}
