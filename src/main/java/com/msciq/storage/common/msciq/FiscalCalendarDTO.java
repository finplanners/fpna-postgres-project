package com.msciq.storage.common.msciq;

import com.msciq.storage.common.ErrorConstants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FiscalCalendarDTO {
	
	private Long id;

	@NotEmpty(message = ErrorConstants.FIS_CAL_KEY_NULL)
	private String key;

	@NotEmpty(message = ErrorConstants.FIS_CAL_DESCRIPTION_NULL)
	private String description;

	private String startMonth;
	private String endMonth;
	private String startDay;
	private String startWeekDay;
	private String startPeriodOfYear;
	private String endYear;
	private String seventhYrExtraWeek;

}
