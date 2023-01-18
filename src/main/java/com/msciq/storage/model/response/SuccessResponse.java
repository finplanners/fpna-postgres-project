package com.msciq.storage.model.response;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.MessageValidator;
import com.msciq.storage.common.SuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * <p>
 * Generic success response
 * </p>
 *
 * @author Sivaranjani DS
 *
 * @param <T>
 */

public class SuccessResponse<T> extends ResponseEntity<Object> {

	public SuccessResponse(String message, Object entity, List<T> entityList,
						   HttpStatus httpStatus) {
		super(new SuccessResponseMessage<T>(message, entity,
				Integer.valueOf(httpStatus.value())), httpStatus);
	}

	public SuccessResponse(String message, List<T> entityList,
						   HttpStatus httpStatus) {
		super(new SuccessResponseMessage<T>(message, entityList,
				Integer.valueOf(httpStatus.value())), httpStatus);
	}

	public SuccessResponse(SuccessCode successCode, Object entity, HttpStatus httpStatus, String value) {
		super(new SuccessResponseMessage<T>(Constants.SUCCESS, entity, null,
				Integer.valueOf(httpStatus.value())), httpStatus);
	}

}
