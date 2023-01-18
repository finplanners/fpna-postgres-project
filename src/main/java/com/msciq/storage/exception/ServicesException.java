package com.msciq.storage.exception;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.MessageValidator;

import java.util.List;

/**
 * This class is used to handle ServicesException.
 * 
 * @author Rajkumar Created on 30 Jun 2022
 */
public class ServicesException extends RuntimeException {

	private static final long serialVersionUID = 6918269662648545345L;
	private final Integer code;
	private final String message;

	public ServicesException(final Integer code) {
		this.code = code;
		this.message = this.getClass().getSimpleName();
	}

	public ServicesException(final Integer code, final String message) {
		this.code = code;
		this.message = MessageValidator.getInstance().getMessage(code.toString(), Constants.ERROR, message);
	}

	public ServicesException(final Integer code, final String... params) {
		this.code = code;
		this.message = MessageValidator.getInstance().getMessage(code.toString(), Constants.ERROR, params);
	}

	public ServicesException(final Integer code, final List<String> params) {
		this.code = code;
		this.message = MessageValidator.getInstance().getMessage(code.toString(), Constants.ERROR, params);
	}

	public Integer getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
