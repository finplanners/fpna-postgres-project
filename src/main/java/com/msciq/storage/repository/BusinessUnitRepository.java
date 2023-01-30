package com.msciq.storage.repository;

import java.util.List;

import com.msciq.storage.common.entity.BusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the repository interface helps maintain the DB connection to perform
 * operations on business unit entity.
 *
 * @author Rajkumar
 */
public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, Long> {

	public static final String IS_BU_EXISTS = "select case when count(bu) > 0 then true else false end from BusinessUnit as bu where bu.id<>:id and bu.code=:code and bu.isDeleted=false";

	/**
	 * Retrieves an business unit based on its Id and IsDeleted fields.
	 * 
	 * @param id       - business unit id
	 * @param isDeleted - deleted status
	 * @return BusinessUnit Entity.
	 */
	public BusinessUnit findByIdAndIsDeleted(Long id, Boolean isDeleted);

	/**
	 * Finds the business unit based on business unit code
	 *
	 * @param code - business unit code
	 * @return BusinessUnit
	 */
	public BusinessUnit findByCode(String code);

	/**
	 * Check the given business unit exists based on id, key
	 *
	 * @param id     - business unit id
	 * @param code - business unit code
	 * @return boolean - true or false
	 */
	@Query(value = IS_BU_EXISTS)
	public boolean isBUExists(Long id, String code);

	/**
	 * To find all active business units
	 * 
	 * @param isActive - active status
	 * @return List of business unit Entities
	 */
	public List<BusinessUnit> findByIsActive(Boolean isActive);

	List<BusinessUnit> findByIdIn(List<Long> ids);
}
