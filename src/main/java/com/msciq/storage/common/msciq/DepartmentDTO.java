package com.msciq.storage.common.msciq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.model.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class DepartmentDTO {
	
	private Long id;

	@NotEmpty(message = ErrorConstants.DEPARTMENT_NAME_NOT_NULL)
	//@Min(1)
	//@Max(15)
	//@Pattern(regexp = "{A-Za-z0-9}*")
 //@Pattern(regexp = "{A-Za-z0-9}*", message = ErrorConstants.DEPARTMENT_NAME_NOT_NULL)
	private String name;

	@NotEmpty(message = ErrorConstants.DEPARTMENT_CODE_NOT_NULL)
	// @Pattern(regexp = "^[a-zA-Z\\d_]+$")
	

	//@Pattern(regexp = "^[a-zA-Z\\d_]+$", message = ErrorConstants.DEPARTMENT_CODE_NOT_NULL)
	private String code;

	private List<CompanyDTO> companies;

	private boolean status;

	private boolean acquiredEntity;

	private String departHead;

	private User departHeadGlobal;

	private String reportOwner;

//	 @DateTimeFormat(pattern = "dd/MM/yyyy")
//	@JsonFormat(pattern = "dd/MM/yyyy")
//	@NotNull(message = ErrorConstants.ACTIVATION_DATE_FORMAT)

//	@Builder.Default
	private Date activationDate;

	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	//@JsonFormat(pattern = "dd/MM/yyyy")
	//@NotNull(message = ErrorConstants.END_DATE_FORMAT)
	private Date endDate;

}
