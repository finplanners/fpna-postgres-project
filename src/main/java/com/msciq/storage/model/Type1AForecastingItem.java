package com.msciq.storage.model;

import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * This class is an entity class for 1A template items.
 * </p>
 *
 * @author Sivaranjani DS
 */
@Data
@Entity
@Table(name = TableConstants.TYPE_1A_FORECASTING_ITEM)
public  class Type1AForecastingItem extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @NotEmpty(message = ErrorConstants.DEPT_ID_NOT_NULL)
    @Column(name = FieldConstants.DEPT_ID)
    private String departmentId;

    @NotEmpty(message = ErrorConstants.DEPT_CODE_NOT_NULL)
    @Column(name = FieldConstants.DEPT_CODE)
    private String departmentCode;

    @NotEmpty(message = ErrorConstants.DEPT_NAME_NOT_NULL)
    @Column(name = FieldConstants.DEPT_NAME)
    private String departmentName;

    @NotEmpty(message = ErrorConstants.BU_ID_NOT_NULL)
    @Column(name = FieldConstants.BUSINESS_UNIT_ID)
    private String businessUnitId;

    @NotEmpty(message = ErrorConstants.BU_CODE_NOT_NULL)
    @Column(name = FieldConstants.BUSINESS_UNIT_CODE)
    private String businessUnitCode;

    @NotEmpty(message = ErrorConstants.BU_NAME_NOT_NULL)
    @Column(name = FieldConstants.BUSINESS_UNIT_NAME)
    private String businessUnitName;

    @NotEmpty(message = ErrorConstants.LOCATION_CODE_NOT_NULL)
    @Column(name = FieldConstants.LOCATION_CODE)
    private String locationCode;

    @NotEmpty(message = ErrorConstants.LOCATION_NAME_NOT_NULL)
    @Column(name = FieldConstants.LOCATION_NAME)
    private String locationName;

    @NotEmpty(message = ErrorConstants.GL_ACCOUNT_NOT_NULL)
    @Column(name = FieldConstants.GL_ACCOUNT)
    private String glAccount;

    @Column(name = FieldConstants.GL_ACCOUNT_DESCRIPTION)
    private String glAccountDescription;

    @NotEmpty(message = ErrorConstants.PROJECT_ID_NOT_NULL)
    @Column(name = FieldConstants.PROJECT_ID)
    private String projectId;

    @NotEmpty(message = ErrorConstants.PROJECT_CODE_NOT_NULL)
    @Column(name = FieldConstants.PROJECT_CODE)
    private String projectCode;

    @Column(name = FieldConstants.PROJECT_NAME)
    private String projectName;

    @Column(name = FieldConstants.VENDOR)
    private String vendor;

    @Column(name = FieldConstants.ITEM_DESCRIPTION)
    private String itemDescription;

    @Column(name = FieldConstants.BUSINESS_JUSTIFICATION)
    private String businessJustification;

    @Column(name = FieldConstants.AMOUNT)
    private String amount;

    @Column(name = FieldConstants.MONTH)
    private String month;

    @Column(name = FieldConstants.YEAR)
    private String year;

}