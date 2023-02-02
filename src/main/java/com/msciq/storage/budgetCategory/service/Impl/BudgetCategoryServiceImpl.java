package com.msciq.storage.budgetCategory.service.Impl;

import com.msciq.storage.budgetCategory.repository.BudgetCategoryRepository;
import com.msciq.storage.budgetCategory.service.BudgetCategoryService;
import com.msciq.storage.forecastPrerequisites.repository.GLAccountGroupRepository;
import com.msciq.storage.model.BudgetCategory;
import com.msciq.storage.model.GLAccountGroup;
import com.msciq.storage.model.TemplateType;
import com.msciq.storage.model.request.BudgetCategoryTemplateTypeMappingDTO;
import com.msciq.storage.model.request.BudgetCategoryWithParentGLInfo;
import com.msciq.storage.model.request.ParentGLAccount;
import com.msciq.storage.templateType.repository.TemplateTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BudgetCategoryServiceImpl implements BudgetCategoryService {

    @Autowired
    private BudgetCategoryRepository budgetCategoryRepository;

    @Autowired
    private GLAccountGroupRepository glAccountGroupRepository;

    @Autowired
    private TemplateTypeRepository templateTypeRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BudgetCategory> getAllBudgetCategories() {
        return budgetCategoryRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BudgetCategory> addBudgetCategories(List<BudgetCategory> budgetCategories) {
        budgetCategories.stream().forEach(budgetCategory -> {
            List<GLAccountGroup> accountGroups = new ArrayList<>();
            budgetCategory.getGlAccountList().stream().forEach(glAccountGroup -> {
                accountGroups.add(glAccountGroupRepository.findById(glAccountGroup.getId()).get());
            });
            budgetCategory.setGlAccountList(accountGroups);
        });
        return budgetCategoryRepository.saveAll(budgetCategories);
    }

    @Override
    public BudgetCategory mapTemplateTypesToBudgetCategory(BudgetCategoryTemplateTypeMappingDTO budgetCategoryTemplateTypeMapping) {
        Optional<BudgetCategory> budgetCategory = budgetCategoryRepository.findById(budgetCategoryTemplateTypeMapping.getBudgetCategoryId());
        if (budgetCategory.isPresent()) {
            Set<TemplateType> templateTypes = new HashSet<>();
            budgetCategoryTemplateTypeMapping.getTemplateTypes().stream().forEach(budgetCategoryTemplateType -> {
                templateTypes.add(templateTypeRepository.findById(budgetCategoryTemplateType.getId()).get());
            });
            budgetCategory.get().setTemplateTypes(templateTypes);
            return budgetCategoryRepository.save(budgetCategory.get());
        }
        return null;
    }

    @Override
    public List<BudgetCategory> getBudgetCategoriesByTemplateType(Long templateTypeId) {
        List<BudgetCategory> allBudgetCategories = budgetCategoryRepository.findByTemplateTypes_Id(templateTypeId);
        return allBudgetCategories;
    }

    @Override
    public List<BudgetCategoryWithParentGLInfo> getBudgetCategoriesWithParentAndChildGLInfoByTemplateType(Long templateTypeId) {
        List<BudgetCategory> allBudgetCategories = budgetCategoryRepository.findByTemplateTypes_Id(templateTypeId);
        List<BudgetCategoryWithParentGLInfo> budgetCategoryWithParentGLInfoList = new ArrayList<>();

        allBudgetCategories.stream().forEach(budgetCategory -> {
            List<ParentGLAccount> parentGLAccounts = new ArrayList<>();
            budgetCategory.getGlAccountList().stream().forEach(glAccountGroup -> {
                if (Objects.isNull(glAccountGroup.getParentGlAccount())) {
                    parentGLAccounts.add(ParentGLAccount.builder()
                            .accountDescription(glAccountGroup.getAccountDescription())
                            .accountType(glAccountGroup.getAccountType())
                            .parentGlAccount(glAccountGroup.getParentGlAccount())
                            .glAccountCode(glAccountGroup.getGlAccountCode())
                            .glGroupCodeName(glAccountGroup.getGlGroupCodeName())
                            .activeFromDate(glAccountGroup.getActiveFromDate())
                            .inactiveDate(glAccountGroup.getInactiveDate())
                            .accountDescription(glAccountGroup.getAccountDescription())
                            .childGLAccounts(glAccountGroupRepository.findAllByParentGlAccount(glAccountGroup.getGlAccountCode()))
                            .build()
                    );
                }
            });
            budgetCategoryWithParentGLInfoList.add(BudgetCategoryWithParentGLInfo.builder()
                    .budgetCategoryId(budgetCategory.getId())
                    .parentGLAccount(parentGLAccounts)
                    .build());
        });
        return budgetCategoryWithParentGLInfoList;
    }
}
