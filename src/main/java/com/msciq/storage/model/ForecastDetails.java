package com.msciq.storage.model;

import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * This class is an entity class for 1A template items.
 * </p>
 *
 * @author Sivaranjani DS
 */
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = TableConstants.FORECAST_DETAILS)
public  class ForecastDetails extends BaseEntity {
    private static final Long serialVersionUID = 1L;

    @Column(name = FieldConstants.USER_ID)
    private String userId;

    @Column(name = FieldConstants.FORECAST_ID)
    private Long forecastId;

    @Column(name = FieldConstants.GL_ACCOUNT)
    private String glAccount;

    @Column(name = FieldConstants.AMOUNT)
    private String amount;

    @Column(name = FieldConstants.MONTHS_TO_AMORTIZE)
    private int noOfMonthsToAmortize;

    @Column(name = FieldConstants.RECURRING_EXPENSE_MONTHS)
    private int noOfRecurringExpenseMonths;

    @Column(name = FieldConstants.MONTH)
    private String month;

    @Column(name = FieldConstants.YEAR)
    private String year;

    @Column(name = FieldConstants.BU)
    private String bu;

    @Column(name = FieldConstants.DEPARTMENT)
    private String department;

    @Column(name = FieldConstants.PROJECT_CODE)
    private String projectCode;

    @Column(name = FieldConstants.LOCATION)
    private String location;

    @Column(name = FieldConstants.HIRING_MANAGER)
    private String hiringManager;

    @Column(name = FieldConstants.JOB_TITLE)
    private String jobTitle;

}