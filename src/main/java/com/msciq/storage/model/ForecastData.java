package com.msciq.storage.model;

import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * This class is an entity class for 1A template items.
 * </p>
 *
 * @author Sivaranjani DS
 */
@Data
@Entity
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Table(name = TableConstants.FORECAST_DATA)
public  class ForecastData extends BaseEntity {
    private static final Long serialVersionUID = 1L;

    @Column(name = FieldConstants.TEMPLATE_TYPE)
    private String templateType;

    @Column(name = FieldConstants.USER_ID)
    private Long userId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String jsonData;
}