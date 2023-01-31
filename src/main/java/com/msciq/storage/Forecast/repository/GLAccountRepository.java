package com.msciq.storage.forecast.repository;

import com.msciq.storage.model.GLAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GLAccountRepository extends JpaRepository<GLAccount, Long> {

}
