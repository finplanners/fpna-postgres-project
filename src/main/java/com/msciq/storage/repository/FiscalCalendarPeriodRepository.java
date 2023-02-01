package com.msciq.storage.repository;

import com.msciq.storage.common.entity.FiscalCalendarPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FiscalCalendarPeriodRepository extends JpaRepository<FiscalCalendarPeriod, Long> {

    public static final String DELETE_FISCAL_CALENDAR_PERIOD = "delete from fiscal_calendar_period where fc_key =:fcKey";

    public List<FiscalCalendarPeriod> findByFcKey(String fcKey);

//    @Query(value = DELETE_FISCAL_CALENDAR_PERIOD,nativeQuery = true)
//    @Modifying
    public void deleteByFcKey(String fcKey);



}
