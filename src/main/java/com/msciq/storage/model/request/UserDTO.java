package com.msciq.storage.model.request;

import com.msciq.storage.model.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserDTO {

    private Long id;
    @NotNull(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull(message = "FirstName is mandatory")
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;

    @NotNull(message = "LastName is mandatory")
    @NotBlank(message = "LastName is mandatory")
    private String lastName;
    private String phoneNumber;

    private long departmentId;

    @NotEmpty(message = "Roles is mandatory")
    private List<String> userRoles;

    private List<Role> roles;

}
