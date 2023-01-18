package com.msciq.storage.common.msciq;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.entity.Currency;
import lombok.Data;

@Data
public class GroupCompanyDTO {

	private Long id;

	//@NotEmpty(message = ErrorConstants.GC_NAME_NOT_NULL)
	//@Size(min=1, max=15)
	//@Pattern(regexp = "{A-Za-z0-9}*")
	
	@Pattern(regexp = "^[a-zA-Z0-9_\\s]*$", message = ErrorConstants.GC_NAME_NOT_NULL)
    //@Pattern(regexp = "^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{1,3}\\\\))|\\\\d{1,3})[- .]?\\\\d{3,4}[- .]?\\\\d{4}$", message = ErrorConstants.PHONE_NUMBER_INVALID)
	private String gcName;
	
	@NotEmpty(message = ErrorConstants.GC_CODE_NOT_NULL)
	//@Pattern(regexp = "^[a-zA-Z\\d_]+$")
	private String gcCode;

	//@NotEmpty(message = ErrorConstants.CURRENCY_NOT_NULL)
	@NotNull(message = "Currency Must Not be Null")
	private Currency currency;
				
}
