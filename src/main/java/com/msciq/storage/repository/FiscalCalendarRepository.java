package com.msciq.storage.repository;

import com.msciq.storage.common.entity.FiscalCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * This is the repository interface helps maintain the DB connection to perform
 * operations on fiscal calendar entity.
 *
 * @author Rajkumar
 */
public interface FiscalCalendarRepository extends JpaRepository<FiscalCalendar, Long> {

	public static final String IS_FISCAL_CAL_EXISTS = "select case when count(fiscalCalendar) > 0 then true else false end from FiscalCalendar as fiscalCalendar where fiscalCalendar.id<>:id and fiscalCalendar.key=:key and fiscalCalendar.isDeleted=false";

	/**
	 * Retrieves an fiscal calendar based on its Id and IsDeleted fields.
	 * 
	 * @param id       - fiscal calendar id
	 * @param isActive - active status
	 * @return FiscalCalendar Entity.
	 */
	public FiscalCalendar findByIdAndIsDeleted(long id, boolean isActive);

	/**
	 * Finds the fiscal calendar based on fiscal calendar key
	 *
	 * @param key - fiscal calendar key
	 * @return FiscalCalendar
	 */
	public FiscalCalendar findByKey(String key);

	/**
	 * Check the given fiscal calendar exists based on id, key
	 *
	 * @param id         - fiscal calendar id
	 * @param key       - fiscal calendar key
	 * @return boolean - true or false
	 */
	@Query(value = IS_FISCAL_CAL_EXISTS)
	public boolean isFiscalCalendarExists(long id, String key);

	/**
	 * To find all active fiscal calendars.
	 * 
	 * @param isActive - active status
	 * @return List of fiscal calendar Entities
	 */
	public List<FiscalCalendar> findByIsActive(Boolean isActive);
}
