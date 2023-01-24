package com.msciq.storage.model.request;

import com.msciq.storage.model.Template;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentTemplateDTO {

    private String departmentCode;

    private List<Template> templates;
}
