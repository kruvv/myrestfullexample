package ru.kruvv.myrestfull.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import ru.kruvv.myrestfull.domain.Loan;
import ru.kruvv.myrestfull.domain.Person;
import ru.kruvv.myrestfull.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

	private final LoanRepository repository;

	@Autowired
	public LoanServiceImpl(LoanRepository repository) {
		this.repository = repository;
	}

	@Override
	public Loan apply(Loan loan) {
		return this.repository.save(loan);
	}

	@Override
	public List<Loan> getAll() {
		return Lists.newArrayList(this.repository.findAll());
	}

	@Override
	public List<Loan> getByPerson(int personId) {
		return this.repository.findByPerson(new Person(personId));
	}

}
