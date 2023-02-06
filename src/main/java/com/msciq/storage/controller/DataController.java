package com.msciq.storage.controller;

import com.google.common.reflect.TypeToken;
import com.msciq.storage.common.Constants;
import com.msciq.storage.common.SuccessCode;
import com.msciq.storage.common.entity.*;
import com.msciq.storage.common.msciq.*;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.repository.CompanyRepository;
import com.msciq.storage.repository.UserRepository;
import com.msciq.storage.service.DataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This controller class helps to perform actions on Location, Country, Currency and meta
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
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private UserRepository userRepository;

	/**
	 * Add a new country with details like location name, code.
	 *
	 * @param locationDTOS
	 * @return List of Locations Entities
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/location")
	public SuccessResponse<List<Location>> addLocation(@Valid @RequestBody List<LocationDTO> locationDTOS) {
		List<Location> locations = dataService.addLocations(locationDTOS);
		return new SuccessResponse<List<Location>>(SuccessCode.LOCATION_SAVE, locations, HttpStatus.CREATED);
	}

	/**
	 * Update a country detail like name, etc.,
	 *
	 * @param locations
	 * @return country Entity
	 * @author Rajkumar
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/location")
	public SuccessResponse<List<Location>> updateLocation(@Valid @RequestBody List<Location> locations) {
		locations = dataService.updateLocation(locations);
		return new SuccessResponse<List<Location>>(SuccessCode.LOCATION_UPDATE, locations, HttpStatus.OK);
	}

	/**
	 * Get country detail by country Id.
	 *
	 * @param locationId - location Id
	 * @return Country entity
	 */
	@GetMapping(value = "/location/{id}")
	public SuccessResponse<Location> getLocation(@PathVariable(value = "id") long locationId) {
		Location location = dataService.findLocationById(locationId);
		return new SuccessResponse<>(SuccessCode.GET_LOCATION_SUCCESS, location, HttpStatus.OK);
	}

	/**
	 * Get all active country details
	 *
	 * @return List - list of Country entity
	 */
	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public SuccessResponse<List<LocationDTO>> getLocations() {
		List<Location> locations = dataService.getAllLocations(true, false);
		return new SuccessResponse<List<LocationDTO>>(SuccessCode.GET_LOCATIONS_SUCCESS, locations, HttpStatus.OK);
	}

	/**
	 * Get all active country details
	 *
	 * @return List - list of Country entity
	 */
	@RequestMapping(value = "/locations/prerequisites", method = RequestMethod.GET)
	public SuccessResponse<LocationPrerequisiteDTO> getLocationPrerequisites() {
		LocationPrerequisiteDTO locationPrerequisiteDTO = new LocationPrerequisiteDTO();
		dataService.getAllCompany(true);
		List<CountryDTO> countries = dataService.getAllCountries(true);
		List<CompanyDTO> companies = dataService.getAllCompany(true);
		locationPrerequisiteDTO.setCompanies(companies);
		locationPrerequisiteDTO.setCountries(countries);
		return new SuccessResponse<LocationPrerequisiteDTO>(SuccessCode.GET_LOCATIONS_SUCCESS, locationPrerequisiteDTO, HttpStatus.OK);
	}

	/**
	 * Add a new country with details like country name, code.
	 *
	 * @param countryDTO
	 * @return Country Entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/country")
	public SuccessResponse<Country> addCountry(@Valid @RequestBody CountryDTO countryDTO) {
		Country country = dataService.addCountry(countryDTO);
		return new SuccessResponse<>(SuccessCode.COUNTRY_SAVE, country, HttpStatus.CREATED);
	}

	/**
	 * Add a new country with details like country name, code.
	 *
	 * @param stateDTO
	 * @return Country Entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/state")
	public SuccessResponse<State> addCountry(@Valid @RequestBody StateDTO stateDTO) {
		State state = dataService.addState(stateDTO);
		return new SuccessResponse<>(SuccessCode.COUNTRY_SAVE, state, HttpStatus.CREATED);
	}

	/**
	 * Update a country detail like name, etc.,
	 *
	 * @param country
	 * @return country Entity
	 * @author Rajkumar
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/country")
	public SuccessResponse<Country> updateCountry(@Valid @RequestBody Country country) {
		country = dataService.updateCountry(country);
		return new SuccessResponse<>(SuccessCode.COUNTRY_UPDATE, country, HttpStatus.OK);
	}

	/**
	 * Get country detail by country Id.
	 *
	 * @param countryId - country Id
	 * @return Country entity
	 */
	@GetMapping(value = "/country/{id}")
	public SuccessResponse<Country> getCountry(@PathVariable(value = "id") long countryId) {
		Country country = dataService.findCountryById(countryId);
		return new SuccessResponse<>(SuccessCode.GET_COUNTRY_SUCCESS, country, HttpStatus.OK);
	}

	/**
	 * Get all active country details
	 *
	 * @return List - list of Country entity
	 */
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public SuccessResponse<List<CountryDTO>> getCountries() {
		List<CountryDTO> countries = dataService.getAllCountries(true);
		return new SuccessResponse<List<CountryDTO>>(SuccessCode.GET_COUNTRIES_SUCCESS, countries, HttpStatus.OK);
	}

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
	 * @param departmentDTOS- Departments details
	 * @return Department Entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/department")
	public SuccessResponse<List<Department>> addDepartment(@Valid @RequestBody List<DepartmentDTO> departmentDTOS) {
		List<Department> departments = dataService.addDepartment(departmentDTOS);
		return new SuccessResponse<List<Department>>(SuccessCode.DEPARTMENT_SAVE, departments, HttpStatus.CREATED);
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
	 * Update a department details like name, code, etc,
	 *
	 * @param lockDeleteDTO - department detail
	 * @return BusinessUnit - department Entity
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/department/inActivateOrDelete")
	public SuccessResponse<String> inActivateOrDeleteDept(@Valid @RequestBody LockDeleteDTO lockDeleteDTO) {
		String message = dataService.inActivateOrDeleteDept(lockDeleteDTO);
		return new SuccessResponse<>(SuccessCode.BU_UPDATE, message, HttpStatus.OK);
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
		List<DepartmentDTO> departments = dataService.getAllDepartment(true, false);
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
	 * Get all active company details
	 *
	 * @return List - list of company entity
	 */
	@RequestMapping(value = "/groupCompany/prerequisites", method = RequestMethod.GET)
	public SuccessResponse<GroupCompanyPrerequisiteDTO> getPrerequisiteGroupCompanies() {
		List<CurrencyDTO> currencies = dataService.getAllCurrencies(true);
		List<CountryDTO> countries = dataService.getAllCountries(true);

		GroupCompanyPrerequisiteDTO groupCompanyPrerequisiteDTO = new GroupCompanyPrerequisiteDTO();
		groupCompanyPrerequisiteDTO.setCurrencies(currencies);
		groupCompanyPrerequisiteDTO.setCountries(countries);

		return new SuccessResponse<GroupCompanyPrerequisiteDTO>(SuccessCode.GET_COMPANY_LIST_SUCCESS, groupCompanyPrerequisiteDTO,
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
		List<GroupCompanyDTO> GroupCompanys = dataService.getAllGroupCompany(true, false);
		return new SuccessResponse<List<GroupCompanyDTO>>(SuccessCode.GET_GC_LIST_SUCCESS, GroupCompanys, HttpStatus.OK);
	}

	/**
	 * Get group company and company details by gc Id.
	 *
	 * @return GroupCompany - group company entity
	 */
	@GetMapping(value = "/sideBar")
	public SuccessResponse<List<SideBarDTO>> getGroupCompany() {
		List<SideBarDTO> sideBars = new ArrayList<>();

		List<GroupCompanyDTO>  groupCompanyDTOS = dataService.getAllGroupCompany(true, false);
		for (GroupCompanyDTO groupCompanyDTO:
		groupCompanyDTOS) {
			SideBarDTO sideBarDTO = new SideBarDTO();
			sideBarDTO.setGroupCompany(groupCompanyDTO);
			List<Company> companies = dataService.findCompanyByGroupCompanyId(groupCompanyDTO.getId());
			List<CompanyDTO> companyDTOList = modelMapper.map(companies, new TypeToken<List<CompanyDTO>>() {
			}.getType());
			List<CompanyDTO> companyDTOListModified = new ArrayList<>();
			if(companies.size()>0){
				for (CompanyDTO companyDTO:
						companyDTOList) {
					List<Location> locations = dataService.getAllLocations(true,false);
					companyDTO.setLocations(locations);
					companyDTOListModified.add(companyDTO);
				}
				sideBarDTO.setCompanies(companyDTOListModified);
				sideBars.add(sideBarDTO);
			}else{
				sideBars.add(sideBarDTO);
			}

		}
		return new SuccessResponse<List<SideBarDTO>>(SuccessCode.GET_GC_SUCCESS, sideBars, HttpStatus.OK);
	}

	/**
	 * Add a new company with company name, code, etc.
	 *
	 * @param companies - company details
	 * @return company Entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/company")
	public SuccessResponse<List<Company>> addCompany(@Valid @RequestBody List<CompanyDTO> companies) {
		List<Company> companyList = dataService.addCompany(companies);
		return new SuccessResponse<List<Company>>(SuccessCode.COMPANY_SAVE, companyList, HttpStatus.CREATED);
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
	 * Get company detail by company Id.
	 *
	 * @param groupCompanyId - groupCompanyId id
	 * @return Company - company entity
	 */
	@GetMapping(value = "/company")
	public SuccessResponse<List<Company>> getCompanyByGroupCompanyId(@RequestParam long groupCompanyId) {
		List<Company> companies = dataService.findCompanyByGroupCompanyId(groupCompanyId);
		return new SuccessResponse<List<Company>>(SuccessCode.GET_COMPANY_SUCCESS, companies, HttpStatus.OK);
	}

	/**
	 * Get all active company details
	 *
	 * @return List - list of company entity
	 */
	@RequestMapping(value = "/companies", method = RequestMethod.GET)
	public SuccessResponse<List<CompanyDTO>> getAllCompany() {
		List<CompanyDTO> companies = dataService.getAllCompany(true);
		return new SuccessResponse<List<CompanyDTO>>(SuccessCode.GET_COMPANY_LIST_SUCCESS, companies,
				HttpStatus.OK);
	}

	/**
	 * Get all active company details
	 *
	 * @return List - list of company entity
	 */
	@RequestMapping(value = "/company/prerequisites", method = RequestMethod.GET)
	public SuccessResponse<CompanyPrerequisiteDTO> getPrerequisiteCompanies() {
		List<CurrencyDTO> currencies = dataService.getAllCurrencies(true);
		List<FiscalCalendarDTO> fiscalCalendars = dataService.getAllFiscalCalendar(true);
		List<GroupCompanyDTO> groupCompanies = dataService.getAllGroupCompany(true, false);
		List<CountryDTO> countries = dataService.getAllCountries(true);

		CompanyPrerequisiteDTO companyPrerequisiteDTO = new CompanyPrerequisiteDTO();
		companyPrerequisiteDTO.setGroupCompanies(groupCompanies);
		companyPrerequisiteDTO.setCurrencies(currencies);
		companyPrerequisiteDTO.setFiscalCalendars(fiscalCalendars);
		companyPrerequisiteDTO.setCountries(countries);

		return new SuccessResponse<CompanyPrerequisiteDTO>(SuccessCode.GET_COMPANY_LIST_SUCCESS, companyPrerequisiteDTO,
				HttpStatus.OK);
	}

	/**
	 * Add a new business unit with name, code, etc.
	 *
	 * @param businessUnits- business unit details
	 * @return business unit Entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/businessUnit")
	public SuccessResponse<BusinessUnit> adBU(@Valid @RequestBody List<BusinessUnitDTO> businessUnits) {
		List<BusinessUnit> businessUnitList = dataService.addBU(businessUnits);
		return new SuccessResponse<>(SuccessCode.BU_SAVE, businessUnitList, HttpStatus.CREATED);
	}

	/**
	 * Update a business unit details like name, code, etc,
	 *
	 * @param businessUnit - business unit detail
	 * @return BusinessUnit - business unit Entity
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/businessUnit")
	public SuccessResponse<BusinessUnit> updateBU(@Valid @RequestBody BusinessUnitDTO businessUnit) {
		BusinessUnit businessUnitModified = dataService.updateBU(businessUnit);
		return new SuccessResponse<>(SuccessCode.BU_UPDATE, businessUnitModified, HttpStatus.OK);
	}

	/**
	 * Update a business unit details like name, code, etc,
	 *
	 * @param lockDeleteDTO - business unit detail
	 * @return BusinessUnit - business unit Entity
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/businessUnit/inActivateOrDelete")
	public SuccessResponse<String> inActivateOrDeleteBU(@Valid @RequestBody LockDeleteDTO lockDeleteDTO) {
		String message = dataService.inActivateOrDeleteBU(lockDeleteDTO);
		return new SuccessResponse<>(SuccessCode.BU_UPDATE, message, HttpStatus.OK);
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
		List<BusinessUnitDTO> businessUnits = dataService.getAllBU(true, false);
		return new SuccessResponse<List<BusinessUnitDTO>>(SuccessCode.GET_BU_LIST_SUCCESS, businessUnits,
				HttpStatus.OK);
	}

	/**
	 * Get all active business unit details
	 *
	 * @return List - list of business unit entity
	 */
	@RequestMapping(value = "/businessUnit/prerequisites", method = RequestMethod.GET)
	public SuccessResponse<BusinessUnitPrerequisiteDTO> getAllBUPrerequisites() {
		BusinessUnitPrerequisiteDTO businessUnitPrerequisiteDTO = new BusinessUnitPrerequisiteDTO();
		businessUnitPrerequisiteDTO.setGroupCompanies(dataService.getAllGroupCompany(true, false));
		businessUnitPrerequisiteDTO.setUsers(userRepository.findByStatus("Active"));
		return new SuccessResponse<BusinessUnitPrerequisiteDTO>(SuccessCode.GET_BU_LIST_SUCCESS, businessUnitPrerequisiteDTO,
				HttpStatus.OK);
	}

	/**
	 * Get all department based on the user email id
	 *
	 * @param email - email id of the user
	 * @return List - list of department entity
	 */
	@RequestMapping(value = "/user-department", method = RequestMethod.GET)
	public SuccessResponse<List<Department>> getAllDepartmentByUser( @RequestParam String email) {
		List<Department> department = dataService.getAllDepartmentByUser(email);
		return new SuccessResponse<List<Department>>(SuccessCode.GET_DEPARTMENTS_SUCCESS, department,
				HttpStatus.OK);
	}

	/**
	 * Add fiscal period
	 *
	 * @param key - key of Fiscal Calendar
	 *
	 * @return List - list of fiscal period  entity
	 */
	@RequestMapping(value = "/fiscal-period", method = RequestMethod.POST)
	public SuccessResponse<List<FiscalCalendarPeriod>> addFiscalPeriodEntity(@RequestParam String key) {
		List<FiscalCalendarPeriod> fiscalCalendarPeriods = null;
		try {
			fiscalCalendarPeriods = dataService.addFiscalPeriodEntity(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new SuccessResponse<List<FiscalCalendarPeriod>>(SuccessCode.GET_DEPARTMENTS_SUCCESS, fiscalCalendarPeriods,
				HttpStatus.OK);
	}

	/**
	 * Get all fiscal period
	 *
	 * @return List - list of fiscal period  entity
	 */
	@RequestMapping(value = "/fiscal-period", method = RequestMethod.GET)
	public SuccessResponse<List<FiscalCalendarPeriod>> getFiscalPeriodEntity() {
		List<FiscalCalendarPeriod> fiscalCalendarPeriods = null;
		try {
			fiscalCalendarPeriods = dataService.getAllFiscalPeriodEntity();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new SuccessResponse<List<FiscalCalendarPeriod>>(SuccessCode.GET_DEPARTMENTS_SUCCESS, fiscalCalendarPeriods,
				HttpStatus.OK);
	}


}