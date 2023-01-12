package com.msciq.storage.validator;

import com.msciq.storage.common.Constants;
import com.msciq.storage.exception.RoleNameStartsWithDefaultException;
import org.springframework.stereotype.Component;

@Component
public class RoleValidator {
    public void checkIfRoleNameIsValid(String roleName) {
        if (Validator.checkIfTheCustomRoleNameIsValid(roleName)) {
            throw new RoleNameStartsWithDefaultException(Constants.ROLE, roleName);
        }
    }
}
