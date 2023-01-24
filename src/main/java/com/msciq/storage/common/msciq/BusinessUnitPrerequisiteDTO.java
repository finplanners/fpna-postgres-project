package com.msciq.storage.common.msciq;

import com.msciq.storage.model.User;
import com.msciq.storage.model.UserDTO;
import lombok.Data;

import java.util.List;

@Data
public class BusinessUnitPrerequisiteDTO {

	private List<GroupCompanyDTO> groupCompanies;

	private List<User> users;

}
