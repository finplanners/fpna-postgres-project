package com.msciq.storage.forecastPrerequisites.repository;

import com.msciq.storage.model.GLAccountGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GLAccountGroupRepository extends JpaRepository<GLAccountGroup, Long> {
    List<GLAccountGroup> findAllByParentGlAccount(Long glAccountCode);
}
