package com.msciq.storage.common.entity;

import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import com.msciq.storage.model.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = TableConstants.EMPLOYEE)
public class Employee extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = FieldConstants.EMPLOYEE_ID)
    private String employeeId;

    @Column(name = FieldConstants.FIRST_NAME)
    private String firstName;

    @Column(name = FieldConstants.LAST_NAME)
    private String lastName;

    @Column(name = FieldConstants.TITLE)
    private String title;

    @Column(name = FieldConstants.COMPANY_ID)
    private String companyId;

    @Column(name = FieldConstants.DEPARTMENT)
    private String department;

    @Column(name = FieldConstants.COST_CENTER)
    private String costCenter;

    @Column(name = FieldConstants.COUNTRY)
    private String country;

    @Column(name = FieldConstants.STATE)
    private String state;

    @Column(name = FieldConstants.EMAIL)
    private String email;

    @Column(name = FieldConstants.START_DATE)
    private LocalDate startDate;

    @Column(name = FieldConstants.TERMINATION_DATE)
    private LocalDate terminationDate;

    @Column(name = FieldConstants.PHONE)
    private String phoneNumber;


}
