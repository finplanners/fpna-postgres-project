package com.msciq.storage.templateType.controller;

import com.msciq.storage.templateType.service.TemplateTypeService;
import com.msciq.storage.common.Constants;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.exception.BadRequestException;
import com.msciq.storage.model.TemplateType;
import com.msciq.storage.model.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/template-type")
@Validated
public class TemplateTypeController {

    @Autowired
    TemplateTypeService templateTypeService;

    /**
     * Gets all template types.
     *
     * @return List of templateType entity
     * @author Sivaranjani DS
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public SuccessResponse<List<TemplateType>> getAllTemplateTypes() {
        List<TemplateType> templateTypes = templateTypeService.getAllTemplateTypes();
        return new SuccessResponse<List<TemplateType>>("Success", templateTypes, null, HttpStatus.OK);
    }

    /**
     * This method is used to add a new Template.
     *
     * @param templateTypes
     * @return List of templateType
     * @author Sivaranjani DS
     */
    @RequestMapping(method = RequestMethod.POST)
    public SuccessResponse<List<TemplateType>> addTemplateTypes(@RequestBody List<TemplateType> templateTypes) {
        if (templateTypes.isEmpty()) {
            throw new BadRequestException(19011);
        }
        List<TemplateType> savedTemplateTypes = templateTypeService.addTemplateTypes(templateTypes);
        return new SuccessResponse<List<TemplateType>>
                (String.format(SuccessMessage.SUCCESSFULLY_SAVED, Constants.TEMPLATE_TYPE),
                        savedTemplateTypes,
                        null,
                        HttpStatus.CREATED);
    }

}
