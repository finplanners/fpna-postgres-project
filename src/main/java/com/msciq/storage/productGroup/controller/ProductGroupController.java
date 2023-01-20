package com.msciq.storage.productGroup.controller;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.exception.BadRequestException;
import com.msciq.storage.model.ProductGroup;
import com.msciq.storage.model.request.ProductGroupDTO;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.productGroup.service.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/product-group")
@Validated
public class ProductGroupController {

    @Autowired
    ProductGroupService productGroupService;

    /**
     * Gets all forecasting templates.
     *
     * @return List of productGroup entity
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public SuccessResponse<List<ProductGroup>> getAllProductGroups() {
        List<ProductGroup> productGroups = productGroupService.getAllProductGroups();
        return new SuccessResponse<List<ProductGroup>>("Success", productGroups, null, HttpStatus.OK);
    }

    /**
     * This method is used to add a new Template.
     *
     * @param productGroups
     * @return List of productGroup
     * @author Sivaranjani DS
     */
    @RequestMapping(method = RequestMethod.POST)
    public SuccessResponse<List<ProductGroup>> addProductGroups(@RequestBody List<ProductGroupDTO> productGroups) {
        if (productGroups.isEmpty()) {
            throw new BadRequestException(19011);
        }
        List<ProductGroup> savedProductGroups = productGroupService.addProductGroups(productGroups);
        return new SuccessResponse<List<ProductGroup>>
                (String.format(SuccessMessage.SUCCESSFULLY_SAVED, Constants.PRODUCT_GROUP),
                        savedProductGroups,
                        null,
                        HttpStatus.CREATED);
    }

}
