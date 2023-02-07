package com.msciq.storage.model.request;

import com.msciq.storage.model.Template;
import lombok.Data;

import java.util.List;

@Data
public class BudgetCategoryTemplateMappingDTO {

    private Long budgetCategoryId;

    private List<Template> templates;

}
