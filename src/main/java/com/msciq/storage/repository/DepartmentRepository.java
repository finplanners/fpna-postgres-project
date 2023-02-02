package com.msciq.storage.repository;

import java.util.List;

import com.msciq.storage.common.entity.Department;
import com.msciq.storage.common.msciq.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This is the repository interface helps maintain the DB connection to perform
 * operations on Department entity.
 *
 * @author Rajkumar
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	public static final String IS_DEPARTMENT_EXISTS = "select case when count(department) > 0 then true else false end from Department as department where department.id<>:id and (department.name=:departName or department.code=:departmentCode) and department.isDeleted=false";

	public static final String DEPARTMENT_BY_TEMPLATE_ID = "select * from department_template as dt, department d where dt.dept_id = d.id and dt.temp_id=:templateId and d.is_active=true";

	public static final String USER_DEPARTMENT = "select d.* from user_department ud inner join department d on ud.department_code = d.depart_code where ud.email =:email order by name";



	/**
	 * Retrieves an department based on its Id and IsDeleted fields.
	 * 
	 * @param id       - department id
	 * @param isActive - active status
	 * @return Department Entity.
	 */
	public Department findByIdAndIsDeleted(long id, boolean isActive);

	/**
	 * Check the given department exists based on id, name and department code
	 *
	 * @param id         - department id
	 * @param departName       - department name
	 * @param departmentCode - department code
	 * @return boolean - true or false
	 */
	@Query(value = IS_DEPARTMENT_EXISTS)
	public boolean isDepartmentExists(long id, String departName, String departmentCode);

	/**
	 * To find all active departments.
	 * 
	 * @param isActive - active status
	 * @return List of department Entities
	 */
	public List<Department> findByIsActive(Boolean isActive);

	public Department findByCode(String departmentCode);

	@Query(value = DEPARTMENT_BY_TEMPLATE_ID,
	nativeQuery = true)
	List<Department> findByTemplateId(Long templateId);

	/**
	 * To find all departments
	 *
	 * @param email - email
	 * @return List of Department Entities
	 */
	@Query(value = USER_DEPARTMENT,nativeQuery = true)
	List<Department> getAllDepartmentByUser(String email);

    Department findByCodeAndName(String departmentCode, String departName);

	List<Department> findByIdIn(List<Long> ids);

	List<Department> findByIsActiveAndIsDeleted(Boolean isActive, Boolean isDeleted);
}
