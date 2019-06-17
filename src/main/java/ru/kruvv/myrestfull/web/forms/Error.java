package ru.kruvv.myrestfull.web.forms;

public class Error extends Result {

	private final String err;

	public Error(String err) {
		this.err = err;
	}

	public String getError() {
		return err;
	}
}
