package com.msciq.storage.common.msciq;

import com.msciq.storage.common.ErrorConstants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class DepartmentDTO {
	
	private Long id;

	@NotEmpty(message = ErrorConstants.DEPARTMENT_NAME_NOT_NULL)
	//@Min(1)
	//@Max(15)
	//@Pattern(regexp = "{A-Za-z0-9}*")
 //@Pattern(regexp = "{A-Za-z0-9}*", message = ErrorConstants.DEPARTMENT_NAME_NOT_NULL)
	private String departName;

	@NotEmpty(message = ErrorConstants.DEPARTMENT_CODE_NOT_NULL)
	// @Pattern(regexp = "^[a-zA-Z\\d_]+$")
	

	//@Pattern(regexp = "^[a-zA-Z\\d_]+$", message = ErrorConstants.DEPARTMENT_CODE_NOT_NULL)
	private String departmentCode;

	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	//@JsonFormat(pattern = "dd/MM/yyyy")
	//@NotNull(message = ErrorConstants.ACTIVATION_DATE_FORMAT)
	
	private Date activationDate;

	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	//@JsonFormat(pattern = "dd/MM/yyyy")
	//@NotNull(message = ErrorConstants.END_DATE_FORMAT)
	private Date endDate;

	private String departHeadName;
	private String reportOwner;

}
