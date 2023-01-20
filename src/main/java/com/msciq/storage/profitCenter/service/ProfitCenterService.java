package com.msciq.storage.profitCenter.service;

import com.msciq.storage.model.ProfitCenter;
import com.msciq.storage.model.request.ProfitCenterDTO;

import java.util.List;

public interface ProfitCenterService {

    /**
     * This method returns list of all forecasting templates
     *
     * @return List of ForecastingTemplate Entity
     * @author Sivaranjani DS
     */
    public List<ProfitCenter> getAllProfitCenters();

    /**
     * This method adds list of template
     *
     * @param templates
     * @return ForecastingTemplate Entity
     * @author Sivaranjani DS
     */
    public List<ProfitCenter> addProfitCenters(List<ProfitCenterDTO> templates);

}
