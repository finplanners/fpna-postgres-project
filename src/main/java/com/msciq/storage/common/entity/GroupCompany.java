package com.msciq.storage.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import com.msciq.storage.model.BaseEntity;
import lombok.Data;

/**
 * <p>
 * This class is an entity class for country table.
 * </p>
 * 
 * @author Rajkumar created on Jun 2022
 */
@Data
@Entity
@Table(name = TableConstants.TABLE_GC)
public class GroupCompany extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = ErrorConstants.GC_NAME_NOT_NULL)
	@Column(name = FieldConstants.GC_NAME)
	private String gcName;

	@NotEmpty(message = ErrorConstants.GC_CODE_NOT_NULL)
	@Column(name = FieldConstants.GC_CODE)
	private String gcCode;

//	@NotEmpty(message = ErrorConstants.CURRENCY_NOT_NULL)
	@ManyToOne
	@JoinColumn(name = FieldConstants.CURRENCY_ID)
	private Currency currency;

}
