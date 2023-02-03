package com.msciq.storage.template.service;

import com.msciq.storage.model.Template;

import java.util.List;

public interface TemplateService {

    /**
     * This method returns list of all forecasting templates
     *
     * @return List of ForecastingTemplate Entity
     * @author Sivaranjani DS
     */
    public List<Template> getAllForecastingTemplates();

    /**
     * This method adds list of template
     *
     * @param templates
     * @return ForecastingTemplate Entity
     * @author Sivaranjani DS
     */
    public Template addForecastingTemplate(Template templates);

    /**
     * This method returns list of all forecasting templates of department
     * @param departId - id of department table
     *
     * @return List of ForecastingTemplate Entity
     *
     */
    public List<Template> getAllForecastingTemplatesByDepart(List<Long> departId);


}
