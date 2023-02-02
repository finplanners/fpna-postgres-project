package com.msciq.storage.controller;

import com.msciq.storage.common.Constants;
import com.msciq.storage.common.ErrorMessage;
import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.common.msciq.LockDeleteDTO;
import com.msciq.storage.model.ResetPassword;
import com.msciq.storage.model.User;
import com.msciq.storage.model.request.LoginDTO;
import com.msciq.storage.model.request.UserDTO;
import com.msciq.storage.model.response.LoginResponse;
import com.msciq.storage.model.response.ResponseDTO;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.service.UserService;
import com.msciq.storage.service.impl.GCPStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fpa")
@Slf4j
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Add a new User in the respective Company
     *
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
    public SuccessResponse<List<User>> getUser(@RequestParam String status) {

        if( status.equalsIgnoreCase("Deleted") && !status.equalsIgnoreCase("all") ){
            return new SuccessResponse<List<User>>
                    (ErrorMessage.INVALID_REQUEST,
                            null,
                            null,
                            HttpStatus.BAD_REQUEST);
        }

        List<User> userViewResponses = userService.getListofUsers(status);

        return new SuccessResponse<List<User>>
                    (SuccessMessage.SUCCESS,
                            userViewResponses,
                            null,
                            HttpStatus.OK);
    }

    /**
     * This method is used to update the given User
     *
     * @param users - list of users to be updated
     * @return updated user details
     * @Param companyName - name of the Company
     */
    @PutMapping("/user/update")
    public SuccessResponse<List<User>> updateUser(@RequestBody List<UserDTO> users) {
        List<User> usersUpdated= userService.updateUser(users);
            return new SuccessResponse<List<User>>
                    (SuccessMessage.USERS_UPDATE_SUCCESS,
                            usersUpdated,
                            null,
                            HttpStatus.OK);

    }

    /**
     * This method is used to update the given User
     *
     * @param lockDeleteDTO - lockDeleteDTO
     * @return updated user details
     * @Param companyName - name of the Company
     */
    @PutMapping("/user/lock-unlock")
    public SuccessResponse<String> lockAndDeleteUser(@RequestBody LockDeleteDTO lockDeleteDTO) {
        String responseMessage = userService.lockOrUnlock(lockDeleteDTO);
        if(!responseMessage.equalsIgnoreCase("Users locked successfully") && !responseMessage.equalsIgnoreCase("Users activated successfully")){
            return new SuccessResponse<String>
                    (String.format(responseMessage, Constants.USER),
                            null,
                            null,
                            HttpStatus.BAD_REQUEST);
        }else{
            return new SuccessResponse<String>
                    (String.format(responseMessage, Constants.USER),
                            lockDeleteDTO,
                            null,
                            HttpStatus.OK);
        }
    }
    /**
     * This method is used to remove the given user
     *
     * @param lockDeleteDTO - lockDeleteDTO
     * @return
     */
    @PutMapping("/user/delete")
    public SuccessResponse<String> removeUser(@RequestBody LockDeleteDTO lockDeleteDTO) {
        if(lockDeleteDTO.getIds()!=null){
            String responseMessage = userService.removeUser(lockDeleteDTO);
            if(responseMessage.equalsIgnoreCase("Users deleted successfully") || responseMessage.equalsIgnoreCase("Users activated successfully") ){
                return new SuccessResponse<String>
                        (String.format(responseMessage, Constants.USER),
                                lockDeleteDTO,
                                null,
                                HttpStatus.OK);
            }else{
                return new SuccessResponse<String>
                        (String.format(responseMessage, Constants.USER),
                                null,
                                null,
                                HttpStatus.BAD_REQUEST);
            }
        }else{
            return new SuccessResponse<String>
                    (String.format("Ids should not be null", Constants.USER),
                            null,
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
        if(!responseMessage.equalsIgnoreCase("Password reset notification sent to your email")){
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

    @PostMapping("/user/forgot-password")
    public  SuccessResponse<String> forgotPasswordEmail(@RequestBody ResetPassword resetPassword) {
        String responseMessage = userService.forgotPasswordEmail(resetPassword.getEmail());
        if(!responseMessage.equalsIgnoreCase("Password reset notification sent to your email")){
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
        if(!responseMessage.equalsIgnoreCase("Your account password has been updated successfully")){
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
