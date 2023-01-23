package com.msciq.storage.common.msciq;

import lombok.Data;

import java.util.List;

@Data
public class GroupCompanyPrerequisiteDTO {

	private List<CurrencyDTO> currencyDTOS;

	private List<CountryDTO> countryDTOS;

}
