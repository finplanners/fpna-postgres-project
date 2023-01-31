package com.msciq.storage.service.impl;

import com.google.common.reflect.TypeToken;
import com.msciq.storage.common.Constants;
import com.msciq.storage.common.ErrorConstants;
import com.msciq.storage.common.ErrorMessage;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.common.entity.*;
import com.msciq.storage.common.msciq.*;
import com.msciq.storage.exception.BadRequestException;
import com.msciq.storage.exception.DataConflictException;
import com.msciq.storage.exception.DataNotFoundException;
import com.msciq.storage.model.User;
import com.msciq.storage.repository.*;
import com.msciq.storage.service.DataService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class is responsible for performing business operations on Country, Currency, GC, Company and etc entities.
 *
 * @author Rajkumar
 */
@Service
public class DataServiceImpl implements DataService {

	@Autowired
	CurrencyRepository currencyRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	FiscalCalendarRepository fiscalCalendarRepository;

	@Autowired
	GroupCompanyRepository groupCompanyRepository;

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	BusinessUnitRepository businessUnitRepository;

	@Autowired
	CountryRepository countryRepository;
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	StateRepository stateRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public Country addCountry(CountryDTO countryDTO) {
		if (Objects.isNull(countryDTO)) {
			throw new BadRequestException(19011);
		}
		Country existingCountry = countryRepository.findByCountryCodeAndNameAndStates_NameIn(countryDTO.getCountryCode(),
				countryDTO.getName(),countryDTO.getStates().stream().map(s-> s.getName()).collect(Collectors.toList()));
		if (!Objects.isNull(existingCountry)) {
			throw new DataConflictException(19010);
		}
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Country country = modelMapper.map(countryDTO, Country.class);
		return countryRepository.save(country);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Country updateCountry(Country country) {
		if (Objects.isNull(country)) {
			throw new BadRequestException(19011);
		} else {
			if (Objects.isNull(countryRepository.findByIdAndIsDeleted(country.getId(), false))) {
				throw new DataNotFoundException(19012);
			}
			boolean isExists = countryRepository.isCountryExists(country.getId(), country.getName(),
					country.getCountryCode());
			if (isExists) {
				throw new DataConflictException(19010);
			}
			return countryRepository.save(country);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Country findCountryById(Long countryId) {
		Country country = countryRepository.findByIdAndIsDeleted(countryId, false);
		if (Objects.isNull(country)) {
			throw new DataNotFoundException(19012);
		}
		return country;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CountryDTO> getAllCountries(Boolean isActive) {
		List<Country> countries = countryRepository.findByIsActive(isActive);
		List<CountryDTO> countryList = modelMapper.map(countries, new TypeToken<List<CountryDTO>>() {
		}.getType());
		return countryList;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Currency addCurrency(CurrencyDTO currencyDTO) {
		if (Objects.isNull(currencyDTO)) {
			throw new BadRequestException(12006);
		}
		Currency existingCurrency = currencyRepository.findByCurrency(currencyDTO.getCurrency());
		if (!Objects.isNull(existingCurrency)) {
			throw new DataConflictException(19013);
		}
		Currency currency = modelMapper.map(currencyDTO, Currency.class);
		return currencyRepository.save(currency);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Currency updateCurrency(Currency currency) {
		if (Objects.isNull(currency)) {
			throw new BadRequestException(19011);
		} else {
			if (Objects.isNull(currencyRepository.findByIdAndIsDeleted(currency.getId(), false))) {
				throw new DataNotFoundException(19014);
			}
			boolean isExists = currencyRepository.isCurrencyExists(currency.getId(), currency.getCurrency());
			if (isExists) {
				throw new DataConflictException(19013);
			}
			return currencyRepository.save(currency);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Currency findCurrencyById(Long currencyId) {
		Currency currency = currencyRepository.findByIdAndIsDeleted(currencyId, false);
		if (Objects.isNull(currency)) {
			throw new DataNotFoundException(19012);
		}
		return currency;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CurrencyDTO> getAllCurrencies(Boolean isActive) {
		List<Currency> currencies = currencyRepository.findByIsActive(isActive);
		List<CurrencyDTO> currencyList = modelMapper.map(currencies, new TypeToken<List<CurrencyDTO>>() {
		}.getType());
		return currencyList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Department addDepartment(DepartmentDTO departmentDTO) {
		if (Objects.isNull(departmentDTO)) {
			throw new BadRequestException(19011);
		}
		Department existingDepartment = departmentRepository
				.findByDepartNameAndDepartmentCode(departmentDTO.getDepartName(), departmentDTO.getDepartmentCode());
		if (!Objects.isNull(existingDepartment)) {
			throw new DataConflictException(19024);
		}

		Department department = modelMapper.map(departmentDTO, Department.class);
		return departmentRepository.save(department);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Department updateDepartment(Department department) {
		if (Objects.isNull(department)) {
			throw new BadRequestException(19011);
		} else {
			if (Objects.isNull(departmentRepository.findByIdAndIsDeleted(department.getId(), false))) {
				throw new DataNotFoundException(19025);
			}
			boolean isExists = departmentRepository.isDepartmentExists(department.getId(), department.getDepartName(),
					department.getDepartmentCode());
			if (isExists) {
				throw new DataConflictException(19024);
			}
			return departmentRepository.save(department);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DepartmentDTO> getAllDepartment(Boolean isActive) {
		List<Department> departments = departmentRepository.findByIsActive(isActive);
		List<DepartmentDTO> departmentList = modelMapper.map(departments, new TypeToken<List<DepartmentDTO>>() {
		}.getType());
		return departmentList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Department findDepartmentById(Long departmentId) {
		Department department = departmentRepository.findByIdAndIsDeleted(departmentId, false);
		if (Objects.isNull(department)) {
			throw new DataNotFoundException(19025);
		}
		return department;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FiscalCalendar addFiscalCalendar(FiscalCalendarDTO fiscalCalendarDTO) {
		if (Objects.isNull(fiscalCalendarDTO)) {
			throw new BadRequestException(19011);
		}
		FiscalCalendar existingFiscalCalendar = fiscalCalendarRepository.findByKey(fiscalCalendarDTO.getKey());
		if (!Objects.isNull(existingFiscalCalendar)) {
			throw new DataConflictException(19034);
		}
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		FiscalCalendar fiscalCalendar = modelMapper.map(fiscalCalendarDTO, FiscalCalendar.class);
		return fiscalCalendarRepository.save(fiscalCalendar);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FiscalCalendar updateFiscalCalendar(FiscalCalendar fiscalCalendar) {
		if (Objects.isNull(fiscalCalendar)) {
			throw new BadRequestException(19011);
		} else {
			if (Objects.isNull(fiscalCalendarRepository.findByIdAndIsDeleted(fiscalCalendar.getId(), false))) {
				throw new DataNotFoundException(19035);
			}
			boolean isExists = fiscalCalendarRepository.isFiscalCalendarExists(fiscalCalendar.getId(),
					fiscalCalendar.getKey());
			if (isExists) {
				throw new DataConflictException(19034);
			}
			return fiscalCalendarRepository.save(fiscalCalendar);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FiscalCalendarDTO> getAllFiscalCalendar(Boolean isActive) {
		List<FiscalCalendar> fiscalCalendars = fiscalCalendarRepository.findByIsActive(isActive);
		List<FiscalCalendarDTO> fiscalCalendarList = modelMapper.map(fiscalCalendars,
				new TypeToken<List<FiscalCalendarDTO>>() {
				}.getType());
		return fiscalCalendarList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FiscalCalendar findFiscalCalendarById(Long fiscalCalendarId) {
		FiscalCalendar fiscalCalendar = fiscalCalendarRepository.findByIdAndIsDeleted(fiscalCalendarId, false);
		if (Objects.isNull(fiscalCalendar)) {
			throw new DataNotFoundException(19035);
		}
		return fiscalCalendar;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroupCompany addGroupCompany(@Valid GroupCompanyDTO groupCompanyDTO) {
		if (Objects.isNull(groupCompanyDTO)) {
			throw new BadRequestException(19011);
		}
		GroupCompany existingGroupCompany = groupCompanyRepository.findByGcCodeAndGcName(groupCompanyDTO.getGcCode(), groupCompanyDTO.getGcName());
		if (!Objects.isNull(existingGroupCompany)) {
			throw new DataConflictException(19044);
		}
		GroupCompany groupCompany = modelMapper.map(groupCompanyDTO, GroupCompany.class);
		return groupCompanyRepository.save(groupCompany);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroupCompany updateGroupCompany(@Valid GroupCompany groupCompany) {
		if (Objects.isNull(groupCompany)) {
			throw new BadRequestException(19011);
		} else {
			if (Objects.isNull(groupCompanyRepository.findByIdAndIsDeleted(groupCompany.getId(), false))) {
				throw new DataNotFoundException(19045);
			}
			boolean isExists = groupCompanyRepository.isGroupCompanyExists(groupCompany.getId(),
					groupCompany.getGcCode());
			if (isExists) {
				throw new DataConflictException(19044);
			}
			return groupCompanyRepository.save(groupCompany);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GroupCompanyDTO> getAllGroupCompany(Boolean isActive) {
		List<GroupCompany> GroupCompanies = groupCompanyRepository.findByIsActive(isActive);
		List<GroupCompanyDTO> GroupCompanyList = modelMapper.map(GroupCompanies,
				new TypeToken<List<GroupCompanyDTO>>() {
				}.getType());
		return GroupCompanyList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroupCompany findGroupCompanyById(Long id) {
		GroupCompany groupCompany = groupCompanyRepository.findByIdAndIsDeleted(id, false);
		if (Objects.isNull(groupCompany)) {
			throw new DataNotFoundException(19035);
		}
		return groupCompany;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Company> addCompany(List<CompanyDTO> companyDTOS) {

		List<Company> companyList = new ArrayList<>();
		for (CompanyDTO companyDTO:
			 companyDTOS) {
			if (Objects.isNull(companyDTO)) {
				throw new BadRequestException(19011);
			}
			Company existingCompany = companyRepository.findByCodeAndName(companyDTO.getCode(), companyDTO.getName());
			if (!Objects.isNull(existingCompany)) {
				throw new DataConflictException(19054);
			}
			Company company = modelMapper.map(companyDTO, Company.class);
			companyList.add(company);

		}
		return companyRepository.saveAll(companyList);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Company updateCompany(@Valid Company company) {
		if (Objects.isNull(company)) {
			throw new BadRequestException(19011);
		} else {
			if (Objects.isNull(companyRepository.findByIdAndIsDeleted(company.getId(), false))) {
				throw new DataNotFoundException(19055);
			}
			boolean isExists = companyRepository.isCompanyExists(company.getId(), company.getCode());
			if (isExists) {
				throw new DataConflictException(19054);
			}
			return companyRepository.save(company);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CompanyDTO> getAllCompany(Boolean isActive) {
		List<Company> companies = companyRepository.findByIsActive(isActive);
		List<CompanyDTO> companyList = modelMapper.map(companies, new TypeToken<List<CompanyDTO>>() {
		}.getType());
		return companyList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Company findCompanyById(Long id) {
		Company company = companyRepository.findByIdAndIsDeleted(id, false);
		if (Objects.isNull(company)) {
			throw new DataNotFoundException(19055);
		}
		return company;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BusinessUnit> addBU(List<BusinessUnitDTO> businessUnitDTOS) {

		List<BusinessUnit> businessUnitList = new ArrayList<>();
		for (BusinessUnitDTO businessUnitDTO:
				businessUnitDTOS) {
			if (Objects.isNull(businessUnitDTO)) {
				throw new BadRequestException(19011);
			}
			BusinessUnit existingBU = businessUnitRepository.findByCode(businessUnitDTO.getCode());
			if (!Objects.isNull(existingBU)) {
				throw new DataConflictException(19064);
			}
			BusinessUnit businessUnit = modelMapper.map(businessUnitDTO, BusinessUnit.class);
			businessUnitList.add(businessUnit);

		}
		return businessUnitRepository.saveAll(businessUnitList);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinessUnit updateBU(BusinessUnitDTO businessUnit) {
		if (Objects.isNull(businessUnit)) {
			throw new BadRequestException(19011);
		} else {
			BusinessUnit businessUnitFromDB = businessUnitRepository.findByIdAndIsDeleted(businessUnit.getId(), false);
			if (Objects.isNull(businessUnitFromDB)) {
				throw new DataNotFoundException(19065);
			}
			if(businessUnit.getName()!=null)
				businessUnitFromDB.setName(businessUnit.getName());
			if(businessUnit.isStatus()){
				businessUnitFromDB.setStatus(true);
			}

			if(businessUnit.getGroupCompany()!=null){
				businessUnitFromDB.setGroupCompany(groupCompanyRepository.findByIdAndIsDeleted(businessUnit.getGroupCompany().getId(),false));
			}
			if(!businessUnit.isStatus()){
				businessUnitFromDB.setStatus(false);
			}
			businessUnitFromDB.setActiveInactiveDate(businessUnit.getActiveInactiveDate());
			return businessUnitRepository.save(businessUnitFromDB);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BusinessUnitDTO> getAllBU(Boolean isActive, Boolean isDeleted) {
		List<BusinessUnit> businessUnits = businessUnitRepository.findByIsActiveAndIsDeleted(isActive, isDeleted);
		List<BusinessUnitDTO> businessUnitList = modelMapper.map(businessUnits, new TypeToken<List<BusinessUnitDTO>>() {
		}.getType());
		return businessUnitList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinessUnit findBUById(Long buId) {
		BusinessUnit businessUnit = businessUnitRepository.findByIdAndIsDeleted(buId, false);
		if (Objects.isNull(businessUnit)) {
			throw new DataNotFoundException(19065);
		}
		return businessUnit;
	}

	@Override
	public List<Location> addLocations(List<LocationDTO> locationDTOS) {
		List<Location> locations = new ArrayList<>();
		for (LocationDTO locationDTO:
				locationDTOS) {
			if (Objects.isNull(locationDTOS)) {
				throw new BadRequestException(19011);
			}
			Location existingLocation = locationRepository.findByCode(locationDTO.getCode());
			if (!Objects.isNull(existingLocation)) {
				throw new DataConflictException(19068);
			}
			Location location = modelMapper.map(locationDTO, Location.class);
			//location.setState(modelMapper.map(locationDTO.getCountry().getStates().get(0), State.class));
			locations.add(location);
		}
		return locationRepository.saveAllAndFlush(locations);
	}

	@Override
	public List<Location> updateLocation(List<Location> locations) {
		List<Location> locationList = new ArrayList<>();
		if (Objects.isNull(locations)) {
			throw new BadRequestException(19011);
		} else {
			for (Location location:
				 locations) {
				if (Objects.isNull(locationRepository.findByIdAndIsDeleted(location.getId(), false))) {
					throw new DataNotFoundException(19055);
				}
				locationList.add(location);
			}
			return locationRepository.saveAllAndFlush(locationList);
		}
	}

	@Override
	public Location findLocationById(long locationId) {
		return null;
	}

	@Override
	public List<Location> getAllLocations(boolean b, long companyId) {
		return locationRepository.findByIsActiveAndCompany_id(b, companyId);
	}

	@Override
	public State addState(StateDTO stateDTO) {
		if (Objects.isNull(stateDTO)) {
			throw new BadRequestException(19011);
		}
		State existingState = stateRepository.findByName(stateDTO.getName());
		if (!Objects.isNull(existingState)) {
			throw new DataConflictException(19066);
		}
		State state = modelMapper.map(stateDTO, State.class);
		return stateRepository.save(state);
	}

	@Override
	public List<Company> findCompanyByGroupCompanyId(long groupCompanyId) {
		List<Company> companies = companyRepository.findByGroupCompany_IdAndIsDeleted(groupCompanyId, false);
		if (Objects.isNull(companies)) {
			throw new DataNotFoundException(19065);
		}
		return companies;
	}

	@Override
	public String inActivateOrDelete(LockDeleteDTO lockDeleteDTO) {
		String message= "";
		try{
			List<BusinessUnit> businessUnits = businessUnitRepository.findByIdIn(lockDeleteDTO.getIds());
			List<BusinessUnit> businessUnitListModified = new ArrayList<>();
			if(businessUnits!=null && businessUnits.size() == 0){
				message= ErrorConstants.INVALID_BUSINESS_UNITS;
				return message;
			}else {
				if (lockDeleteDTO.getIsActive() != null) {
					if (!lockDeleteDTO.getIsActive()) {
						message = SuccessMessage.BUSINESS_DEACTIVATED;
						for (BusinessUnit businessUnit :
								businessUnits) {
							businessUnit.setActive(false);
							businessUnitListModified.add(businessUnit);
						}
					} else if (lockDeleteDTO.getIsActive()) {
						message = SuccessMessage.BUSINESS_ACTIVATED;
						for (BusinessUnit businessUnit :
								businessUnits) {
							businessUnit.setActive(true);
							businessUnitListModified.add(businessUnit);
						}
					}

				} else if (lockDeleteDTO.getIsDeleted() != null) {
					if (lockDeleteDTO.getIsDeleted()) {
						message = SuccessMessage.BUSINESS_UNIT_DELETED;
						for (BusinessUnit businessUnit :
								businessUnits) {
							businessUnit.setDeleted(true);
							businessUnitListModified.add(businessUnit);
						}
					} else{
						message = ErrorMessage.INVALID_ACTION;
					}
				}else{
					message = ErrorMessage.INVALID_ACTION;
				}
				businessUnitRepository.saveAllAndFlush(businessUnitListModified);
				return message;

			}
		}catch(Exception e){
			return e.getMessage();
		}
	}

	@Override
	public List<DepartmentDTO> getAllDepartmentByUser(String email) {
		List<DepartmentDTO> departmentDTOS = departmentRepository.getAllDepartmentByUser(email);
		if (Objects.isNull(departmentDTOS)) {
			throw new DataNotFoundException(19065);
		}
		return departmentDTOS;
	}

}
