package com.msciq.storage.model.request;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.ErrorMessage;
import com.msciq.storage.common.RegexPattern;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ProfitCenterDTO {

    private Long id;

    private String parentProfitCenter;

    @Pattern(regexp = RegexPattern.ALPHA_NUMERIC_REGEX_PATTERN,
            message = Constants.PROFIT_CENTER + ErrorMessage.NOT_ALPHANUMERIC)
    @Size(min = 1, max = 15, message = Constants.PROFIT_CENTER + ErrorMessage.MIN_LENGTH_15_ERROR)
    private String code;

    @Pattern(regexp = RegexPattern.ALPHA_NUMERIC_REGEX_PATTERN,
            message = Constants.PROFIT_CENTER + ErrorMessage.NOT_ALPHANUMERIC)
    @Size(min = 1, max = 100, message = Constants.PROFIT_CENTER + ErrorMessage.LENGTH_100_ERROR)
    private String name;

    private String profitCenterOwner;

    @Email(message = "Email is invalid")
    private String email;

    private LocalDate activeFromDate;

    private LocalDate inactiveDate;

}
