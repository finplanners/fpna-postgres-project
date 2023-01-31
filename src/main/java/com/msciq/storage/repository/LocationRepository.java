package com.msciq.storage.repository;

import com.msciq.storage.common.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

    Location findByCode(String code);

    Location findByIdAndIsDeleted(Long id, boolean b);

    Location findById(Long id);

    List<Location> findByIsActive(boolean isActive);

    List<Location> findByIsActiveAndCompanyId(boolean b, long companyId);

    List<Location> findByIsActiveAndCompanyIdIn(boolean isActive, List<Long> companyIds);
}
