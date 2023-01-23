package com.msciq.storage.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "USER_TBL")
@ToString
@AllArgsConstructor
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {

    @NotNull(message = "Organization name is mandatory")
    @NotBlank(message = "Organization name is mandatory")
    private String organizationName;

    @NotNull(message = "FirstName is mandatory")
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;

    private String lastName;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email is invalid")
    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private String password;
    private String status;
    private String userType;
    private boolean isVerified;

}
