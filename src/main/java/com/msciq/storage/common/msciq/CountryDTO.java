package com.msciq.storage.common.msciq;

import lombok.Data;

import java.util.List;

@Data
public class CountryDTO {

    private Long id;
    private String name;

    private String countryCode;
    private String description;

    private List<StateDTO> states;
}
