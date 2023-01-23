package com.msciq.storage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msciq.storage.common.FieldConstants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "ORGANIZATION")
@ToString
@AllArgsConstructor
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Organization extends BaseEntity{

    @NotNull(message = "Organization name is mandatory")
    @NotBlank(message = "Organization name is mandatory")
    private String organizationName;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email is invalid")
    private String email;

    @NotNull(message = "First Name is mandatory")
    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotNull(message = "Last Name is mandatory")
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

}
