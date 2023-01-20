package com.msciq.storage.common.msciq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.model.Company;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date activeFrom;

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date inActive;



}
