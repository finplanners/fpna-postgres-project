package com.msciq.storage.forecastPrerequisites.controller;

import com.msciq.storage.forecastPrerequisites.service.ForecastPreRequisitesService;
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
@RequestMapping(value = "/forecast-pre-requisites")
@Validated
public class ForecastPreRequisitesController {

    @Autowired
    ForecastPreRequisitesService forecastService;

    /**
     * Gets prerequisites for forecasting.
     *      This method will fetch list of all departments and all BusinessUnit required for forecasting
     *
     * @return forecasting prerequisites
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/{userEmail}/{templateId}", method = RequestMethod.GET)
    public SuccessResponse<ForecastingPrerequisites> getForecastingPrerequisites(
            @PathVariable Long templateId,
            @PathVariable String userEmail
    ) {
        return forecastService.getForecastingPrerequisites(templateId, userEmail);
    }

    /**
     * Gets prerequisites for forecasting.
     *      This method will fetch list of all departments and all BusinessUnit required for forecasting
     *
     * @return forecasting prerequisites
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/{buId}/bu-details", method = RequestMethod.GET)
    public SuccessResponse<BusinessUnitDetails> getBusinessUnitRelatedDetailsForForecasting(
            @PathVariable Long buId
    ) {
        return forecastService.getBusinessUnitRelatedDetailsForForecasting(buId);
    }
}
