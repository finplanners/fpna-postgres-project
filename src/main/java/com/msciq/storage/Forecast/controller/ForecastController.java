package com.msciq.storage.forecast.controller;

import com.msciq.storage.forecast.service.ForecastService;
import com.msciq.storage.model.response.BusinessUnitDetails;
import com.msciq.storage.model.response.ForecastingPrerequisites;
import com.msciq.storage.model.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/forecast")
@Validated
public class ForecastController {

    @Autowired
    ForecastService forecastService;

    /**
     * Gets prerequisites for forecasting.
     *      This method will fetch list of all departments and all BusinessUnit required for forecasting
     *
     * @return forecasting prerequisites
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/pre-requisites/{templateId}", method = RequestMethod.GET)
    public SuccessResponse<ForecastingPrerequisites> getForecastingPrerequisites(
            @PathVariable Long templateId
    ) {
        return forecastService.getForecastingPrerequisites(templateId);
    }

    /**
     * Gets prerequisites for forecasting.
     *      This method will fetch list of all departments and all BusinessUnit required for forecasting
     *
     * @return forecasting prerequisites
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/bu-details/{buId}", method = RequestMethod.GET)
    public SuccessResponse<BusinessUnitDetails> getBusinessUnitRelatedDetailsForForecasting(
            @PathVariable Long buId
    ) {
        return forecastService.getBusinessUnitRelatedDetailsForForecasting(buId);
    }
}
