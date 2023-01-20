package com.msciq.storage.common.msciq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.model.Company;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class LocationDTO {

	private Long id;

	private Company company;

	private String code;

	private String name;

	private String address;

	private boolean isActive;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date activeFrom;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date inActive;



}
