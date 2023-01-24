package com.msciq.storage.template.controller;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.model.Template;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/template")
@Validated
public class TemplateController {

    @Autowired
    TemplateService templateService;

    /**
     * Gets all forecasting templates.
     *
     * @return List of ForecastingTemplate entity
     * @author Sivaranjani DS
     */
    @RequestMapping(method = RequestMethod.GET)
   // @PreAuthorize("hasAuthority('Fun_Forecast_Template:READ') or hasAuthority('Key_Forecast_Template:READ')")
    public SuccessResponse<List<Template>> getAllForecastingTemplates() {
        List<Template> forecastingTemplates = templateService.getAllForecastingTemplates();
        return new SuccessResponse<List<Template>>(String.format(SuccessMessage.SUCCESS, Constants.TEMPLATE)
                , forecastingTemplates, null, HttpStatus.OK);
    }

    /**
     * This method is used to add a new Template.
     *
     * @param templates
     * @return ForecastingTemplate Entity.
     * @author Sivaranjani DS
     */
    @RequestMapping(method = RequestMethod.POST)
    //@PreAuthorize("hasAuthority('Fun_Forecast_Template:CREATE') or hasAuthority('Key_Forecast_Template:CREATE')")
    public SuccessResponse<List<Template>> addForecastingTemplates(@RequestBody Template templates) {
        return new SuccessResponse<List<Template>>
                (String.format(SuccessMessage.SUCCESSFULLY_SAVED, Constants.TEMPLATE),
                        templateService.addForecastingTemplate(templates),
                        null,
                        HttpStatus.CREATED);
    }

}
