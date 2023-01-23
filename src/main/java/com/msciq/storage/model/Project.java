package com.msciq.storage.model;

import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import com.msciq.storage.common.entity.BusinessUnit;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = TableConstants.PROJECT)
public class Project extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = FieldConstants.CODE)
    private String code;

    @Column(name = FieldConstants.NAME)
    private String name;

    @ManyToOne
    @JoinColumn(name=FieldConstants.BU_ID)
    private BusinessUnit businessUnit;

    @ManyToOne
    @JoinColumn(name=FieldConstants.PRODUCT_GROUP_ID)
    private ProductGroup productGroup;

    @ManyToOne
    @JoinColumn(name=FieldConstants.PROFIT_CENTER_ID)
    private ProfitCenter profitCenter;

    @Column(name = FieldConstants.PROJECT_OWNER_NAME)
    private String projectOwnerName;

    @Column(name = FieldConstants.PROJECT_OWNER_EMAIL)
    private String projectOwnerEmail;

    @Column(name = FieldConstants.START_DATE)
    private LocalDate startDate;

    @Column(name = FieldConstants.END_DATE)
    private LocalDate endDate;

    @Column(name = FieldConstants.INACTIVE_DATE)
    private LocalDate inactiveDate;

}
