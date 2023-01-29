package com.msciq.storage.forecast.repository;

import com.msciq.storage.model.GLAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GLAccountRepository extends JpaRepository<GLAccount, Long> {

    public static final String GET_ALL_FORECASTING_TEMPLATES = " select templates from Template templates";

    @Query(value = GET_ALL_FORECASTING_TEMPLATES)
    List<GLAccount> getAllForecastingTemplates();

    List<GLAccount> findByBudgetCategory_IdIn(List<Long> budgetCategoryIds);
}
