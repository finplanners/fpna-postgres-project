package com.msciq.storage.controller;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.SuccessCode;
import com.msciq.storage.common.entity.*;
import com.msciq.storage.common.msciq.*;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.service.DataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * This controller class helps to perform actions on Country, Currency and meta
 * data Entities.
 *
 * @author Rajkumar
 */
@RestController
@RequestMapping(value = "/data")
@Validated
public class DataController {

	private static final List<String> noDataList = Arrays.asList(Constants.NO_DATA_FOUND);

	@Autowired
	private DataService dataService;

	ModelMapper modelMapper = new ModelMapper();

	/**
	 * Add a new currency with name and description
	 *
	 * @param currencyDTO - currency
	 * @return Currency Entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/currency")
	public SuccessResponse<Currency> addCurrency(@Valid @RequestBody CurrencyDTO currencyDTO) {
		Currency currency = dataService.addCurrency(currencyDTO);
		return new SuccessResponse<>(SuccessCode.CURRENCY_SAVE, currency, HttpStatus.CREATED);
	}

	/**
	 * Update a currency,
	 *
	 * @param currency - currency detail
	 * @return country Entity
	 * @author Rajkumar
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/currency")
	public SuccessResponse<Currency> updateCurrency(@Valid @RequestBody Currency currency) {
		currency = dataService.updateCurrency(currency);
		return new SuccessResponse<>(SuccessCode.CURRENCY_UPDATE, currency, HttpStatus.OK);
	}

	/**
	 * Get currency detail by currency Id.
	 *
	 * @param currencyId - currency Id
	 * @return currency entity
	 */
	@GetMapping(value = "/currency/{id}")
	public SuccessResponse<CurrencyDTO> getCurrency(@PathVariable(value = "id") long currencyId) {
		Currency currency = dataService.findCurrencyById(currencyId);
		return new SuccessResponse<>(SuccessCode.GET_CURRENCY_SUCCESS, currency, HttpStatus.OK);
	}

	/**
	 * Get all active currency details
	 *
	 * @return List - list of Currency
	 */
	@RequestMapping(value = "/currencies", method = RequestMethod.GET)
	public SuccessResponse<List<CurrencyDTO>> getCurrencies() {
		List<CurrencyDTO> currencies = dataService.getAllCurrencies(true);
		return new SuccessResponse<List<CurrencyDTO>>(SuccessCode.GET_CURRENCIES_SUCCESS, currencies, HttpStatus.OK);
	}

	/**
	 * Add a new Department with name, short code, etc.
	 *
	 * @param departmentDTO- Department details
	 * @return Department Entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/department")
	public SuccessResponse<Department> addDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
		Department department = dataService.addDepartment(departmentDTO);
		return new SuccessResponse<>(SuccessCode.DEPARTMENT_SAVE, department, HttpStatus.CREATED);
	}

	/**
	 * Update a department details like name, code, etc,
	 *
	 * @param department - department detail
	 * @return Department Entity
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/department")
	public SuccessResponse<Department> updateDepartment(@Valid @RequestBody Department department) {
		department = dataService.updateDepartment(department);
		return new SuccessResponse<>(SuccessCode.DEPARTMENT_UPDATE, department, HttpStatus.OK);
	}

	/**
	 * Get department detail by department Id.
	 *
	 * @param departmentId - department Id
	 * @return department entity
	 */
	@GetMapping(value = "/department/{id}")
	public SuccessResponse<Department> getDepartment(@PathVariable(value = "id") long departmentId) {
		Department department = dataService.findDepartmentById(departmentId);
		return new SuccessResponse<>(SuccessCode.GET_DEPARTMENT_SUCCESS, department, HttpStatus.OK);
	}

	/**
	 * Get all active department details
	 *
	 * @return List - list of Department entity
	 */
	@RequestMapping(value = "/departments", method = RequestMethod.GET)
	public SuccessResponse<List<DepartmentDTO>> getAllDepartment() {
		List<DepartmentDTO> departments = dataService.getAllDepartment(true);
		return new SuccessResponse<List<DepartmentDTO>>(SuccessCode.GET_DEPARTMENTS_SUCCESS, departments,
				HttpStatus.OK);
	}

