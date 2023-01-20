package com.msciq.storage.profitCenter.controller;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.exception.BadRequestException;
import com.msciq.storage.model.ProfitCenter;
import com.msciq.storage.model.request.ProfitCenterDTO;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.profitCenter.service.ProfitCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/profit-center")
@Validated
public class ProfitCenterController {

    @Autowired
    ProfitCenterService profitCenterService;

    /**
     * Gets all forecasting templates.
     *
     * @return List of profitCenter entity
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public SuccessResponse<List<ProfitCenter>> getAllProfitCenters() {
        List<ProfitCenter> profitCenters = profitCenterService.getAllProfitCenters();
        return new SuccessResponse<List<ProfitCenter>>("Success", profitCenters, null, HttpStatus.OK);
    }

    /**
     * This method is used to add a new Template.
     *
     * @param profitCenterDTOS
     * @return List of profitCenter
     * @author Sivaranjani DS
     */
    @RequestMapping(method = RequestMethod.POST)
    public SuccessResponse<List<ProfitCenter>> addProfitCenters(@RequestBody List<ProfitCenterDTO> profitCenterDTOS) {
        if (profitCenterDTOS.isEmpty()) {
            throw new BadRequestException(19011);
        }
        List<ProfitCenter> profitCenters = profitCenterService.addProfitCenters(profitCenterDTOS);
        return new SuccessResponse<List<ProfitCenter>>
                (String.format(SuccessMessage.SUCCESSFULLY_SAVED, Constants.PROFIT_CENTER),
                        profitCenters,
                        null,
                        HttpStatus.CREATED);
    }

}
