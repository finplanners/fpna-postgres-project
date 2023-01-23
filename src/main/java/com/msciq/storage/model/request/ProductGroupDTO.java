package com.msciq.storage.model.request;

import com.msciq.storage.common.ErrorMessage;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.RegexPattern;
import com.msciq.storage.common.entity.BusinessUnit;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ProductGroupDTO {

    private Long id;

    @Pattern(regexp = RegexPattern.ALPHA_NUMERIC_REGEX_PATTERN,
            message = FieldConstants.CODE + ErrorMessage.NOT_ALPHANUMERIC)
    @Size(min = 1, max = 15, message = FieldConstants.CODE + ErrorMessage.MIN_LENGTH_15_ERROR)
    private String code;

    @Pattern(regexp = RegexPattern.ALPHA_NUMERIC_REGEX_PATTERN,
            message = FieldConstants.DESCRIPTION + ErrorMessage.NOT_ALPHANUMERIC)
    @Size(min = 1, max = 50, message = FieldConstants.DESCRIPTION + ErrorMessage.MIN_LENGTH_50_ERROR)
    private String description;

    private BusinessUnit businessUnit;

}
