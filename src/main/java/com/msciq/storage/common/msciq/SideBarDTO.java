package com.msciq.storage.common.msciq;

import com.msciq.storage.common.entity.Company;
import com.msciq.storage.common.entity.Location;
import lombok.Data;

import java.util.List;

@Data
public class SideBarDTO {

	private GroupCompanyDTO groupCompany;

	private List<Company> companies;

	private List<Location> locations;
//
//	private String groupCompanyName;
//	private List<String> companiesName;

}
