package com.msciq.storage.exception;

import java.util.List;

/**
 * This class is used to handle the DataNotFoundException.
 * 
 * @author Rajkumar Created on 30 Jun 2022
 */
public class DataNotFoundException extends ServicesException {

	/**
	 * This method is used to generate generic exception
	 */
	public DataNotFoundException() {
		this(1001, DataNotFoundException.class.getSimpleName());
	}

	public DataNotFoundException(final Integer code) {
		this(code, DataNotFoundException.class.getSimpleName());
	}

	public DataNotFoundException(final Integer code, final String... params) {
		super(code, params);
	}

	public DataNotFoundException(final Integer code, final String message) {
		super(code, message);
	}

	public DataNotFoundException(final Integer code, final List<String> params) {
		super(code, params);
	}
}
