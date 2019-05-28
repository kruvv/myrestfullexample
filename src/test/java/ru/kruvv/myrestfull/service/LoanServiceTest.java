package ru.kruvv.myrestfull.service;

//import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.kruvv.myrestfull.domain.Country;
import ru.kruvv.myrestfull.domain.Loan;
import ru.kruvv.myrestfull.domain.Person;
import ru.kruvv.myrestfull.repository.CountryRepository;
import ru.kruvv.myrestfull.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanServiceTest {

	@Autowired
	private PersonRepository persons;

	@Autowired
	private CountryRepository countries;

	@Autowired
	private LoanService service;

	@Test
	public void whenApplyLoadThenSaveInDb() {
		Person person = this.persons.save(new Person("Viktor", "Krupkin"));
		Country country = this.countries.save(new Country("Russia"));
		Loan loan = this.service.apply(new Loan("", 0D, country, person));
		List<Loan> result = this.service.getAll();
		assertTrue(result.contains(loan));
	}

	@Test
	public void whenFindByPersonThenReturnListOnlyForRerson() {
		Person person = this.persons.save(new Person("Viktor", "Krupkin"));
		Country country = this.countries.save(new Country("Russia"));
		Loan loan = this.service.apply(new Loan("", 0D, country, person));
		List<Loan> result = this.service.getByPerson(person.getId());
		assertThat(result.iterator().next(), is(loan));
	}
}
