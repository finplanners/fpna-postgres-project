package com.msciq.storage.common.msciq;

import javax.validation.constraints.NotEmpty;

import com.mdtlabs.coreplatform.common.ErrorConstants;

import lombok.Data;

@Data
public class CurrencyDTO {

	private Long id;
	
	@NotEmpty(message = ErrorConstants.CURRENCY_NOT_NULL)
	private String currency;

	private String description;

}
