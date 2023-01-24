package com.msciq.storage.departmentTemplate.service;

import com.msciq.storage.common.entity.Department;
import com.msciq.storage.model.BudgetCategory;
import com.msciq.storage.model.request.DepartmentTemplateDTO;

public interface DepartmentTemplateService {
    public Department mapDepartmentTemplate(DepartmentTemplateDTO departmentTemplateDTO);
}
