package com.msciq.storage.budgetCategory.controller;

import com.msciq.storage.budgetCategory.service.BudgetCategoryService;
import com.msciq.storage.common.Constants;
import com.msciq.storage.common.SuccessCode;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.exception.BadRequestException;
import com.msciq.storage.model.BudgetCategory;
import com.msciq.storage.model.request.BudgetCategoryTemplateTypeMappingDTO;
import com.msciq.storage.model.request.BudgetCategoryWithParentGLInfo;
import com.msciq.storage.model.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/budget-category")
@Validated
public class BudgetCategoryController {

    @Autowired
    BudgetCategoryService budgetCategoryService;

    /**
     * Gets all budget categories.
     *
     * @return List of budgetCategory entity
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public SuccessResponse<List<BudgetCategory>> getAllBudgetCategories() {
        List<BudgetCategory> budgetCategories = budgetCategoryService.getAllBudgetCategories();
        return new SuccessResponse<List<BudgetCategory>>("Success", budgetCategories, null, HttpStatus.OK);
    }

    /**
     * This method is used to add a new BudgetCategory.
     *
     * @param budgetCategories
     * @return List of budgetCategory
     * @author Sivaranjani DS
     */
    @RequestMapping(method = RequestMethod.POST)
    public SuccessResponse<List<BudgetCategory>> addBudgetCategories(@RequestBody List<BudgetCategory> budgetCategories) {
        if (budgetCategories.isEmpty()) {
            throw new BadRequestException(19011);
        }
        List<BudgetCategory> savedBudgetCategories = budgetCategoryService.addBudgetCategories(budgetCategories);
        return new SuccessResponse<List<BudgetCategory>>
                (String.format(SuccessMessage.SUCCESSFULLY_SAVED, Constants.BUDGET_CATEGORY),
                        savedBudgetCategories,
                        null,
                        HttpStatus.CREATED);
    }

    /**
     * This method is used to map TemplateType to BudgetCategory.
     *
     * @param budgetCategoryTemplateTypeMapping
     * @return List of budgetCategory
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/assign-templates", method = RequestMethod.PUT)
    public SuccessResponse<BudgetCategory> mapTemplateTypesToBudgetCategory(@RequestBody BudgetCategoryTemplateTypeMappingDTO budgetCategoryTemplateTypeMapping) {
        if (Objects.isNull(budgetCategoryTemplateTypeMapping)) {
            throw new BadRequestException(19011);
        }
        BudgetCategory savedBudgetCategory = budgetCategoryService.mapTemplateTypesToBudgetCategory(budgetCategoryTemplateTypeMapping);
        return new SuccessResponse<BudgetCategory>
                (String.format(SuccessMessage.SUCCESSFULLY_SAVED, Constants.BUDGET_CATEGORY),
                        savedBudgetCategory,
                        null,
                        HttpStatus.CREATED);
    }

    /**
     * This method is used get list of budget categories by template type id
     *
     * @param templateTypeId
     * @return List of budgetCategory
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/{templateTypeId}/list", method = RequestMethod.GET)
    public SuccessResponse<List<BudgetCategory>> getBudgetCategoriesByTemplateType(@PathVariable Long templateTypeId) {
        return new SuccessResponse<List<BudgetCategory>>
                (SuccessCode.GET_BUDGET_CATEGORIES_SUCCESS,
                        budgetCategoryService.getBudgetCategoriesByTemplateType(templateTypeId),
                        HttpStatus.CREATED);
    }

    /**
     * This method is used get list of budget categories by template type id
     *
     * @param templateTypeId
     * @return List of budgetCategory with parent gl info
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/{templateTypeId}/list-with-parent-glaccounts", method = RequestMethod.GET)
    public SuccessResponse<List<BudgetCategoryWithParentGLInfo>> getBudgetCategoriesWIthParentChildGLByTemplateType(@PathVariable Long templateTypeId) {
        return new SuccessResponse<List<BudgetCategoryWithParentGLInfo>>
                (SuccessCode.GET_BUDGET_CATEGORIES_SUCCESS,
                        budgetCategoryService.getBudgetCategoriesWithParentAndChildGLInfoByTemplateType(templateTypeId),
                        HttpStatus.CREATED);
    }

}
