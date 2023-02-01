package com.msciq.storage.budgetCategory.service;

import com.msciq.storage.model.BudgetCategory;
import com.msciq.storage.model.request.BudgetCategoryTemplateTypeMappingDTO;
import com.msciq.storage.model.request.BudgetCategoryWithParentGLInfo;

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

    /**
     * This method is used to map budget category and template types
     *
     * @param budgetCategoryTemplateTypeMapping
     * @return BudgetCategory Entity
     * @author Sivaranjani DS
     */
    BudgetCategory mapTemplateTypesToBudgetCategory(BudgetCategoryTemplateTypeMappingDTO budgetCategoryTemplateTypeMapping);

    /**
     * This method is used to get budget categories by templateType
     *
     * @param templateTypeId
     * @return List of budget categories
     * @author Sivaranjani DS
     */
    List<BudgetCategory> getBudgetCategoriesByTemplateType(Long templateTypeId);

    /**
     * This method returns list of Budget category with parent and child gl account details by templatetype
     *
     * @param templateTypeId
     * @return List of budget categories with gl account group info
     * @author Sivaranjani DS
     */
    List<BudgetCategoryWithParentGLInfo> getBudgetCategoriesWithParentAndChildGLInfoByTemplateType(Long templateTypeId);
}
