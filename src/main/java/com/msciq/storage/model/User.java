package com.msciq.storage.model;

import com.msciq.storage.common.TableConstants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@AllArgsConstructor
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = TableConstants.USER)
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

    @ManyToMany(targetEntity= Role.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role_table",
            joinColumns = { @JoinColumn(name = "user_id",referencedColumnName="id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id",referencedColumnName="id") })
    private List<Role> roles;

}
