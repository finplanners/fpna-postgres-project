package com.msciq.storage.forecast.repository;

import com.msciq.storage.model.GLAccount;
import com.msciq.storage.model.GLAccountGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GLAccountGroupRepository extends JpaRepository<GLAccountGroup, Long> {

}
