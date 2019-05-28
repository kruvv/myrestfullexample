package ru.kruvv.myrestfull.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.kruvv.myrestfull.domain.BlackList;
import ru.kruvv.myrestfull.domain.Person;
import ru.kruvv.myrestfull.repository.BlackListRepository;
import ru.kruvv.myrestfull.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlackListServiceTest {

	@Autowired
	private PersonRepository persons;

	@Autowired
	private BlackListRepository blacklists;

	@Autowired
	private BlackListService service;

	@Test
	public void whenPersonInBlackListThenReturnTrue() {
		Person person = this.persons.save(new Person("Viktor", "Krupkin"));
		this.blacklists.save(new BlackList(person));
		boolean result = this.service.isBlackListPerson(person.getId());
		assertTrue(result);
	}

	@Test
	public void whenBlackListEmptyThenAnyPersonNotIn() {
		Person person = this.persons.save(new Person("Viktor", "Krupkin"));
		boolean result = this.service.isBlackListPerson(person.getId());
		assertTrue(result);
	}

}
