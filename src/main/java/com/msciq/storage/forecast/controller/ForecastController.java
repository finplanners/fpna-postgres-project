package com.msciq.storage.forecast.controller;

import com.msciq.storage.common.SuccessCode;
import com.msciq.storage.model.ForecastData;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.forecast.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
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
     * Save type 1A forecasting items
     *
     * @return 1A forecast items
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public SuccessResponse<ForecastData> saveForecastingItems(
        @RequestBody ForecastData forecastData
    ) {
        return new SuccessResponse<ForecastData>(SuccessCode.FORECAST_SUBMIT_SUCCESS,
                forecastService.saveForecastData(forecastData), HttpStatus.OK);
    }
}
