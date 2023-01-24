package com.msciq.storage.model;

import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = TableConstants.TEMPLATE_TYPE)
public class TemplateType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = FieldConstants.NAME)
    private String type;

    @Column(name = FieldConstants.DESCRIPTION)
    private String description;

}
