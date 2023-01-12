package com.msciq.storage.controller;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.model.ResetPassword;
import com.msciq.storage.model.Role;
import com.msciq.storage.model.User;
import com.msciq.storage.model.request.LoginDTO;
import com.msciq.storage.model.request.UserDTO;
import com.msciq.storage.model.response.LoginResponse;
import com.msciq.storage.model.response.ResponseDTO;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.model.response.UserViewResponse;
import com.msciq.storage.service.UserService;
import com.msciq.storage.service.impl.GCPStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fpa")
@Slf4j
public class UserController {

    @Autowired
    GCPStorageServiceImpl gcpStorageService;

    @Autowired
    UserService userService;

    /**
     * This method is used to create a User in the respective Company
     *
     * @param user - model
     * @return Successful message on creating the new User
     */
    @PostMapping("/create/user")
    public ResponseDTO createUser(
            @RequestBody User user
    ) {
        try {
            return userService.createUser(user);

        } catch (Exception exception) {
            return ResponseDTO.builder()
                    .message("Error while creating User- " + exception.getMessage())
                    .isError(true)
                    .build();
        }
    }

    /**
     * This method is used to retrieve a User from the given company
     *
     * @param id - id of the User
     * @return User details will be returned
     */
    @GetMapping("/get/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    /**
     * This method is used to retrieve a User from the given company
     *
     * @return List of Users will be returned
     */
    @GetMapping("/get/user")
    public List<UserViewResponse> getUser() {
        return userService.getListofUsers();
    }

    /**
     * This method is used to update the given User
     *
     * @param user - model
     * @return updated user details
     * @Param companyName - name of the Company
     */
    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * This method is used to remove the given user
     *
     * @param id - id of the user
     * @return
     */
    @PutMapping("/user/delete/{id}")
    public String removeUser(@PathVariable Long id) {
        return userService.removeUser(id);
    }

    /**
     * This method is used to create a User in the firebase authentication
     *
     * @param user - model
     * @return LoginResponse - model
     */
    @PostMapping("/user/sign-up")
    public SuccessResponse<LoginResponse> userSignUp(
            @Valid @RequestBody User user,
            @RequestParam String token
    ) {
        LoginResponse loginResponse = userService.userSignUp(user,token);
        if(loginResponse.isError()==true){
            return new SuccessResponse<LoginResponse>
                    (String.format(SuccessMessage.USER_NOT_SAVED, Constants.USER),
                            loginResponse,
                            null,
                            HttpStatus.BAD_REQUEST);
        }else{
            return new SuccessResponse<LoginResponse>
                    (String.format(SuccessMessage.SUCCESSFULLY_SAVED, Constants.USER),
                            loginResponse,
                            null,
                            HttpStatus.CREATED);
        }

    }

    /**
     * This method is used to invite new User to teh system
     *
     * @param orgName
     * @param users
     * @return ResponseDTO
     */
    @PostMapping("/{orgName}/invite-users")
    public ResponseDTO inviteUsers(
            @PathVariable String orgName,
            @RequestBody @Valid List<UserDTO> users
    ) {
        return userService.inviteUsers(orgName, users);
    }

    /**
     * This method is used to reset the password for the given email
     *
     * @return Successful or Error message will be shown
     */

    @PostMapping("/user/reset-password-email")
    public String resetPasswordEmail(@RequestBody ResetPassword resetPassword) {
        return userService.resetPasswordEmail(resetPassword);
    }

    /**
     * This method is used to reset the password for the given email
     *
     * @return Successful or Error message will be shown
     */

    @PostMapping("/user/reset-password")
    public String resetPassword(@RequestParam String resetPassword, @RequestParam String token) {
        return userService.resetPassword(resetPassword, token);
    }

    /**
     * This method is used to login with user name and password
     *
     * @param loginDTO - model
     * @return LoginResponse - model
     */
    @PostMapping("/user/login")
    public SuccessResponse<LoginResponse> userLogin(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.userLogin(loginDTO);
        if(loginResponse.isError()==true){
            return new SuccessResponse<LoginResponse>
                    (String.format(SuccessMessage.USER_NOT_SAVED, Constants.USER),
                            loginResponse,
                            null,
                            HttpStatus.BAD_REQUEST);
        }else{
            return new SuccessResponse<LoginResponse>
                    (String.format(SuccessMessage.SUCCESSFULLY_SAVED, Constants.USER),
                            loginResponse,
                            null,
                            HttpStatus.OK);
        }
    }


}
