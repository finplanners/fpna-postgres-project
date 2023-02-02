package com.msciq.storage.model;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This is a DTO class for user entity.
 *
 * @author Rajkumar Created on 30 Jun 2022
 */
@Data
public class UserDTO {

	private long id;

	private String username;

	private String email;

	private String gender;

	private Set<Role> roles = new HashSet<>();

	private Boolean isActive;

	private boolean accountExpired;

	private boolean accountLocked;

	private boolean credentialsExpired;

	private String authorization;

	private long currentDate;

	private String firstName;

	private String lastName;

	private String middleName;

	private String subject;

	private String phonenumber;


	private Set<Organization> tenants;
	
	private Boolean isBlocked;

    private String countryCode;

    private Long deviceInfoId;
    
    private long tenantId;

	private long departmentId;

	public Set<Role> getRoles() {
		return roles.stream().map(role -> new ModelMapper().map(role, Role.class)).collect(Collectors.toSet());
	}

//	public CountryDTO getCountry() {
//		ModelMapper modelMapper = new ModelMapper();
//		return modelMapper.map(country, CountryDTO.class);
//	}

}
