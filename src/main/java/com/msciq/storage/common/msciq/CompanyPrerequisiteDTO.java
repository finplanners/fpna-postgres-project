package com.msciq.storage.common.msciq;

import lombok.Data;

import java.util.List;

@Data
public class CompanyPrerequisiteDTO {

	private List<CurrencyDTO> currencies;

	private List<FiscalCalendarDTO> fiscalCalendars;

	private List<GroupCompanyDTO> groupCompanies;

	private List<CountryDTO> countries;
	
}