	/**
	 * Add a new Fiscal calendar with key, description, etc.
	 *
	 * @param fiscalCalendarDTO - fiscal calendar details
	 * @return FiscalCalendar Entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/fiscalCalendar")
	public SuccessResponse<FiscalCalendar> addFiscalCalendar(@Valid @RequestBody FiscalCalendarDTO fiscalCalendarDTO) {
		FiscalCalendar fiscalCalendar = dataService.addFiscalCalendar(fiscalCalendarDTO);
		return new SuccessResponse<>(SuccessCode.FISCAL_DEPT_SAVE, fiscalCalendar, HttpStatus.CREATED);
	}

	/**
	 * Update a Fiscal calendar details like name, code, etc,
	 *
	 * @param fiscalCalendar - fiscal calendar detail
	 * @return FiscalCalendar Entity
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/fiscalCalendar")
	public SuccessResponse<FiscalCalendar> updateFiscalCalendar(@Valid @RequestBody FiscalCalendar fiscalCalendar) {
		fiscalCalendar = dataService.updateFiscalCalendar(fiscalCalendar);
		return new SuccessResponse<>(SuccessCode.FISCAL_DEPT_UPDATE, fiscalCalendar, HttpStatus.OK);
	}

	/**
	 * Get fiscal calendar detail by department Id.
	 *
	 * @param fiscalCalendarId - fiscal calendar id
	 * @return FiscalCalendar entity
	 */
	@GetMapping(value = "/fiscalCalendar/{id}")
	public SuccessResponse<FiscalCalendar> getFiscalCalendar(@PathVariable(value = "id") long fiscalCalendarId) {
		FiscalCalendar fiscalCalendar = dataService.findFiscalCalendarById(fiscalCalendarId);
		return new SuccessResponse<>(SuccessCode.GET_FISCAL_DEPT_SUCCESS, fiscalCalendar, HttpStatus.OK);
	}

	/**
	 * Get all active fiscal calendar details
	 *
	 * @return List - list of FiscalCalendar entity
	 */
	@RequestMapping(value = "/fiscalCalendars", method = RequestMethod.GET)
	public SuccessResponse<List<FiscalCalendarDTO>> getAllFiscalCalendar() {
		List<FiscalCalendarDTO> fiscalCalendars = dataService.getAllFiscalCalendar(true);
		return new SuccessResponse<List<FiscalCalendarDTO>>(SuccessCode.GET_FISCAL_DEPTS_SUCCESS, fiscalCalendars,
				HttpStatus.OK);
	}

	/**
	 * Add a new group company with GC name, GC code, etc.
	 *
	 * @param groupCompanyDTO - group company details
	 * @return Group Company Entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/groupCompany")
	public SuccessResponse<GroupCompany> addGroupCompany(@Valid @RequestBody GroupCompanyDTO groupCompanyDTO) {
		GroupCompany groupCompany = dataService.addGroupCompany(groupCompanyDTO);
		return new SuccessResponse<>(SuccessCode.GC_SAVE, groupCompany, HttpStatus.CREATED);
	}

	/**
	 * Update a group company details like name, code, etc,
	 *
	 * @param groupCompany - group company detail
	 * @return GroupCompany - group company Entity
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/groupCompany")
	public SuccessResponse<GroupCompany> updateGroupCompany(@Valid @RequestBody GroupCompany groupCompany) {
		groupCompany = dataService.updateGroupCompany(groupCompany);
		return new SuccessResponse<>(SuccessCode.GC_UPDATE, groupCompany, HttpStatus.OK);
	}

	/**
	 * Get group company detail by gc Id.
	 * 
	 * @param gcId - group company id
	 * @return GroupCompany - group company entity
	 */
	@GetMapping(value = "/groupCompany/{id}")
	public SuccessResponse<GroupCompany> getGroupCompany(@PathVariable(value = "id") long gcId) {
		GroupCompany groupCompany = dataService.findGroupCompanyById(gcId);
		return new SuccessResponse<>(SuccessCode.GET_GC_SUCCESS, groupCompany, HttpStatus.OK);
	}

