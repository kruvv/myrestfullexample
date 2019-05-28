package ru.kruvv.myrestfull.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.kruvv.myrestfull.domain.Loan;
import ru.kruvv.myrestfull.domain.Person;

public interface LoanRepository extends CrudRepository<Loan, Integer> {
	List<Loan> findByPerson(Person person);
}
