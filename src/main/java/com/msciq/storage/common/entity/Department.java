package com.msciq.storage.common.entity;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.msciq.storage.common.*;
import com.msciq.storage.model.BaseEntity;
import com.msciq.storage.model.Role;
import com.msciq.storage.model.Template;
import com.msciq.storage.model.User;
import lombok.Data;

/**
 * <p>
 * This class is an entity class for department table.
 * </p>
 * 
 * @author Rajkumar created on Jun 2022
 */
@Data
@Entity
@Table(name = TableConstants.TABLE_DEPARTMENT)
public class Department extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = ErrorConstants.DEPARTMENT_NAME_NOT_NULL)
	@Column(name = FieldConstants.NAME,unique = true)
	private String name;

	@NotEmpty(message = ErrorConstants.DEPARTMENT_CODE_NOT_NULL)
	@Column(name = FieldConstants.DEPARTMENT_CODE)
	private String code;
	
	@Column(name = FieldConstants.ACTIVATION_DATE)
	private Date activationDate;
	
	@Column(name = FieldConstants.END_DATE)
	private Date endDate;

//	private boolean status = true;

//	private boolean acquiredEntity = false;
	
	@Column(name = FieldConstants.DEPART_HEAD)
	private String departHead;

//	@OneToOne(targetEntity = User.class, cascade = { CascadeType.ALL })
//	@JoinColumn(name = FieldConstants.DEPART_HEAD_GLOBAL)
	@OneToOne
	@JoinColumn(name = FieldConstants.DEPART_HEAD_GLOBAL)
	private User departHeadGlobal;
	
	@Column(name = FieldConstants.REPORT_OWNER)
	private String reportOwner;

//	@OneToMany(targetEntity = Company.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
//	@JoinColumn(name = "dept_id")
//	private List<Company> companies;

	public Date getActivationDate() {
		System.out.println("before===="+activationDate);
		if(activationDate instanceof Date) {
			System.out.println("yes Date");
		}else {
			System.out.println("No Date");
		}
		//String dateString = !CommonUtil.isNull(activationDate) ? StringUtil.getDateString(activationDate,"dd/MM/yyyy"): null;
		Date convertedDate = DateUtil.formatDate(activationDate, Constants.FORMAT_DDMMYYYY,false);
		System.out.println("before===="+convertedDate);
		return convertedDate;
	}
	
	public Date getEndDate() {
		System.out.println("before end date===="+endDate);
		if(endDate instanceof Date) {
			System.out.println("endDate yes Date");
		}else {
			System.out.println("endDate No Date");
		}
		Date convertedDate = DateUtil.formatDate(activationDate, Constants.FORMAT_DDMMYYYY,false);
		System.out.println("before= end date==="+convertedDate);
		return convertedDate;
	}

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "department_template",
			joinColumns = { @JoinColumn(name = "dept_id") },
			inverseJoinColumns = { @JoinColumn(name = "temp_id") })
	private Set<Template> templates = new HashSet<>();
}
