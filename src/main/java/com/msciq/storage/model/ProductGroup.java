package com.msciq.storage.model;

import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import com.msciq.storage.common.entity.BusinessUnit;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = TableConstants.PRODUCT_GROUP)
public class ProductGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = FieldConstants.CODE)
    private String code;

    @Column(name = FieldConstants.DESCRIPTION)
    private String description;

    @ManyToOne
    @JoinColumn(name = FieldConstants.BU_ID)
    private BusinessUnit businessUnit;

}
