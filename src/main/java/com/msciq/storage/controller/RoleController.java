package com.msciq.storage.controller;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.SuccessCode;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.common.entity.Location;
import com.msciq.storage.model.Company;
import com.msciq.storage.model.Role;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.service.RoleService;
import com.msciq.storage.validator.RoleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleValidator roleValidator;

    /**
     * <p>
     * Add new role for some action privileges.
     * </p>
     *
     * @param roles - role information to be added
     * @return Role - response of adding the role
     */
    @PostMapping
    //@PreAuthorize("hasAuthority('Role_Admin:CREATE')")
    public SuccessResponse<List<Role>> addRole(@RequestBody List<Role> roles) {
        validateRoleRequest(roles);
        return new SuccessResponse<List<Role>>(SuccessCode.SUCCESS, roleService.addRole(roles), HttpStatus.CREATED);
    }

    /**
     * <p>
     * Retrieve all the active roles detail list.
     * </p>
     *
     * @return List<Role> - List of Role Entity
     */
    @GetMapping
    //@PreAuthorize("hasAuthority('Role_Admin:READ') or hasAuthority('User_Role_Admin:READ')")
    public SuccessResponse<List<Role>> getAllRoles() {
        List<Role> roleList =  roleService.getAllRoles();
        return new SuccessResponse<List<Role>>(SuccessCode.SUCCESS, roleList, HttpStatus.CREATED);
    }
    private void validateRoleRequest(List<Role> roles) {
        for (Role role:roles) {
            roleValidator.checkIfRoleNameIsValid(role.getName());
        }
    }
}
