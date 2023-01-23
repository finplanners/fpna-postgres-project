package com.msciq.storage.common.msciq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.entity.Country;
import com.msciq.storage.common.entity.Currency;
import com.msciq.storage.common.entity.FiscalCalendar;
import com.msciq.storage.common.entity.GroupCompany;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CompanyDTO {
	
	private Long id;

	@NotEmpty(message = ErrorConstants.COMPANY_NAME_NOT_NULL)
	//@Size(min=1, max=15)
	//@Pattern(regexp = "{A-Za-z0-9}*")
	private String name;
	
	@NotEmpty(message = ErrorConstants.COMPANY_CODE_NOT_NULL)
	//@Pattern(regexp = "{A-Za-z0-9}*")
	private String code;
	@NotNull(message = ErrorConstants.COUNTRY_NOT_NULL)
	private Country country;

	@NotNull(message = ErrorConstants.CURRENCY_NOT_NULL)
	private Currency currency;
	
	@NotNull(message = ErrorConstants.FISCAL_YEAR_NOT_NULL)
	private FiscalCalendar fiscalCalendar;
	
	@NotNull(message = ErrorConstants.GC_NAME_NOT_NULL)
	private GroupCompany groupCompany;
	
	//@NotEmpty(message = ErrorConstants.GC_CODE_NOT_NULL)
	//@DateTimeFormat(pattern = "dd/mm/yyyy")
	//@DateTimeFormat(pattern = "dd/mm/yyyy")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date activationDate;
	
	//@NotEmpty(message = ErrorConstants.GC_CODE_NOT_NULL)
	//@DateTimeFormat(pattern = "dd/mm/yyyy")
	//@DateTimeFormat(pattern = "dd/mm/yyyy")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;

	private boolean status;
	
}
