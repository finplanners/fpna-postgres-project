package com.msciq.storage.repository;

import java.util.List;

import com.msciq.storage.common.entity.GroupCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the repository interface helps maintain the DB connection to perform
 * operations on group company entity.
 *
 * @author Rajkumar
 */
public interface GroupCompanyRepository extends JpaRepository<GroupCompany, Long> {

	public static final String IS_GC_EXISTS = "select case when count(groupCompany) > 0 then true else false end from GroupCompany as groupCompany where groupCompany.id<>:id and groupCompany.gcCode=:gcCode and groupCompany.isDeleted=false";

	/**
	 * Retrieves an group company based on its Id and IsDeleted fields.
	 * 
	 * @param id       - group company id
	 * @param isDeleted - isDeleted status
	 * @return group company unit Entity.
	 */
	public GroupCompany findByIdAndIsDeleted(Long id, Boolean isDeleted);

	/**
	 * Finds the group company based on fiscal calendar key
	 *
	 * @param gcCode - group company code
	 * @return GroupCompany
	 */
	public GroupCompany findByGcCode(String gcCode);

	/**
	 * Check the given group company exists based on id, key
	 *
	 * @param id     - group company id
	 * @param gcCode - group company code
	 * @return boolean - true or false
	 */
	@Query(value = IS_GC_EXISTS)
	public boolean isGroupCompanyExists(Long id, String gcCode);

	/**
	 * To find all active group companies
	 * 
	 * @param isActive - active status
	 * @return List of group company Entities
	 */
	public List<GroupCompany> findByIsActive(Boolean isActive);

	/**
	 * To find all active group companies
	 *
	 * @param gcName,gcCode - gcName group company Name and code
	 * @return List of group company Entities
	 */
	GroupCompany findByGcCodeAndGcName(String gcCode, String gcName);
}
