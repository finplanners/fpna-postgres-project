package com.msciq.storage.common.msciq;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LocationDTO {

	private Long id;

	private CompanyDTO company;

	private String code;

	private String name;

	private String address;

	private String country;

	private String state;

	private boolean isActive;

	private long companyId;

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date activeFrom;

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date inActive;



}
