package com.msciq.storage.forecast.service.impl;

import com.msciq.storage.forecast.repository.ForecastLineItemRepository;
import com.msciq.storage.forecast.repository.ForecastRepository;
import com.msciq.storage.forecast.service.ForecastService;
import com.msciq.storage.model.ForecastData;
import com.msciq.storage.model.ForecastLineItem;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ForecastServiceImpl implements ForecastService {

    @Autowired
    ForecastRepository forecastRepository;

    @Autowired
    ForecastLineItemRepository forecastLineItemRepository;

    @Override
    public ForecastData saveForecastData(ForecastData forecastData) {
        ForecastData savedForecastData = forecastRepository.save(forecastData);
        if (Objects.nonNull(savedForecastData)) {
            List<ForecastLineItem> forecastLineItems = mapForecastJsonDataToForecastLineItemDetails(savedForecastData);
            forecastLineItemRepository.saveAll(forecastLineItems);
        }
        return forecastData;
    }

    private List<ForecastLineItem> mapForecastJsonDataToForecastLineItemDetails(ForecastData savedForecastData) {
        List<ForecastLineItem> forecastLineItems = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(savedForecastData.getJsonData());
        JSONArray jsonArray = jsonObj.getJSONArray("data");
        String templateType = savedForecastData.getTemplateType();
        for (Object jsonObject: jsonArray) {
            JSONObject data = (JSONObject) jsonObject;
            ForecastLineItem forecastLineItem = new ForecastLineItem();
            forecastLineItem.setForecastId(savedForecastData.getId());
            forecastLineItem.setUserEmail(savedForecastData.getUserEmail());
            forecastLineItem.setBu(data.getString("BU"));
            forecastLineItem.setLocation(data.getString("Location"));
            forecastLineItem.setAmount(data.getString("Amount"));
            switch (templateType) {
                case "One-Time":
                    forecastLineItem.setGlAccount(data.getString("GL Account"));
                    forecastLineItem.setProjectCode(data.getString("ProjectCode"));
                    forecastLineItem.setYear(data.getString("YEAR"));
                    forecastLineItem.setMonth(data.getString("MON"));
                    break;
                case "Trend":
                    forecastLineItem.setGlAccount(data.getString("Account"));
                    break;
                case "CAPEX":
                    forecastLineItem.setProjectCode(data.getString("ProjectCode"));
                    forecastLineItem.setGlAccount(data.getString("Equipment Type"));
                    forecastLineItem.setMonth(data.getString("In Service month"));
                    forecastLineItem.setYear(data.getString("In Service Year"));
                    break;
                case "Amortized":
                    forecastLineItem.setGlAccount(data.getString("Type"));
                    forecastLineItem.setMonth(data.getString("Start month"));
                    forecastLineItem.setYear(data.getString("Year"));
                    forecastLineItem.setNoOfMonthsToAmortize(data.getInt("Length(Mons)"));
                    break;
                case "Recurring Expenses":
                    forecastLineItem.setMonth(data.getString("Hire month"));
                    forecastLineItem.setYear(data.getString("Hire Year"));
                    break;
                case "Recurring Time Bound Expenses":
                    forecastLineItem.setGlAccount(data.getString("Consultant type"));
                    forecastLineItem.setMonth(data.getString("Start month"));
                    forecastLineItem.setYear(data.getString("Year"));
                    forecastLineItem.setNoOfRecurringExpenseMonths(data.getInt("Length(Mons)"));
                    break;
            }
            forecastLineItems.add(forecastLineItem);
        }
        return forecastLineItems;
    }
}

