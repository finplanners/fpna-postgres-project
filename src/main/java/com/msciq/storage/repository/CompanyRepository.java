package com.msciq.storage.repository;

import java.util.List;

import com.msciq.storage.common.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the repository interface helps maintain the DB connection to perform
 * operations on company entity.
 *
 * @author Rajkumar
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

	public static final String IS_COMPANY_EXISTS = "select case when count(company) > 0 then true else false end from Company as company where company.id<>:id and company.code=:code and company.isDeleted=false";

	/**
	 * Retrieves an company based on its Id and IsDeleted fields.
	 * 
	 * @param id       - company id
	 * @param isActive - active status
	 * @return Company Entity.
	 */
	public Company findByIdAndIsDeleted(Long id, Boolean isActive);

	/**
	 * Finds the company based on fiscal calendar key
	 *
	 * @param code - company code
	 * @return Company
	 */
	public Company findByCode(String code);

	/**
	 * Check the given company exists based on id, key
	 *
	 * @param id     - company id
	 * @param code - company code
	 * @return boolean - true or false
	 */
	@Query(value = IS_COMPANY_EXISTS)
	public boolean isCompanyExists(Long id, String code);

	/**
	 * To find all active group companies
	 * 
	 * @param isActive - active status
	 * @return List of company Entities
	 */
	public List<Company> findByIsActive(Boolean isActive);

}
