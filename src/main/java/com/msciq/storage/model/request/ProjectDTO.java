package com.msciq.storage.model.request;

import com.msciq.storage.common.ErrorMessage;
import com.msciq.storage.common.FieldConstants;
import com.msciq.storage.common.RegexPattern;
import com.msciq.storage.common.entity.BusinessUnit;
import com.msciq.storage.model.ProductGroup;
import com.msciq.storage.model.ProfitCenter;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ProjectDTO {

    private Long id;

    @Pattern(regexp = RegexPattern.ALPHA_NUMERIC_REGEX_PATTERN,
            message = FieldConstants.CODE + ErrorMessage.NOT_ALPHANUMERIC)
    @Size(min = 1, max = 25, message = FieldConstants.CODE + ErrorMessage.MIN_LENGTH_25_ERROR)
    private String code;

    @Pattern(regexp = RegexPattern.STRING_PATTERN,
            message = FieldConstants.NAME + ErrorMessage.NOT_ALPHABET)
    @Size(min = 1, max = 100, message = FieldConstants.CODE + ErrorMessage.LENGTH_100_ERROR)
    private String name;

    private BusinessUnit businessUnit;

    private ProductGroup productGroup;

    private ProfitCenter profitCenter;

    private String projectOwnerName;

    @Email(message = "Email is invalid")
    private String projectOwnerEmail;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate inactiveDate;

}
