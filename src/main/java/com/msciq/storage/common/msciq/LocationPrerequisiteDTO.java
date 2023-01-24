package com.msciq.storage.common.msciq;

import lombok.Data;

import java.util.List;

@Data
public class LocationPrerequisiteDTO {

	private List<CompanyDTO> companies;

	private List<CountryDTO> countries;

}
