package com.msciq.storage.forecastPrerequisites.repository;

import com.msciq.storage.model.GLAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GLAccountRepository extends JpaRepository<GLAccount, Long> {
    List<GLAccount> findAllByForecastingRelevant(boolean isForecastingRelevant);
}
