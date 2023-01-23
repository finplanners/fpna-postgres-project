package com.msciq.storage.budgetCategory.service;

import com.msciq.storage.model.BudgetCategory;
import com.msciq.storage.model.request.BudgetCategoryTemplateTypeMappingDTO;

import java.util.List;

public interface BudgetCategoryService {

    /**
     * This method returns list of all budget categories
     *
     * @return List of BudgetCategory Entity
     * @author Sivaranjani DS
     */
    public List<BudgetCategory> getAllBudgetCategories();

    /**
     * This method adds list of budget categories
     *
     * @param budgetCategories
     * @return BudgetCategory Entity
     * @author Sivaranjani DS
     */
    public List<BudgetCategory> addBudgetCategories(List<BudgetCategory> budgetCategories);

    BudgetCategory mapTemplateTypesToBudgetCategory(BudgetCategoryTemplateTypeMappingDTO budgetCategoryTemplateTypeMapping);
}
