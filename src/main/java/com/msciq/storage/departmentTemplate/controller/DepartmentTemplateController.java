package com.msciq.storage.departmentTemplate.controller;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.common.entity.Department;
import com.msciq.storage.departmentTemplate.service.DepartmentTemplateService;
import com.msciq.storage.exception.BadRequestException;
import com.msciq.storage.model.BudgetCategory;
import com.msciq.storage.model.request.BudgetCategoryTemplateTypeMappingDTO;
import com.msciq.storage.model.request.DepartmentTemplateDTO;
import com.msciq.storage.model.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/department-template")
@Validated
public class DepartmentTemplateController {

    @Autowired
    DepartmentTemplateService departmentTemplateService;


    @RequestMapping(method = RequestMethod.PUT)
    public SuccessResponse<Department> mapDepartmentTemplate(@RequestBody DepartmentTemplateDTO departmentTemplateDTO) {
        if (Objects.isNull(departmentTemplateDTO)) {
            throw new BadRequestException(19011);
        }
        Department savedDepartmentTemplate = departmentTemplateService.mapDepartmentTemplate(departmentTemplateDTO);
        return new SuccessResponse<Department>
                (String.format(SuccessMessage.SUCCESSFULLY_SAVED, Constants.DEPARTMENT_TEMPLATE),
                        savedDepartmentTemplate,
                        null,
                        HttpStatus.CREATED);
    }

}
