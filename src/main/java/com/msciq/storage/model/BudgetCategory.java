package com.msciq.storage.model;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.TableConstants;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = TableConstants.BUDGET_CATEGRORY)
public class BudgetCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = FieldConstants.NAME)
    private String name;

    @Column(name = FieldConstants.TYPE)
    private BudgetCategoryType type;

    @Column(name = FieldConstants.ACTIVE_FROM_DATE)
    private LocalDate activeFromDate;

    @ManyToMany
    @JoinTable(name = "budget_categories_template_types",
            joinColumns = { @JoinColumn(name = FieldConstants.BUDGET_CATEGORY) },
            inverseJoinColumns = { @JoinColumn(name = FieldConstants.TEMPLATE_TYPE) })
    private Set<TemplateType> templateTypes = new HashSet<TemplateType>();

    @OneToMany(targetEntity= GLAccountGroup.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = Constants.BUDGET_CATEGORY_CODE, referencedColumnName = "id")
    private List<GLAccountGroup> glAccountList;

}
