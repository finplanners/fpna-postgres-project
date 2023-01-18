package com.msciq.storage.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

import com.msciq.storage.common.*;
import com.msciq.storage.model.BaseEntity;
import lombok.Data;

/**
 * <p>
 * This class is an entity class for department table.
 * </p>
 * 
 * @author Rajkumar created on Jun 2022
 */
@Data
@Entity
@Table(name = TableConstants.TABLE_DEPARTMENT)
public class Department extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = ErrorConstants.DEPARTMENT_NAME_NOT_NULL)
	@Column(name = FieldConstants.NAME)
	private String departName;

	@NotEmpty(message = ErrorConstants.DEPARTMENT_CODE_NOT_NULL)
	@Column(name = FieldConstants.DEPARTMENT_CODE)
	private String departmentCode;
	
	@Column(name = FieldConstants.ACTIVATION_DATE)
	private Date activationDate;
	
	@Column(name = FieldConstants.END_DATE)
	private Date endDate;
	
	@Column(name = FieldConstants.DEPART_HEAD_NAME)
	private String departHeadName;
	
	@Column(name = FieldConstants.REPORT_OWNER)
	private String reportOwner;
	
	public Date getActivationDate() {
		System.out.println("before===="+activationDate);
		if(activationDate instanceof Date) {
			System.out.println("yes Date");
		}else {
			System.out.println("No Date");
		}
		//String dateString = !CommonUtil.isNull(activationDate) ? StringUtil.getDateString(activationDate,"dd/MM/yyyy"): null;
		Date convertedDate = DateUtil.formatDate(activationDate, Constants.FORMAT_DDMMYYYY,false);
		System.out.println("before===="+convertedDate);
		return convertedDate;
	}
	
	public Date getEndDate() {
		System.out.println("before end date===="+endDate);
		if(endDate instanceof Date) {
			System.out.println("endDate yes Date");
		}else {
			System.out.println("endDate No Date");
		}
		Date convertedDate = DateUtil.formatDate(activationDate, Constants.FORMAT_DDMMYYYY,false);
		System.out.println("before= end date==="+convertedDate);
		return convertedDate;
	}
}
