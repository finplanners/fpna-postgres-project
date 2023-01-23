package com.msciq.storage.model.request;

import com.msciq.storage.model.TemplateType;
import lombok.Data;

import java.util.List;

@Data
public class BudgetCategoryTemplateTypeMappingDTO {

    private Long budgetCategoryId;

    private List<TemplateType> templateTypes;

}
