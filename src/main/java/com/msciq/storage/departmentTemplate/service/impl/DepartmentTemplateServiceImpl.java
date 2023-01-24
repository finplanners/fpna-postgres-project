package com.msciq.storage.departmentTemplate.service.impl;

import com.msciq.storage.common.entity.Department;
import com.msciq.storage.departmentTemplate.service.DepartmentTemplateService;
import com.msciq.storage.model.Template;
import com.msciq.storage.model.TemplateType;
import com.msciq.storage.model.request.DepartmentTemplateDTO;
import com.msciq.storage.repository.DepartmentRepository;
import com.msciq.storage.template.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class DepartmentTemplateServiceImpl implements DepartmentTemplateService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public Department mapDepartmentTemplate(DepartmentTemplateDTO departmentTemplateDTO) {
        Optional<Department> department = Optional.ofNullable(departmentRepository.findByDepartmentCode(departmentTemplateDTO.getDepartmentCode()));
        if (department.isPresent()) {
            Set<Template> templates = new HashSet<>();
            departmentTemplateDTO.getTemplates().stream().forEach(template -> {
                templates.add(templateRepository.findById(template.getId()).get());
            });
            department.get().setTemplates(templates);
            return departmentRepository.save(department.get());
        }

        return null;
    }
}
