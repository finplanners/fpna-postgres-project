package com.msciq.storage.common.entity;

import com.msciq.storage.common.TableConstants;
import com.msciq.storage.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = TableConstants.USER_DEPARTMENT)
public class UserDepartment extends BaseEntity {
    private String email;

    private Long departmentId;

    private boolean isActual;

}
