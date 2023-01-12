package com.msciq.storage.model.response;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * Generic success message object.
 * </p>
 * 
 * @author Sivaranjani DS
 *
 * @param <T> -
 */
@Data
public class SuccessResponseMessage<T> {
	private String message;
	private Object data;
	private Integer responseCode;

	/**
	 * <h1>Success message.</h1>
	 * 
	 * @param message      - Message to be displayed to the user is passed in this
	 *                     attribute.
	 * @param entity       - object is passed in this attribute.
	 * @param responseCode - response code is passed in this attribute.
	 */
	public SuccessResponseMessage(String message, Object entity, Integer responseCode) {
		this.setMessage(message);
		this.setData(entity);
		this.setResponseCode(responseCode);
	}
}
