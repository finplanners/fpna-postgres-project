package com.msciq.storage.common.entity;

import com.msciq.storage.model.BaseEntity;
import com.msciq.storage.common.TableConstants;
import com.msciq.storage.common.FieldConstants;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = TableConstants.TABLE_FISCAL_CALENDAR_PERIOD)
public class FiscalCalendarPeriod extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = FieldConstants.FC_KEY)
    private String fcKey;
    @Column(name = FieldConstants.MONTH)
    private String month;

    @Column(name = FieldConstants.YEAR)
    private String year;

    @Column(name = FieldConstants.QUARTER)
    private String quarter;

}