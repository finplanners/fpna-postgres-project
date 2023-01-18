package com.msciq.storage.common.msciq;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.mdtlabs.coreplatform.common.ErrorConstants;

import lombok.Data;

@Data
public class FiscalCalendarDTO {
	
	private Long id;

	@NotEmpty(message = ErrorConstants.FIS_CAL_KEY_NULL)
	//@Size(min = 1, max = 4)
	private String key;

	@NotEmpty(message = ErrorConstants.FIS_CAL_DESCRIPTION_NULL)
	private String description;

	private String startMonth;
	private String startDay;
	private String startWeekDay;
	private String startPeriodOfYear;
	private String seventhYrExtraWeek;

}
