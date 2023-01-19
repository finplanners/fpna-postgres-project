package com.msciq.storage.service;

import com.msciq.storage.common.msciq.LockDeleteDTO;
import com.msciq.storage.model.ResetPassword;
import com.msciq.storage.model.User;
import com.msciq.storage.model.request.LoginDTO;
import com.msciq.storage.model.request.UserDTO;
import com.msciq.storage.model.response.LoginResponse;
import com.msciq.storage.model.response.ResponseDTO;
import com.msciq.storage.model.response.UserViewResponse;


import java.util.List;

public interface UserService {

    /**
     * This method is used to create an user in default Namespace
     *
     * @param user - model with user values
     *
     * @return ResponseDTO
     *       which has message and a isError flag
     *       if isError flag is true the message has the error message
     */
    ResponseDTO createUser(User user);

    /**
     * This method is used to fetch an user from default Namespace
     *
     * @param id - id of the user
     *
     * @return User model will be returned with values
     *
     */
    User getUser(Long id);

    /**
     * This method is used to update an user in default Namespace
     *
     * @param user - model with user values
     *
     * @return User model with updated values
     *
     */
    List<User> updateUser(List<UserDTO> users);

    /**
     * This method is used to remove an user from default Namespace(soft delete)
     *
     * @param id - id of the user to be removed
     *
     * @return Successfull or failure message based on the result
     *
     */
    String removeUser(LockDeleteDTO lockDeleteDTO);

    /**
     * This method is used to fetch the list of Users from default Namespace
     *
     * @return List of Users with details
     */
    List<UserViewResponse> getListofUsers(boolean isDeleted);

    /**
     * This method is used to sign up user into
     *
     * @param user - model with sign up details
     *
     * @return String
     *      if sign up was successful or not
     */
    LoginResponse userSignUp(User user,String token);

    /**
     * This method is used to reset password for the given Emai
     *
     * @param resetPassword - model with reset values
     *
     * @return String
     *      if reset password reste email sent successfully
     */
    String resetPasswordEmail(ResetPassword resetPassword);

    /**
     * This method is used to reset password for the given Emai
     *
     * @param resetPassword - model with reset values
     *
     * @return String
     *      if reset password reste email sent successfully
     */
    String forgotPasswordEmail(String email);

    /**
     * This method is used to reset password for the given Emai
     *
     * @param resetPassword - model with reset values
     * @param token
     * @return String
     * if reset password is successful
     */
    String resetPassword(String resetPassword, String token);

    /**
     *
     * This service method is used to validate user email and password.
     *
     */
    LoginResponse userLogin(LoginDTO loginDTO);

    /**
     * This method add new user to the firebase and send mail to the users to set up the password
     *
     * @param orgName
     * @param users
     * @return ResponseDTO
     */
    ResponseDTO inviteUsers(String orgName, List<UserDTO> users);

    String lockOrUnlock(LockDeleteDTO lockDeleteDTO);
}
