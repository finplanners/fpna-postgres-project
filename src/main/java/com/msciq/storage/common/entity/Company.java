package com.msciq.storage.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

import com.msciq.storage.common.*;
import com.msciq.storage.model.BaseEntity;
import lombok.Data;

/**
 * <p>
 * This class is an entity class for company table.
 * </p>
 * 
 * @author Rajkumar created on Jun 2022
 */
@Data
@Entity
@Table(name = TableConstants.TABLE_COMPANY)
public class Company extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = ErrorConstants.COMPNAY_NAME_NOT_NULL)
	@Column(name = FieldConstants.NAME)
	private String name;

	@NotEmpty(message = ErrorConstants.COMPNAY_CODE_NOT_NULL)
	@Column(name = FieldConstants.CODE)
	private String code;
	
	//@NotEmpty(message = ErrorConstants.CURRENCY_NOT_NULL)
	@ManyToOne
	@JoinColumn(name = FieldConstants.CURRENCY_ID)
	private Currency currency;
	
	//@NotEmpty(message = ErrorConstants.GC_NAME_NOT_NULL)
	@ManyToOne
	@JoinColumn(name = FieldConstants.GC_ID)
	private GroupCompany groupCompany;
	
	
	//@NotEmpty(message = ErrorConstants.FIS_CAL_KEY_NULL)
	@ManyToOne
	@JoinColumn(name = FieldConstants.FC_ID)
	private FiscalCalendar fiscalCalendar;
	
	@Column(name = FieldConstants.ACTIVATION_DATE)
	private Date activationDate;
	
	@Column(name = FieldConstants.END_DATE)
	private Date endDate;
	
	public Date getActivationDate() {
		System.out.println("before===="+activationDate);
		Date convertedDate = DateUtil.formatDate(activationDate, Constants.FORMAT_DDMMYYYY,false);
		System.out.println("before===="+convertedDate);
		return convertedDate;
	}
	
	public Date getEndDate() {
		Date convertedDate = DateUtil.formatDate(activationDate, Constants.FORMAT_DDMMYYYY,false);
		System.out.println("before= end date==="+convertedDate);
		return convertedDate;
		
		
	}
	

}
