package com.msciq.storage.service;

import com.msciq.storage.common.entity.*;
import com.msciq.storage.common.msciq.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

/**
 * This interface contains business logic for manipulating Country, Currency and
 * etc entities.
 *
 * @author Rajkumar
 */
public interface DataService {

	/**
	 * This method is used to add a new Country.
	 *
	 * @param countryDTO
	 * @return Country Entity
	 */
	Country addCountry(CountryDTO countryDTO);

	/**
	 * This method is used to update a country details like name, code.
	 *
	 * @param country
	 * @return country Entity
	 */
	Country updateCountry(Country country);

	/**
	 * Get all active countries using status active.
	 *
	 * @param isActive - active status
	 * @return List of country entities.
	 */
	List<CountryDTO> getAllCountries(Boolean isActive);

	/**
	 * Get country detail by Id.
	 *
	 * @param countryId - country Id
	 * @return Country entity
	 */
	Country findCountryById(Long countryId);

	/**
	 * This method is used to add a new Currency.
	 *
	 * @param currencyDTO - currency details
	 * @return Currency Entity
	 */
	Currency addCurrency(CurrencyDTO currencyDTO);

	/**
	 * This method is used to update a currency details.
	 *
	 * @param currency - currency details
	 * @return Currency Entity
	 */
	Currency updateCurrency(Currency currency);

	/**
	 * Get all active currencies using status active.
	 * 
	 * @param isActive - active status
	 * @return List of currency entities.
	 */
	List<CurrencyDTO> getAllCurrencies(Boolean isActive);

	/**
	 * Get currency detail by Id.
	 * 
	 * @param currencyId - Currency Id
	 * @return Currency entity
	 */
	Currency findCurrencyById(Long currencyId);

	/**
	 * This method is used to add a new department.
	 *
	 * @param departmentDTO - department details
	 * @return Department Entity
	 */
	List<Department> addDepartment(@Valid List<DepartmentDTO> departmentDTO);

	/**
	 * This method is used to update a department details.
	 *
	 * @param department - department details
	 * @return Department Entity
	 */
	Department updateDepartment(Department department);

	/**
	 * Get all active departments using status active.
	 * 
	 * @param isActive - active status
	 * @return List of Department entities.
	 */
	List<DepartmentDTO> getAllDepartment(Boolean isActive, Boolean isDeleted);

	/**
	 * Get department detail by Id.
	 * 
	 * @param departmentId - department Id
	 * @return Department entity
	 */
	Department findDepartmentById(Long departmentId);

	/**
	 * This method is used to add a new fiscal calendar
	 * 
	 * @param fiscalCalendarDTO - fiscal calendar details
	 * @return FiscalCalendar Entity
	 */
	FiscalCalendar addFiscalCalendar(FiscalCalendarDTO fiscalCalendarDTO);

	/**
	 * This method is used to update a fiscal calendar details.
	 *
	 * @param fiscalCalendar - fiscal calendar details
	 * @return FiscalCalendar Entity
	 */
	FiscalCalendar updateFiscalCalendar(FiscalCalendar fiscalCalendar);

	/**
	 * Get all active fiscal calendars using status active.
	 * 
	 * @param isActive - active status
	 * @return List of fiscal calendar entities.
	 */
	List<FiscalCalendarDTO> getAllFiscalCalendar(Boolean isActive);

	/**
	 * Get fiscal calendar detail by Id.
	 * 
	 * @param fiscalCalendarId - fiscal calendar id
	 * @return FiscalCalendar entity
	 */
	FiscalCalendar findFiscalCalendarById(Long fiscalCalendarId);

	/**
	 * This method is used to add a new group company
	 * 
	 * @param groupCompanyDTO - group company details
	 * @return GroupCompany Entity
	 */
	GroupCompany addGroupCompany(GroupCompanyDTO groupCompanyDTO);

	/**
	 * This method is used to update a group company details.
	 *
	 * @param groupCompany - group company details
	 * @return GroupCompany Entity
	 */
	GroupCompany updateGroupCompany(GroupCompany groupCompany);

	/**
	 * Get all active group company using status active.
	 * 
	 * @param isActive - active status
	 * @return List of group company entities.
	 */
	List<GroupCompanyDTO> getAllGroupCompany(Boolean isActive, Boolean isDeleted);

	/**
	 * Get group company detail by Id.
	 * 
	 * @param gcId - group company id
	 * @return GroupCompany entity
	 */
	GroupCompany findGroupCompanyById(Long gcId);

	/**
	 * This method is used to add a new company
	 * 
	 * @param companyDTOS - company details
	 * @return Company Entity
	 */
	List<Company> addCompany(List<CompanyDTO> companyDTOS);

	/**
	 * This method is used to update a company details.
	 *
	 * @param company - company details
	 * @return Company Entity
	 */
	Company updateCompany(Company company);

	/**
	 * Get all active company using status active.
	 * 
	 * @param isActive - active status
	 * @return List of company entities.
	 */
	List<CompanyDTO> getAllCompany(Boolean isActive);

	/**
	 * Get company detail by Id.
	 * 
	 * @param companyId - company id
	 * @return Company entity
	 */
	Company findCompanyById(Long companyId);

	/**
     * This method is used to add a new business unit
     *
     * @param businessUnits - business unit details
     * @return BusinessUnit Entity
     */
	List<BusinessUnit> addBU(List<BusinessUnitDTO> businessUnits);

	/**
	 * This method is used to update a business unit details.
	 *
	 * @param businessUnit - business unit details
	 * @return BusinessUnit Entity
	 */
	BusinessUnit updateBU(BusinessUnitDTO businessUnit);

	/**
	 * Get all active business unit using status active.
	 * 
	 * @param isActive - active status
	 * @return List of business unit entities.
	 */
	List<BusinessUnitDTO> getAllBU(Boolean isActive, Boolean isDeleted);

	/**
	 * Get business unit detail by Id.
	 * 
	 * @param buId - business unit id
	 * @return business unit entity
	 */
	BusinessUnit findBUById(Long buId);

	List<Location> addLocations(List<LocationDTO> locationDTO);

	List<Location> updateLocation(List<Location> location);

	Location findLocationById(long locationId);

	List<Location> getAllLocations(boolean isActive, boolean isDeleted);

	State addState(StateDTO stateDTO);

	List<Company> findCompanyByGroupCompanyId(long groupCompanyId);

	String inActivateOrDeleteBU(LockDeleteDTO lockDeleteDTO);

	/**
	 * Get all department based on the user email.
	 *
	 * @param email - email id of user
	 * @return List of Department entities.
	 */
	List<Department> getAllDepartmentByUser(String email);

	/**
	 * Add fiscal period.
	 * @param fcKey - key of Fiscal Calendar
	 *
	 * @return list of fiscal period  entity.
	 */
	List<FiscalCalendarPeriod> addFiscalPeriodEntity(String fcKey);

	/**
	 * Get all fiscal period
	 *
	 * @return List - list of fiscal period  entity
	 */
	List<FiscalCalendarPeriod> getAllFiscalPeriodEntity();

	String inActivateOrDeleteDept(LockDeleteDTO lockDeleteDTO);
}