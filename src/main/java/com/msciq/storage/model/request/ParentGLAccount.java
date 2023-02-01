package com.msciq.storage.model.request;

import com.msciq.storage.model.GLAccountGroup;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class ParentGLAccount {

    private String glGroupCodeName;

    private Long glAccountCode;

    private String accountDescription;

    private String accountType;

    private Timestamp activeFromDate;

    private Timestamp inactiveDate;

    private Long parentGlAccount;

    private List<GLAccountGroup> childGLAccounts;

}
