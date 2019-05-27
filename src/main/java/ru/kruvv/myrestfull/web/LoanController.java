package ru.kruvv.myrestfull.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.kruvv.myrestfull.domain.Loan;
import ru.kruvv.myrestfull.service.BlackListService;
import ru.kruvv.myrestfull.service.LoanService;
import ru.kruvv.myrestfull.web.forms.Error;
import ru.kruvv.myrestfull.web.forms.Result;
import ru.kruvv.myrestfull.web.forms.Success;

public class LoanController {
	private final LoanService loans;

	private final BlackListService blacklists;

	@Autowired
	public LoanController(LoanService loans, BlackListService blacklists) {
		this.loans = loans;
		this.blacklists = blacklists;
	}

	@PostMapping("/")
	public Result aplly(@RequestBody Loan loan) {
		final Result result;
		if (!this.blacklists.isBlackListPerson(loan.getPerson().getId())) {
			result = new Success<Loan>(this.loans.apply(loan));
		} else {
			result = new Error(String.format("User %s in blacklist", loan.getPerson().getId()));
		}
		return result;
	}

	@GetMapping("/")
	public List<Loan> getAll() {
		return this.loans.getAll();
	}

	@GetMapping("/{personId}")
	public List<Loan> findByPersonId(@PathVariable int personId) {
		return this.loans.getByPersonId(personId);
	}

}