	/**
	 * Get all active group company details
	 * 
	 * @return List - list of group company entity
	 */
	@RequestMapping(value = "/groupCompanies", method = RequestMethod.GET)
	public SuccessResponse<List<GroupCompanyDTO>> getAllGroupCompany() {
		List<GroupCompanyDTO> GroupCompanys = dataService.getAllGroupCompany(true);
		return new SuccessResponse<List<GroupCompanyDTO>>(SuccessCode.GET_GC_LIST_SUCCESS, GroupCompanys, HttpStatus.OK);
	}

	/**
	 * Add a new company with company name, code, etc.
	 *
	 * @param companyDTO - company details
	 * @return company Entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/company")
	public SuccessResponse<Company> addCompany(@Valid @RequestBody CompanyDTO companyDTO) {
		Company company = dataService.addCompany(companyDTO);
		return new SuccessResponse<>(SuccessCode.COMPANY_SAVE, company, HttpStatus.CREATED);
	}

	/**
	 * Update a company details like name, code, etc,
	 *
	 * @param company - company detail
	 * @return Company - company Entity
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/company")
	public SuccessResponse<Company> updateCompany(@Valid @RequestBody Company company) {
		company = dataService.updateCompany(company);
		return new SuccessResponse<>(SuccessCode.COMPANY_UPDATE, company, HttpStatus.OK);
	}

	/**
	 * Get company detail by company Id.
	 *
	 * @param companyId - company id
	 * @return Company - company entity
	 */
	@GetMapping(value = "/company/{id}")
	public SuccessResponse<Company> getCompany(@PathVariable(value = "id") long companyId) {
		Company company = dataService.findCompanyById(companyId);
		return new SuccessResponse<>(SuccessCode.GET_COMPANY_SUCCESS, company, HttpStatus.OK);
	}

	/**
	 * Get all active company details
	 *
	 * @return List - list of company entity
	 */
	@RequestMapping(value = "/companies", method = RequestMethod.GET)
	public SuccessResponse<List<CompanyDTO>> getAllCompany() {
		List<CompanyDTO> GroupCompanys = dataService.getAllCompany(true);
		return new SuccessResponse<List<CompanyDTO>>(SuccessCode.GET_COMPANY_LIST_SUCCESS, GroupCompanys,
				HttpStatus.OK);
	}


	/**
	 * Add a new business unit with name, code, etc.
	 *
	 * @param businessUnitDTO - business unit details
	 * @return business unit Entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/businessUnit")
	public SuccessResponse<BusinessUnit> adBU(@Valid @RequestBody BusinessUnitDTO businessUnitDTO) {
		BusinessUnit businessUnit = dataService.addBU(businessUnitDTO);
		return new SuccessResponse<>(SuccessCode.BU_SAVE, businessUnit, HttpStatus.CREATED);
	}

	/**
	 * Update a business unit details like name, code, etc,
	 *
	 * @param businessUnit - business unit detail
	 * @return BusinessUnit - business unit Entity
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/businessUnit")
	public SuccessResponse<BusinessUnit> updateBU(@Valid @RequestBody BusinessUnit businessUnit) {
		businessUnit = dataService.updateBU(businessUnit);
		return new SuccessResponse<>(SuccessCode.BU_UPDATE, businessUnit, HttpStatus.OK);
	}

	/**
	 * Get business unit detail by company Id.
	 *
	 * @param buId - business unit id
	 * @return BusinessUnit - business unit entity
	 */
	@GetMapping(value = "/businessUnit/{id}")
	public SuccessResponse<BusinessUnit> getBU(@PathVariable(value = "id") long buId) {
		BusinessUnit businessUnit = dataService.findBUById(buId);
		return new SuccessResponse<>(SuccessCode.GET_BU_SUCCESS, businessUnit, HttpStatus.OK);
	}

	/**
	 * Get all active business unit details
	 *
	 * @return List - list of business unit entity
	 */
	@RequestMapping(value = "/businessUnits", method = RequestMethod.GET)
	public SuccessResponse<List<BusinessUnitDTO>> getAllBU() {
		List<BusinessUnitDTO> businessUnits = dataService.getAllBU(true);
		return new SuccessResponse<List<BusinessUnitDTO>>(SuccessCode.GET_BU_LIST_SUCCESS, businessUnits,
				HttpStatus.OK);
	}
}