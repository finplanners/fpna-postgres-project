package com.msciq.storage.template.service.Impl;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.entity.Department;
import com.msciq.storage.exception.BadRequestException;
import com.msciq.storage.exception.DataConflictException;
import com.msciq.storage.model.Template;
import com.msciq.storage.template.repository.TemplateRepository;
import com.msciq.storage.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Template> getAllForecastingTemplates() {
        return templateRepository.findByIsActive(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Template addForecastingTemplate(Template templates) {
        if (Objects.isNull(templates)) {
            throw new BadRequestException(19011);
        }
        Template existingTemplate = templateRepository.findByTemplateCodeAndTemplateName(templates.getTemplateCode(),templates.getTemplateName());
        if (!Objects.isNull(existingTemplate)) {
            throw new DataConflictException(19070);
        }
        return templateRepository.save(templates);
    }
}
