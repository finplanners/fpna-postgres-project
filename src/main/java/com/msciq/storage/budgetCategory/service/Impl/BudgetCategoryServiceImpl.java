package com.msciq.storage.budgetCategory.service.Impl;

import com.msciq.storage.budgetCategory.repository.BudgetCategoryRepository;
import com.msciq.storage.budgetCategory.service.BudgetCategoryService;
import com.msciq.storage.forecastPrerequisites.repository.GLAccountGroupRepository;
import com.msciq.storage.model.BudgetCategory;
import com.msciq.storage.model.GLAccountGroup;
import com.msciq.storage.model.Template;
import com.msciq.storage.model.request.BudgetCategoryTemplateMappingDTO;
import com.msciq.storage.model.request.BudgetCategoryWithParentGLInfo;
import com.msciq.storage.model.request.ParentGLAccount;
import com.msciq.storage.template.repository.TemplateRepository;
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
    private TemplateRepository templateRepository;

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
    public BudgetCategory mapTemplateToBudgetCategory(BudgetCategoryTemplateMappingDTO budgetCategoryTemplateMapping) {
        Optional<BudgetCategory> budgetCategory = budgetCategoryRepository.findById(budgetCategoryTemplateMapping.getBudgetCategoryId());
        if (budgetCategory.isPresent()) {
            Set<Template> templates = new HashSet<>();
            budgetCategoryTemplateMapping.getTemplateTypes().stream().forEach(budgetCategoryTemplate -> {
                templates.add(templateRepository.findById(budgetCategoryTemplate.getId()).get());
            });
            budgetCategory.get().setTemplates(templates);
            return budgetCategoryRepository.save(budgetCategory.get());
        }
        return null;
    }

    @Override
    public List<BudgetCategory> getBudgetCategoriesByTemplate(Long templateId) {
        List<BudgetCategory> allBudgetCategories = budgetCategoryRepository.findByTemplates_Id(templateId);
        return allBudgetCategories;
    }

    @Override
    public List<BudgetCategoryWithParentGLInfo> getBudgetCategoriesWithParentAndChildGLInfoByTemplateType(Long templateId) {
        List<BudgetCategory> allBudgetCategories = budgetCategoryRepository.findByTemplates_Id(templateId);
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
