package com.msciq.storage.model.response;

import com.msciq.storage.common.entity.BusinessUnit;
import com.msciq.storage.common.entity.Department;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ForecastingPrerequisites {
        private List<Department> departments;
        private List<BusinessUnit> businessUnits;
}
