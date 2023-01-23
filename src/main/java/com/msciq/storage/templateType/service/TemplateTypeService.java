package com.msciq.storage.templateType.service;

import com.msciq.storage.model.TemplateType;

import java.util.List;

public interface TemplateTypeService {

    /**
     * This method returns list of all template types
     *
     * @return List of TemplateType Entity
     * @author Sivaranjani DS
     */
    public List<TemplateType> getAllTemplateTypes();

    /**
     * This method adds list of template
     *
     * @param templateTypes
     * @return TemplateType Entity
     * @author Sivaranjani DS
     */
    public List<TemplateType> addTemplateTypes(List<TemplateType> templateTypes);

}
