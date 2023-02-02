package com.msciq.storage.forecastPrerequisites.service;

import com.msciq.storage.model.response.BusinessUnitDetails;
import com.msciq.storage.model.response.ForecastingPrerequisites;
import com.msciq.storage.model.response.SuccessResponse;

public interface ForecastPreRequisitesService {

    /**
     * Gets prerequisites for forecasting.
     *      This method will fetch list of all departments and all BusinessUnit required for forecasting
     *
     * @return forecasting prerequisites
     * @author Sivaranjani DS
     */
    public SuccessResponse<ForecastingPrerequisites> getForecastingPrerequisites(Long templateId);

    SuccessResponse<BusinessUnitDetails> getBusinessUnitRelatedDetailsForForecasting(Long buId);
}