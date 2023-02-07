package com.msciq.storage.forecast.service.impl;

import com.msciq.storage.common.entity.Department;
import com.msciq.storage.exception.DataConflictException;
import com.msciq.storage.forecast.repository.ForecastLineItemRepository;
import com.msciq.storage.forecast.repository.ForecastRepository;
import com.msciq.storage.forecast.service.ForecastService;
import com.msciq.storage.model.ForecastData;
import com.msciq.storage.model.ForecastDetails;
import com.msciq.storage.model.Template;
import com.msciq.storage.model.User;
import com.msciq.storage.service.DataService;
import com.msciq.storage.service.UserService;
import com.msciq.storage.template.service.TemplateService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ForecastServiceImpl implements ForecastService {

    @Autowired
    ForecastRepository forecastRepository;

    @Autowired
    DataService dataService;

    @Autowired
    UserService userService;

    @Autowired
    TemplateService templateService;

    @Autowired
    ForecastLineItemRepository forecastLineItemRepository;

    @Override
    public ForecastData saveForecastData(ForecastData forecastData) {
        System.out.println("json data -> " + forecastData.getJsonData());
        User userInfo = userService.getUser(forecastData.getUserId());
        List<Department> departments = dataService.getAllDepartmentByUser(userInfo.getEmail());
        List<Long> departmentIds = departments.stream().map(department -> {
            return department.getId();
        }).collect(Collectors.toList());
        List<Template> templates = templateService.getAllForecastingTemplatesByDepart(departmentIds);
        Stream<Template> filteredTemplates = templates.stream().filter(template1 -> {
            return template1.getType().equals(forecastData.getTemplateType());
        });
        if (!filteredTemplates.toList().isEmpty()) {
            ForecastData savedForecastData = forecastRepository.save(forecastData);
            if (Objects.nonNull(savedForecastData)) {
                List<ForecastDetails> forecastDetails = mapForecastJsonDataToForecastLineItemDetails(savedForecastData);
                forecastLineItemRepository.saveAll(forecastDetails);
            }
            return forecastData;
        }
        throw new DataConflictException(19100, forecastData.getTemplateType());
    }

    private List<ForecastDetails> mapForecastJsonDataToForecastLineItemDetails(ForecastData savedForecastData) {
        List<ForecastDetails> forecastDetailsList = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(savedForecastData.getJsonData());
        JSONArray jsonArray = jsonObj.getJSONArray("data");
        String templateType = savedForecastData.getTemplateType();
        for (Object jsonObject: jsonArray) {
            JSONObject data = (JSONObject) jsonObject;
            ForecastDetails forecastDetails = new ForecastDetails();
            forecastDetails.setForecastId(savedForecastData.getId());
            forecastDetails.setUserId(String.valueOf(savedForecastData.getUserId()));
            forecastDetails.setBu(getStringValueFromJsonObject(data, "BU"));
            forecastDetails.setLocation(getStringValueFromJsonObject(data, "Location"));
            forecastDetails.setAmount(getStringValueFromJsonObject(data, "Amount"));
            forecastDetails.setDepartment(getStringValueFromJsonObject(data, "Department"));
            forecastDetails.setProjectCode(getStringValueFromJsonObject(data, "ProjectCode"));
            switch (templateType) {
                case "One-Time":
                    forecastDetails.setGlAccount(getStringValueFromJsonObject(data, "GL Account"));

                    forecastDetails.setYear(getStringValueFromJsonObject(data, "YEAR"));
                    forecastDetails.setMonth(getStringValueFromJsonObject(data, "MON"));
                    break;
                case "Trend":
                    forecastDetails.setGlAccount(getStringValueFromJsonObject(data, "Account"));
                    break;
                case "CAPEX":
                    forecastDetails.setGlAccount(getStringValueFromJsonObject(data, "Equipment Type"));
                    forecastDetails.setMonth(getStringValueFromJsonObject(data, "In Service month"));
                    forecastDetails.setYear(getStringValueFromJsonObject(data, "In Service Year"));
                    break;
                case "Amortized":
                    forecastDetails.setGlAccount(getStringValueFromJsonObject(data, "Type"));
                    forecastDetails.setMonth(getStringValueFromJsonObject(data, "Start month"));
                    forecastDetails.setYear(getStringValueFromJsonObject(data, "Year"));
                    forecastDetails.setNoOfMonthsToAmortize(getIntValueFromJsonObject(data, "Length(Mons)"));
                    break;
                case "Recurring Expenses":
                    forecastDetails.setMonth(getStringValueFromJsonObject(data, "HIRE_MONTH"));
                    forecastDetails.setYear(getStringValueFromJsonObject(data, "HIRE_YEAR"));
                    forecastDetails.setHiringManager(getStringValueFromJsonObject(data, "HiringManager"));
                    forecastDetails.setJobTitle(getStringValueFromJsonObject(data, "JobTitle"));
                    forecastDetails.setAmount(getStringValueFromJsonObject(data, "Annual_Salary"));
                    break;
                case "Recurring Time Bound Expenses":
                    forecastDetails.setGlAccount(getStringValueFromJsonObject(data, "Consultant type"));
                    forecastDetails.setMonth(getStringValueFromJsonObject(data, "Start month"));
                    forecastDetails.setYear(getStringValueFromJsonObject(data, "Year"));
                    forecastDetails.setNoOfRecurringExpenseMonths(getIntValueFromJsonObject(data, "Length(Mons)"));
                    break;
            }
            forecastDetailsList.add(forecastDetails);
        }
        return forecastDetailsList;
    }

    private static String getStringValueFromJsonObject(JSONObject data, String key) {
        return data.isNull(key) ? "" : data.getString(key);
    }

    private static int getIntValueFromJsonObject(JSONObject data, String key) {
        return data.isNull(key) ? null : data.getInt(key);
    }
}

