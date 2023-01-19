package com.msciq.storage.common.entity;

import com.msciq.storage.common.*;
import com.msciq.storage.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * <p>
 * This class is an entity class for country table.
 * </p>
 * 
 * @author Venugopl created on Jan 2023
 */
@Data
@Entity
@Table(name = TableConstants.TABLE_COUNTRY)
public class Country extends BaseEntity {

	@NotEmpty(message = ErrorConstants.COUNTRY_NAME_NOT_NULL)
	@Column(name = FieldConstants.NAME)
	private String name;

	private String countryCode;

	private String description;


}