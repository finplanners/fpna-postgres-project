package com.msciq.storage.common.msciq;

import lombok.Data;

@Data
public class CountryDTO {

    private Long id;
    private String name;

    private String countryCode;
    private String description;
}
