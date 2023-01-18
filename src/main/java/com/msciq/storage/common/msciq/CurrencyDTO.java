package com.msciq.storage.common.msciq;

import com.msciq.storage.common.ErrorConstants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CurrencyDTO {

	private Long id;
	
	@NotEmpty(message = ErrorConstants.CURRENCY_NOT_NULL)
	private String currency;

	private String description;

}
