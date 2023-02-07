package com.msciq.storage.template.repository;

import com.msciq.storage.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    public static final String GET_ALL_FORECASTING_TEMPLATES = " select templates from Template templates";
    public static final String GET_ALL_FORECASTING_TEMPLATES_DEPART = "select distinct(t.*) from template t  inner join department_template td  on t.id  = td.temp_id where td.dept_id  in (:departId) and t.is_active = true";


    @Query(value = GET_ALL_FORECASTING_TEMPLATES)
    List<Template> getAllForecastingTemplates();

    public List<Template> findByIsActive(Boolean isActive);

    public Template findByTemplateCodeAndTemplateName(String templateCode,String templateName);

    List<Object> findByTemplateCode(String templateCode);

    /**
     * To find all forecasting templates of department
     *
     * @param departId - departId
     * @return List of template Entities
     */
    @Query(value = GET_ALL_FORECASTING_TEMPLATES_DEPART,nativeQuery = true)
    List<Template> getAllForecastingTemplatesByDepart(List<Long> departId);
}
