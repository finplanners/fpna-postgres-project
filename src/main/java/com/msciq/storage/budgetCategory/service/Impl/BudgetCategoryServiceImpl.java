package com.msciq.storage.budgetCategory.service.Impl;

import com.msciq.storage.budgetCategory.repository.BudgetCategoryRepository;
import com.msciq.storage.budgetCategory.service.BudgetCategoryService;
import com.msciq.storage.model.BudgetCategory;
import com.msciq.storage.model.TemplateType;
import com.msciq.storage.model.request.BudgetCategoryTemplateTypeMappingDTO;
import com.msciq.storage.templateType.repository.TemplateTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetCategoryServiceImpl implements BudgetCategoryService {

    @Autowired
    private BudgetCategoryRepository budgetCategoryRepository;

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
        return budgetCategoryRepository.saveAll(budgetCategories);
    }

    @Override
    public BudgetCategory mapTemplateTypesToBudgetCategory(BudgetCategoryTemplateTypeMappingDTO budgetCategoryTemplateTypeMapping) {
        Optional<BudgetCategory> budgetCategory = budgetCategoryRepository.findById(budgetCategoryTemplateTypeMapping.getBudgetCategoryId());
        if (budgetCategory.isPresent()) {
            List<TemplateType> templateTypes = new ArrayList<>();
            budgetCategoryTemplateTypeMapping.getTemplateTypes().stream().forEach(budgetCategoryTemplateType -> {
                templateTypes.add(templateTypeRepository.findById(budgetCategoryTemplateType.getId()).get());
            });
            budgetCategory.get().setTemplateTypes(templateTypes);
            return budgetCategoryRepository.save(budgetCategory.get());
        }
        return null;
    }
}
