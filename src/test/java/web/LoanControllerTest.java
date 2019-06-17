package web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.kruvv.myrestfull.domain.Country;
import ru.kruvv.myrestfull.domain.Loan;
import ru.kruvv.myrestfull.domain.Person;
import ru.kruvv.myrestfull.service.BlackListService;
import ru.kruvv.myrestfull.service.LimitService;
import ru.kruvv.myrestfull.service.LoanService;
import ru.kruvv.myrestfull.web.LoanController;
import ru.kruvv.myrestfull.web.forms.Success;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BlackListService blacklists;

	@MockBean
	private LoanService loans;

	@MockBean
	private LimitService limit;

	@Test
	public void whenPersonNotInBlackListThenApplyLoan() throws Exception {
		List<Loan> list = Collections.singletonList(new Loan("test", 1D, new Country("Russia"), new Person("Viktor", "Krupkin")));
		ObjectMapper mapper = new ObjectMapper();
		given(this.loans.getAll()).willReturn(list);
		this.mvc.perform(get("/").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andExpect(content().string(mapper.writeValueAsString(list)));
	}

	@Test
	public void whenLoadThenApplyLoan() throws Exception {
		List<Loan> list = Collections.singletonList(new Loan("test", 1D, new Country("Russia"), new Person("Viktor", "Krupkin")));
		ObjectMapper mapper = new ObjectMapper();
		given(this.loans.getByPerson(0)).willReturn(list);
		this.mvc.perform(get("/0").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andExpect(content().string(mapper.writeValueAsString(list)));
	}

	@Test
	public void whenApplyThenSave() throws Exception {
		Loan loan = new Loan("test", 1D, new Country("Russia"), new Person("Viktor", "Krupkin"));
		ObjectMapper mapper = new ObjectMapper();
		given(this.blacklists.isBlackListPerson(0)).willReturn(false);
		given(this.loans.apply(loan)).willReturn(loan);
		this.mvc.perform(post("/").contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(loan))).andExpect(status().isOk()).andExpect(content().string(mapper.writeValueAsString(new Success<>(loan))));
	}

	@Test
	public void whenInBlacklistThenError() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		given(this.blacklists.isBlackListPerson(0)).willReturn(true);
		this.mvc.perform(post("/").contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(new Loan("test", 1D, new Country("Russia"), new Person("Viktor", "Krupkin"))))).andExpect(status().isOk())
				.andExpect(content().string(mapper.writeValueAsString(new Error("User 0 in blacklist"))));
	}

}
