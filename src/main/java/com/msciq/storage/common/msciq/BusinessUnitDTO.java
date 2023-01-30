package com.msciq.storage.common.msciq;

import java.util.Date;
import java.util.List;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.model.GroupCompany;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
public class BusinessUnitDTO {
	
	private Long id;

	@NotEmpty(message = ErrorConstants.BU_NAME_NOT_NULL)
	//@Pattern(regexp = "{A-Za-z0-9}*")
	private String name;
	
	@NotEmpty(message = ErrorConstants.BU_CODE_NOT_NULL)
	//@Pattern(regexp = "{A-Za-z0-9}*")
	private String code;

	//@NotEmpty(message = ErrorConstants.GC_NAME_NOT_NULL)
	private GroupCompanyDTO groupCompany;
	
	//@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date activationDate;
	
	//@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date endDate;

//	private boolean isActive;
//
//	private boolean isDeleted;


	//private String buOwner;
	//private String buOwnerEmail;
				
}
