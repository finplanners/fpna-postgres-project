package com.msciq.storage.templateType.service.impl;

import com.msciq.storage.model.TemplateType;
import com.msciq.storage.templateType.repository.TemplateTypeRepository;
import com.msciq.storage.templateType.service.TemplateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateTypeServiceImpl implements TemplateTypeService {

    @Autowired
    private TemplateTypeRepository templateTypeRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateType> getAllTemplateTypes() {
        return templateTypeRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateType> addTemplateTypes(List<TemplateType> templateTypes) {
        return templateTypeRepository.saveAll(templateTypes);
    }
}
