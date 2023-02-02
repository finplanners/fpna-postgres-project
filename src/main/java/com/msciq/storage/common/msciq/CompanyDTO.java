package com.msciq.storage.common.msciq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.entity.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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

	private List<Location> locations;

	@NotNull(message = ErrorConstants.CURRENCY_NOT_NULL)
	private CurrencyDTO currency;
	
	@NotNull(message = ErrorConstants.FISCAL_YEAR_NOT_NULL)
	private FiscalCalendarDTO fiscalCalendar;
	
	@NotNull(message = ErrorConstants.GC_NAME_NOT_NULL)
	private long groupCompanyId;

	@NotNull(message = ErrorConstants.DEPARTMENT_NAME_NOT_NULL)
	private long departmentId;

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
