package com.msciq.storage.type1Forecast.controller;

import com.msciq.storage.common.SuccessCode;
import com.msciq.storage.model.Type1AForecastingItem;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.type1Forecast.service.Type1ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/type1/forecast")
@Validated
public class Type1ForecastController {

    @Autowired
    Type1ForecastService type1ForecastService;

    /**
     * Save type 1A forecasting items
     *
     * @return 1A forecast items
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/1A", method = RequestMethod.POST)
    public SuccessResponse<List<Type1AForecastingItem>> save1AForecastingItems(
        @RequestBody List<Type1AForecastingItem> Type1AForecastingItems
    ) {
        return new SuccessResponse<List<Type1AForecastingItem>>(SuccessCode.TYPE_1A_FORECAST_SUBMIT_SUCCESS,
                type1ForecastService.save1AForecastingItems(Type1AForecastingItems), HttpStatus.OK);
    }
}
