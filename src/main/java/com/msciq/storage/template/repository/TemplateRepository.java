package com.msciq.storage.template.repository;

import com.msciq.storage.common.entity.Department;
import com.msciq.storage.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    public static final String GET_ALL_FORECASTING_TEMPLATES = " select templates from Template templates";

    @Query(value = GET_ALL_FORECASTING_TEMPLATES)
    List<Template> getAllForecastingTemplates();

    public List<Template> findByIsActive(Boolean isActive);

    public Template findByTemplateCodeAndTemplateName(String templateCode,String templateName);

    List<Object> findByTemplateCode(String templateCode);
}
