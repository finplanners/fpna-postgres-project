package com.msciq.storage.template.service.Impl;

import com.msciq.storage.common.entity.FiscalCalendarPeriod;
import com.msciq.storage.exception.BadRequestException;
import com.msciq.storage.exception.DataConflictException;
import com.msciq.storage.model.Template;
import com.msciq.storage.repository.FiscalCalendarPeriodRepository;
import com.msciq.storage.template.repository.TemplateRepository;
import com.msciq.storage.template.service.TemplateService;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private FiscalCalendarPeriodRepository fiscalCalendarPeriodRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Template> getAllForecastingTemplates() {
        List<Template> templateRepositoryList =  templateRepository.findByIsActive(true);
        if (Objects.isNull(templateRepositoryList)) {
            throw new BadRequestException(19084);
        }
        try{
            List<Template> templates = new ArrayList<>();

            for(Template template : templateRepositoryList) {
                if (template.getType().equals("Trend")) {
                    List<FiscalCalendarPeriod> fiscalCalendarPeriods = fiscalCalendarPeriodRepository.findAll();
                    List<Object> templateObject = new ArrayList<>();
                    List<String> findFirstQuarter = new ArrayList<>();
                    Object obj = new JSONParser().parse(template.getTemplateValue());
                    JSONArray jo = (JSONArray) obj;
//                    templateObject.add((JSONObject) jo.get(0));
//                    templateObject.add((JSONObject) jo.get(1));
                    templateObject.addAll(jo);
                    for (FiscalCalendarPeriod fiscalCalendarPeriod : fiscalCalendarPeriods) {
                        if (findFirstQuarter.size() == 0 || !findFirstQuarter.contains(fiscalCalendarPeriod.getQuarter())) {
                            JSONObject trendObject = new JSONObject();
                            trendObject.put("name", fiscalCalendarPeriod.getQuarter() + fiscalCalendarPeriod.getYear());
                            trendObject.put("type", "String");
                            templateObject.add(trendObject);
                            findFirstQuarter.add(fiscalCalendarPeriod.getQuarter());
                        }
                        template.setTemplateValue(templateObject.toString());
                    }
                }
                templates.add(template);
            }
        return templates;

        }catch (Exception e){
            return null;
        }
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
