package com.msciq.storage.controller;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.ErrorMessage;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.model.ResetPassword;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fpa")
@Slf4j
@CrossOrigin("*")
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
    public SuccessResponse<List<UserViewResponse>> getUser(@RequestParam boolean isDeleted, @RequestParam String status) {

        if(!(status.equalsIgnoreCase("Deleted") && isDeleted) && !(status.equalsIgnoreCase("active") && !isDeleted)){
            return new SuccessResponse<List<UserViewResponse>>
                    (ErrorMessage.INVALID_REQUEST,
                            null,
                            null,
                            HttpStatus.BAD_REQUEST);
        }

        List<UserViewResponse> userViewResponses = userService.getListofUsers(isDeleted,status);

        return new SuccessResponse<List<UserViewResponse>>
                    (SuccessMessage.SUCCESS,
                            userViewResponses,
                            null,
                            HttpStatus.OK);
    }

    /**
     * This method is used to update the given User
     *
     * @param user - model
     * @return updated user details
     * @Param companyName - name of the Company
     */
    @PutMapping("/user/update")
    public SuccessResponse<User> updateUser(@RequestBody User user) {
        try{
            User userUpdated = userService.updateUser(user);
            return new SuccessResponse<User>
                    (SuccessMessage.SUCCESS,
                            userUpdated,
                            null,
                            HttpStatus.OK);
        }catch(Exception e){
            return new SuccessResponse<User>
                    (e.getMessage(),
                            null,
                            null,
                            HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is used to update the given User
     *
     * @param action - action to be performed "delete" or "lock"
     * @param ids - list of ids to perform the action
     * @return updated user details
     * @Param companyName - name of the Company
     */
    @PutMapping("/user/lock-unlock")
    public SuccessResponse<String> lockAndDeleteUser(@RequestParam String action, @RequestBody List<Long> ids) {
        String responseMessage = userService.lockOrUnlock(action, ids);
        if(!responseMessage.equalsIgnoreCase("Users updated successfully")){
            return new SuccessResponse<String>
                    (String.format(responseMessage, Constants.USER),
                            ids,
                            null,
                            HttpStatus.BAD_REQUEST);
        }else{
            return new SuccessResponse<String>
                    (String.format(responseMessage, Constants.USER),
                            ids,
                            null,
                            HttpStatus.OK);
        }
    }
    /**
     * This method is used to remove the given user
     *
     * @param ids - ids of the users to be deleted
     * @return
     */
    @PutMapping("/user/delete")
    public SuccessResponse<String> removeUser(@RequestParam String action,@RequestBody List<Long> ids) {
        if(ids!=null){
            String responseMessage = userService.removeUser(action,ids);
            if(responseMessage.equalsIgnoreCase("The given users are successfully deleted")){
                return new SuccessResponse<String>
                        (String.format(responseMessage, Constants.USER),
                                ids,
                                null,
                                HttpStatus.OK);
            }else{
                return new SuccessResponse<String>
                        (String.format(responseMessage, Constants.USER),
                                ids,
                                null,
                                HttpStatus.BAD_REQUEST);
            }
        }else{
            return new SuccessResponse<String>
                    (String.format("Ids should not be null", Constants.USER),
                            ids,
                            null,
                            HttpStatus.BAD_REQUEST);
        }
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
                    (String.format(loginResponse.getMessage(), Constants.USER),
                            loginResponse,
                            null,
                            HttpStatus.BAD_REQUEST);
        }else{
            return new SuccessResponse<LoginResponse>
                    (String.format(loginResponse.getMessage(), Constants.USER),
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
    public SuccessResponse<ResponseDTO> inviteUsers(
            @PathVariable String orgName,
            @RequestBody @Valid List<UserDTO> users
    ) {
        ResponseDTO responseDTO = userService.inviteUsers(orgName, users);
        if(responseDTO.isError()==true){
            return new SuccessResponse<ResponseDTO>
                    (String.format(responseDTO.getMessage(), Constants.USER),
                            responseDTO,
                            null,
                            HttpStatus.BAD_REQUEST);
        }else{
            return new SuccessResponse<ResponseDTO>
                    (String.format(responseDTO.getMessage(), Constants.USER),
                            responseDTO,
                            null,
                            HttpStatus.CREATED);
        }
    }

    /**
     * This method is used to reset the password for the given email
     *
     * @return Successful or Error message will be shown
     */

    @PostMapping("/user/reset-password-email")
    public SuccessResponse<String> resetPasswordEmail(@RequestBody ResetPassword resetPassword) {
        String responseMessage = userService.resetPasswordEmail(resetPassword);
        if(!responseMessage.equalsIgnoreCase("Mail sent successfully")){
            return new SuccessResponse<String>
                    (String.format(responseMessage, Constants.USER),
                            null,
                            null,
                            HttpStatus.BAD_REQUEST);
        }else{
            return new SuccessResponse<String>
                    (String.format(responseMessage, Constants.USER),
                            null,
                            null,
                            HttpStatus.OK);
        }
    }

    /**
     * This method is used to reset the password for the given email
     *
     * @return Successful or Error message will be shown
     */

    @PostMapping("/user/forgot-password-email")
    public  SuccessResponse<String> forgotPasswordEmail(@RequestBody ResetPassword resetPassword) {
        String responseMessage = userService.forgotPasswordEmail(resetPassword.getEmail());
        if(!responseMessage.equalsIgnoreCase("Mail sent successfully")){
            return new SuccessResponse<String>
                    (String.format(responseMessage, Constants.USER),
                            null,
                            null,
                            HttpStatus.BAD_REQUEST);
        }else{
            return new SuccessResponse<String>
                    (String.format(responseMessage, Constants.USER),
                            null,
                            null,
                            HttpStatus.OK);
        }
    }

    /**
     * This method is used to reset the password for the given email
     *
     * @return Successful or Error message will be shown
     */

    @PostMapping("/user/reset-password")
    public  SuccessResponse<String> resetPassword(@RequestBody ResetPassword resetPassword, @RequestParam String token) {
        String responseMessage = userService.resetPassword(resetPassword.getPassword(), token);
        if(!responseMessage.equalsIgnoreCase("Password has been successfully reset")){
            return new SuccessResponse<String>
                    (String.format(responseMessage, Constants.USER),
                            null,
                            null,
                            HttpStatus.BAD_REQUEST);
        }else{
            return new SuccessResponse<String>
                    (String.format(responseMessage, Constants.USER),
                            null,
                            null,
                            HttpStatus.OK);
        }
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
                    (String.format(loginResponse.getMessage(), Constants.USER),
                            loginResponse,
                            null,
                            HttpStatus.BAD_REQUEST);
        }else{
            return new SuccessResponse<LoginResponse>
                    (String.format(SuccessMessage.LOGIN_SUCCESS, Constants.USER),
                            loginResponse,
                            null,
                            HttpStatus.OK);
        }
    }


}
