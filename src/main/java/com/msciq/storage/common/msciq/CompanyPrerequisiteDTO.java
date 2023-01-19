package com.msciq.storage.common.msciq;

import lombok.Data;

import java.util.List;

@Data
public class CompanyPrerequisiteDTO {

	private List<CurrencyDTO> currencyDTOS;

	private List<FiscalCalendarDTO> fiscalCalendarDTOS;

	private List<CompanyDTO> companyDTOS;
	
}
