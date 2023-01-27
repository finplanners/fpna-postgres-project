package com.msciq.storage.budgetCategory.repository;

import com.msciq.storage.model.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Long> {

    List<BudgetCategory> findByTemplateTypesIn(List<Long> templateTypeId);
}
