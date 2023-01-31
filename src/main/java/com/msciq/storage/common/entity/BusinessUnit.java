package com.msciq.storage.common.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.msciq.storage.common.*;
import com.msciq.storage.model.BaseEntity;
import lombok.Data;

/**
 * <p>
 * This class is an entity class for business unit table.
 * </p>
 * 
 * @author Rajkumar created on Jun 2022
 */
@Data
@Entity
@Table(name = TableConstants.TABLE_BU)
public class BusinessUnit extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = ErrorConstants.BU_NAME_NOT_NULL)
	@Column(name = FieldConstants.NAME)
	private String name;

	@NotEmpty(message = ErrorConstants.BU_CODE_NOT_NULL)
	@Column(name = FieldConstants.CODE)
	private String code;
	
	//@NotEmpty(message = ErrorConstants.GC_NAME_NOT_NULL)
	@ManyToOne
	@JoinColumn(name = FieldConstants.GC_ID)
	private GroupCompany groupCompany;

	private Boolean status;
	
	@Column(name = FieldConstants.ACTIVATION_DATE)
	private Date activationDate;

	@Column(name = FieldConstants.END_DATE)
	private Date endDate;

//	@Column(name = FieldConstants.BU_OWNER)
//	private String buOwner;
//
//	@Column(name = FieldConstants.BU_OWNER_EMAIL)
//	private String buOwnerEmail;

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
