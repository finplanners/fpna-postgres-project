package com.msciq.storage.productGroup.service;

import com.msciq.storage.model.ProductGroup;
import com.msciq.storage.model.request.ProductGroupDTO;

import java.util.List;

public interface ProductGroupService {

    /**
     * This method returns list of all forecasting templates
     *
     * @return List of ForecastingTemplate Entity
     * @author Sivaranjani DS
     */
    public List<ProductGroup> getAllProductGroups();

    /**
     * This method adds list of template
     *
     * @param productGroupDTOS
     * @return ForecastingTemplate Entity
     * @author Sivaranjani DS
     */
    public List<ProductGroup> addProductGroups(List<ProductGroupDTO> productGroupDTOS);

}
