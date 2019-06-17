package ru.kruvv.myrestfull.web;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.kruvv.myrestfull.service.LimitService;

@Component
public class LimitFilter implements Filter {

	private final LimitService service;
	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public LimitFilter(LimitService service) {
		this.service = service;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		This is method works automatic
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Locale locale = request.getLocale();
		if (!this.service.isLimit(locale != null ? locale.getCountry() : "lv")) {
			chain.doFilter(request, response);
		} else {
			response.getOutputStream().write(mapper.writeValueAsBytes(new Error("Exceed execute from this country")));
		}
	}

	@Override
	public void destroy() {
//This is method works automatic
	}

}
