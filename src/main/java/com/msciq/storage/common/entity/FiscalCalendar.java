package com.msciq.storage.common.entity;

import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import com.msciq.storage.model.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * This class is an entity class for department table.
 * </p>
 * 
 * @author Rajkumar created on Jun 2022
 */
@Data
@Entity
@Table(name = TableConstants.TABLE_FISCAL_CALENDAR)
public class FiscalCalendar extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = ErrorConstants.FIS_CAL_KEY_NULL)
	@Column(name = FieldConstants.KEY)
	private String key;

	@NotEmpty(message = ErrorConstants.FIS_CAL_DESCRIPTION_NULL)
	@Column(name = FieldConstants.DESCRIPTION)
	private String description;
	
	@Column(name = FieldConstants.START_MONTH)
	private String startMonth;
	
	@Column(name = FieldConstants.START_DAY)
	private String startDay;
	
	@Column(name = FieldConstants.START_WEEK_DAY)
	private String startWeekDay;
	
	@Column(name = FieldConstants.START_PERIOD_OF_YEAR)
	private String startPeriodOfYear;
	
	@Column(name = FieldConstants.SEVENTH_YR_EXTRA_WEEK)
	private String seventhYrExtraWeek;	

}
