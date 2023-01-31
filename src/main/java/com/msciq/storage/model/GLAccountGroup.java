package com.msciq.storage.model;


import com.msciq.storage.common.ErrorMessage;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = TableConstants.TABLE_GL_ACCOUNT_GROUP)
public class GLAccountGroup extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @NotNull(message = ErrorMessage.ERROR_GL_ACCOUNT_IMPORT)
    @Column(name = FieldConstants.GL_ACCOUNT_GROUP_CODE_NAME)
    private String glGroupCodeName;

    @Column(name = FieldConstants.GL_ACCOUNT_CODE)
    private Long glAccountCode;

    @Column(name = FieldConstants.ACCOUNT_DESCRIPTION)
    private String accountDescription;

    @Column(name = FieldConstants.ACCOUNT_TYPE)
    private String accountType;

    @Column(name = FieldConstants.ACTIVE_FROM_DATE)
    private Timestamp activeFromDate;

    @Column(name = FieldConstants.INACTIVE_DATE)
    private Timestamp inactiveDate;

    @Column(name = FieldConstants.FORECASTING_RELEVANT)
    private boolean forecastingRelevant = false;

    @Column(name = FieldConstants.IS_PARENT)
    private Boolean isParent;

    @Column(name = FieldConstants.PARENT_GL_ACCOUNT)
    private String parentGlAccount;

}
