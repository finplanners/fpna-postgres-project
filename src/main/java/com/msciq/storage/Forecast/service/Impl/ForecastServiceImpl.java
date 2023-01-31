package com.msciq.storage.Forecast.service.Impl;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.entity.*;
import com.msciq.storage.exception.DataNotFoundException;
import com.msciq.storage.forecast.service.ForecastService;
import com.msciq.storage.model.Project;
import com.msciq.storage.model.response.BusinessUnitDetails;
import com.msciq.storage.model.response.ForecastingPrerequisites;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.project.repository.ProjectRepository;
import com.msciq.storage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private GroupCompanyRepository groupCompanyRepository;

    public SuccessResponse<ForecastingPrerequisites> getForecastingPrerequisites(Long templateId) {
        List<Department> departments = departmentRepository.findByTemplateId(templateId);
        List<BusinessUnit> businessUnits = businessUnitRepository.findAll();
        if (businessUnits.isEmpty()) {
            throw new DataNotFoundException(19080);
        }
        if (departments.isEmpty()) {
            throw new DataNotFoundException(19081);
        }
        ForecastingPrerequisites forecastingPrerequisitesResponse = ForecastingPrerequisites.builder()
                .businessUnits(businessUnits)
                .departments(departments)
                .build();
        return new SuccessResponse<ForecastingPrerequisites>(Constants.SUCCESS, forecastingPrerequisitesResponse, null, HttpStatus.OK);
    }

    @Override
    public SuccessResponse<BusinessUnitDetails> getBusinessUnitRelatedDetailsForForecasting(Long buId) {
        List<Project> projects = projectRepository.findByBusinessUnit_Id(buId);
        if (projects.isEmpty()) {
            throw new DataNotFoundException(19082);
        }
        BusinessUnit businessUnit = businessUnitRepository.findById(buId).get();
        List<Company> companies = companyRepository.findByGroupCompanyIdAndIsDeleted(businessUnit.getGroupCompany().getId(), false);
        List<Long> companyIds = companies.stream().map(company -> {
            return company.getId();
        }).collect(Collectors.toList());
        List<Location> locations = locationRepository.findByIsActiveAndCompanyIdIn(true, companyIds);
        if (locations.isEmpty()) {
            throw new DataNotFoundException(19083);
        }
        BusinessUnitDetails businessUnitDetails = BusinessUnitDetails.builder()
                .projects(projects)
                .locations(locations)
                .build();
        return new SuccessResponse<BusinessUnitDetails>(Constants.SUCCESS, businessUnitDetails, null, HttpStatus.OK);
    }
}
