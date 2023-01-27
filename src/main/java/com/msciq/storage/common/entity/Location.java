package com.msciq.storage.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
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
@Table(name = TableConstants.TABLE_LOCATION)
public class Location extends BaseEntity {

	@NotEmpty(message = ErrorConstants.LOCATION_NAME_NOT_NULL)
	@Column(name = FieldConstants.NAME)
	private String name;

	@NotEmpty(message = ErrorConstants.LOCATION_CODE_NOT_NULL)
	@Column(name = FieldConstants.CODE)
	private String code;

	@ManyToOne
	@JoinColumn(name = FieldConstants.COMPANY_ID)
	private Company company;

	private String country;

	private String state;

	private String address;

	private Date activeFrom;

	private Date inActive;


}
