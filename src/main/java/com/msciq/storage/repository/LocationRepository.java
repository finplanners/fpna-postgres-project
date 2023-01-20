package com.msciq.storage.repository;

import com.msciq.storage.common.entity.Currency;
import com.msciq.storage.common.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

    Location findByCode(String code);

    Location findByIdAndIsDeleted(Long id, boolean b);

    List<Location> findByIsActive(boolean isActive);
}
