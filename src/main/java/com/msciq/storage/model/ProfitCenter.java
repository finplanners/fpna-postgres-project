package com.msciq.storage.model;

import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@Entity
@Table(name = TableConstants.PROFIT_CENTER)
public class ProfitCenter extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = FieldConstants.PARENT_PROFIT_CENTER)
    private String parentProfitCenter;

    @Column(name = FieldConstants.CODE)
    private String code;

    @Column(name = FieldConstants.NAME)
    private String name;

    @Column(name = FieldConstants.PROFIT_CENTER_OWNER)
    private String profitCenterOwner;

    @Email(message = "Email is invalid")
    @Column(name = FieldConstants.EMAIL)
    private String email;

    @Column(name = FieldConstants.ACTIVE_FROM_DATE)
    private LocalDate activeFromDate;

    @Column(name = FieldConstants.INACTIVE_DATE)
    private LocalDate inactiveDate;

}
