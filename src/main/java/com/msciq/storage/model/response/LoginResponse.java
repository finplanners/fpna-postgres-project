package com.msciq.storage.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msciq.storage.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ToString
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class LoginResponse {

    private String idToken;

    private boolean isError;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private String message;

    private User user;

}
