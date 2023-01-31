package com.msciq.storage.common.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import com.msciq.storage.model.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(targetEntity = Company.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = FieldConstants.GC_ID)
	private List<Company> companies;

//	@NotEmpty(message = ErrorConstants.CURRENCY_NOT_NULL)
	@ManyToOne
	@JoinColumn(name = FieldConstants.CURRENCY_ID)
	private Currency currency;

	@ManyToOne
	@JoinColumn(name = FieldConstants.COUNTRY_ID)
	private Country country;

}
