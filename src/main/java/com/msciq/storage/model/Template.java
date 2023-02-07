package com.msciq.storage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msciq.storage.common.Constants;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import com.msciq.storage.common.entity.Department;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = TableConstants.TEMPLATE)
@Entity
@EqualsAndHashCode
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Template extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = FieldConstants.TEMPLATE_CODE)
    private String templateCode;

    @Column(name = FieldConstants.TEMPLATE_NAME)
    private String templateName;

    @Column(name = FieldConstants.TYPE)
    private String type;

    @Column(name = FieldConstants.VERSION)
    private String version;

    @Enumerated(EnumType.STRING)
    @Column(name = FieldConstants.TEMPLATE_SOURCE)
    private Constants.TEMPLATE_SOURCE source;

    @Column(name = FieldConstants.TEMPLATE_VALUE, columnDefinition = "jsonb")
    @Type(type = "jsonb")
    private String templateValue;

}
