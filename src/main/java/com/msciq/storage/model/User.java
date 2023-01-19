package com.msciq.storage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msciq.storage.common.Constants;
import com.msciq.storage.common.FieldConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

//    @ManyToOne
//    @JoinColumn(name = "user_role_id")
//    private List<Role> userRolesId;

    private String userType;
    private boolean isVerified;

}
