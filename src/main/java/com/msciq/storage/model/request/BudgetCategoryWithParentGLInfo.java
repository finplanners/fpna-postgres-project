package com.msciq.storage.model.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BudgetCategoryWithParentGLInfo {

    private long budgetCategoryId;

    private List<ParentGLAccount> parentGLAccount;

}
