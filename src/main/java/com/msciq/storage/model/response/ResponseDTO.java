package com.msciq.storage.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.enterprise.inject.Model;

@Model
@Builder
@ToString
@Getter
public class ResponseDTO {

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private String message;
    private boolean isError;
}
