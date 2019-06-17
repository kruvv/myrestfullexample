package ru.kruvv.myrestfull.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LimitServiceTest {

	@Autowired
	private LimitService service;

	@Test
	public void whenLimitNotExceedThenFalse() {
		boolean result = this.service.isLimit("Ru");
		assertFalse(result);
	}

	@Test
	public void whenLimitExceedThenFalse() {
		this.service.isLimit("Ru");
		boolean result = this.service.isLimit("Ru");
		assertTrue(result);
	}
}
