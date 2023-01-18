package com.msciq.storage.common.msciq;

import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.entity.Currency;
import com.msciq.storage.common.entity.FiscalCalendar;
import com.msciq.storage.model.GroupCompany;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class CompanyDTO {
	
	private Long id;

	@NotEmpty(message = ErrorConstants.COMPNAY_NAME_NOT_NULL)
	//@Size(min=1, max=15)
	//@Pattern(regexp = "{A-Za-z0-9}*")
	private String name;
	
	@NotEmpty(message = ErrorConstants.COMPNAY_CODE_NOT_NULL)
	//@Pattern(regexp = "{A-Za-z0-9}*")
	private String code;

	//@NotEmpty(message = ErrorConstants.CURRENCY_NOT_NULL)
	private Currency curreny;
	
	//@NotEmpty(message = ErrorConstants.FISCAL_YEAR_NOT_NULL)
	private FiscalCalendar fiscalCalendar;
	
	//@NotEmpty(message = ErrorConstants.GC_NAME_NOT_NULL)
	private GroupCompany groupCompany;
	
	//@NotEmpty(message = ErrorConstants.GC_CODE_NOT_NULL)
	//@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date activationDate;
	
	//@NotEmpty(message = ErrorConstants.GC_CODE_NOT_NULL)
	//@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date endDate;
	
}
