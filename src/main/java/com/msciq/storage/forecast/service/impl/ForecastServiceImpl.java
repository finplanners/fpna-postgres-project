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
            forecastLineItem.setTemplateType(savedForecastData.getTemplateType());
            forecastLineItem.setBu(getStringValueFromJsonObject(data, "BU"));
            forecastLineItem.setLocation(getStringValueFromJsonObject(data, "Location"));
            forecastLineItem.setAmount(getStringValueFromJsonObject(data, "Amount"));
            switch (templateType) {
                case "One-Time":
                    forecastLineItem.setGlAccount(getStringValueFromJsonObject(data, "GL Account"));
                    forecastLineItem.setDepartment(getStringValueFromJsonObject(data, "Department"));
                    forecastLineItem.setProjectCode(getStringValueFromJsonObject(data, "ProjectCode"));
                    forecastLineItem.setYear(getStringValueFromJsonObject(data, "YEAR"));
                    forecastLineItem.setMonth(getStringValueFromJsonObject(data, "MON"));
                    break;
                case "Trend":
                    forecastLineItem.setGlAccount(getStringValueFromJsonObject(data, "Account"));
                    break;
                case "CAPEX":
                    forecastLineItem.setProjectCode(getStringValueFromJsonObject(data, "ProjectCode"));
                    forecastLineItem.setGlAccount(getStringValueFromJsonObject(data, "Equipment Type"));
                    forecastLineItem.setMonth(getStringValueFromJsonObject(data, "In Service month"));
                    forecastLineItem.setYear(getStringValueFromJsonObject(data, "In Service Year"));
                    break;
                case "Amortized":
                    forecastLineItem.setGlAccount(getStringValueFromJsonObject(data, "Type"));
                    forecastLineItem.setMonth(getStringValueFromJsonObject(data, "Start month"));
                    forecastLineItem.setYear(getStringValueFromJsonObject(data, "Year"));
                    forecastLineItem.setNoOfMonthsToAmortize(getIntValueFromJsonObject(data, "Length(Mons)"));
                    break;
                case "Recurring Expenses":
                    forecastLineItem.setMonth(getStringValueFromJsonObject(data, "Hire month"));
                    forecastLineItem.setYear(getStringValueFromJsonObject(data, "Hire Year"));
                    break;
                case "Recurring Time Bound Expenses":
                    forecastLineItem.setGlAccount(getStringValueFromJsonObject(data, "Consultant type"));
                    forecastLineItem.setMonth(getStringValueFromJsonObject(data, "Start month"));
                    forecastLineItem.setYear(getStringValueFromJsonObject(data, "Year"));
                    forecastLineItem.setNoOfRecurringExpenseMonths(getIntValueFromJsonObject(data, "Length(Mons)"));
                    break;
            }
            forecastLineItems.add(forecastLineItem);
        }
        return forecastLineItems;
    }

    private static String getStringValueFromJsonObject(JSONObject data, String key) {
        return data.isNull(key) ? "" : data.getString(key);
    }

    private static int getIntValueFromJsonObject(JSONObject data, String key) {
        return data.isNull(key) ? null : data.getInt(key);
    }
}

