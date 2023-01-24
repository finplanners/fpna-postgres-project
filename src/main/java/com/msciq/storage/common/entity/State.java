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
 * This class is an entity class for country table.
 * </p>
 * 
 * @author Venugopl created on Jan 2023
 */
@Data
@Entity
@Table(name = TableConstants.TABLE_STATE)
public class State extends BaseEntity {

	@NotEmpty(message = ErrorConstants.STATE_NAME_NOT_NULL)
	@Column(name = FieldConstants.NAME)
	private String name;

}
