package com.msciq.storage.service.impl;

import com.google.common.reflect.TypeToken;
import com.msciq.storage.common.entity.*;
import com.msciq.storage.common.msciq.*;
import com.msciq.storage.exception.BadRequestException;
import com.msciq.storage.exception.DataConflictException;
import com.msciq.storage.exception.DataNotFoundException;
import com.msciq.storage.repository.*;
import com.msciq.storage.service.DataService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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

	ModelMapper modelMapper = new ModelMapper();

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
		GroupCompany existingGroupCompany = groupCompanyRepository.findByGcCode(groupCompanyDTO.getGcCode());
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
	public Company addCompany(@Valid CompanyDTO companyDTO) {
		if (Objects.isNull(companyDTO)) {
			throw new BadRequestException(19011);
		}
		Company existingCompany = companyRepository.findByCode(companyDTO.getCode());
		if (!Objects.isNull(existingCompany)) {
			throw new DataConflictException(19054);
		}
		Company company = modelMapper.map(companyDTO, Company.class);
		return companyRepository.save(company);
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
	public BusinessUnit addBU(BusinessUnitDTO businessUnitDTO) {
		if (Objects.isNull(businessUnitDTO)) {
			throw new BadRequestException(19011);
		}
		BusinessUnit existingBU = businessUnitRepository.findByCode(businessUnitDTO.getCode());
		if (!Objects.isNull(existingBU)) {
			throw new DataConflictException(19064);
		}
		BusinessUnit businessUnit = modelMapper.map(businessUnitDTO, BusinessUnit.class);
		return businessUnitRepository.save(businessUnit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinessUnit updateBU(BusinessUnit businessUnit) {
		if (Objects.isNull(businessUnit)) {
			throw new BadRequestException(19011);
		} else {
			if (Objects.isNull(businessUnitRepository.findByIdAndIsDeleted(businessUnit.getId(), false))) {
				throw new DataNotFoundException(19065);
			}
			boolean isExists = businessUnitRepository.isBUExists(businessUnit.getId(), businessUnit.getCode());
			if (isExists) {
				throw new DataConflictException(19064);
			}
			return businessUnitRepository.save(businessUnit);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BusinessUnitDTO> getAllBU(Boolean isActive) {
		List<BusinessUnit> businessUnits = businessUnitRepository.findByIsActive(isActive);
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

}