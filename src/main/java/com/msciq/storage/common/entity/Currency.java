package com.msciq.storage.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import com.msciq.storage.model.BaseEntity;
import lombok.Data;

/**
 * <p>
 * This class is an entity class for Currency table.
 * </p>
 * 
 * @author Rajkumar created on Dec 2022
 */
@Data
@Entity
@Table(name = TableConstants.TABLE_CURRENCY)
public class Currency extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = ErrorConstants.CURRENCY_NOT_NULL)
	@Column(name = FieldConstants.CURRENCY)
	private String currency;

	@Column(name = FieldConstants.DESCRIPTION)
	private String description;

}
