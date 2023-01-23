package com.msciq.storage.profitCenter.repository;

import com.msciq.storage.model.ProfitCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitCenterRepository extends JpaRepository<ProfitCenter, Long> {

}
