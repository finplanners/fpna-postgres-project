package com.msciq.storage.service.impl;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.msciq.storage.model.*;
import com.msciq.storage.model.request.LoginDTO;
import com.msciq.storage.model.request.UserDTO;
import com.msciq.storage.model.response.LoginResponse;
import com.msciq.storage.model.response.ResponseDTO;
import com.msciq.storage.model.response.UserViewResponse;
import com.msciq.storage.repository.RolePermissionMappingRepository;
import com.msciq.storage.repository.UserRepository;
import com.msciq.storage.repository.UserRoleMappingRepository;
import com.msciq.storage.security.Actions;
import com.msciq.storage.security.JwtUtil;
import com.msciq.storage.service.EmailService;
import com.msciq.storage.service.RolePermissionMappingService;
import com.msciq.storage.service.UserManagementService;
import com.msciq.storage.service.UserService;
import com.msciq.storage.common.Constants;
import com.msciq.storage.common.ErrorMessage;
import com.msciq.storage.common.SuccessMessage;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private RolePermissionMappingRepository rolePermissionMappingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RolePermissionMappingService rolePermissionMappingService;

    @Autowired
    private UserRoleMappingRepository userRoleMappingRepository;

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    JwtUtil jwtUtil;


    public ResponseDTO createUser(User user) {
        try {
            user.setActive(true);
            user.setStatus(Constants.USER_STATUS.Active.toString());
            user.setVerified(true);
            User userCreated = (User) userRepository.save(user);
            return ResponseDTO.builder()
                    .message("user " + user + " created successfully")
                    .isError(false)
                    .build();
        } catch (Exception e) {
        return ResponseDTO.builder()
                .message("Error while creating user - " + e.getMessage())
                .isError(true)
                .build();
        }
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public List<UserViewResponse> getListofUsers(boolean isDeleted)  {
        List<User> users = userRepository.findByUserStatus(isDeleted);
        List<UserViewResponse> userViewResponses = new ArrayList<>();
        for (User user : users)
        {
            UserViewResponse userViewResponse =  new UserViewResponse();
            List<UserRoleMapping> userRoleMappings = userRoleMappingRepository.getAllRoleByUserId(user.getId());
            Set<String> userRoles =  userRoleMappings.stream().filter(v->!v.isDeleted() && v.isActive()).map(p->p.getRoleName()).collect(Collectors.toSet());
            userViewResponse.setUserRoles(userRoles);
            userViewResponse.setEmail(user.getEmail());
            userViewResponse.setFirstName(user.getFirstName());
            userViewResponse.setLastName(user.getLastName());
            userViewResponse.setActive(user.isActive());
            userViewResponse.setPhoneNumber(user.getPhoneNumber());
            userViewResponse.setId(user.getId());
            userViewResponse.setOrganizationName(user.getOrganizationName());
            userViewResponse.setStatus(user.getStatus());
            userViewResponses.add(userViewResponse);

        }
        return userViewResponses;
    }
    public User updateUser(User user) {
        Optional<User> userFromDb = userRepository.findById(user.getId());
        User userModified = userFromDb.get();
        if(user!=null){
            if(user.getEmail()!=null && !user.getEmail().isEmpty())
                userModified.setEmail(user.getEmail());
            if(user.getOrganizationName()!=null && !user.getOrganizationName().isEmpty())
                userModified.setOrganizationName(user.getOrganizationName());
            if(user.getFirstName()!=null && !user.getFirstName().isEmpty())
                userModified.setFirstName(user.getFirstName());
            if(user.getLastName()!=null && !user.getLastName().isEmpty())
                userModified.setLastName(user.getLastName());
            if(user.getStatus()!=null && !user.getStatus().isEmpty())
                userModified.setStatus(user.getStatus());
            if(user.getPhoneNumber()!=null && !user.getPhoneNumber().isEmpty())
                userModified.setPhoneNumber(user.getPhoneNumber());
        }

        return (User) userRepository.save(userModified);
    }

    public String removeUser(String action,List<Long> ids) {
        try{
            List<User> users = userRepository.findByIdIn(ids);
            if(users.size() == 0){
                return "Invalid user";
            }
            for (User user:users) {
                if(action.equalsIgnoreCase("delete")){
                    user.setDeleted(true);
                    user.setStatus(Constants.USER_STATUS.Deleted.toString());
                    user.setActive(false);
                }else if(action.equalsIgnoreCase("reactivate")){
                    user.setDeleted(false);
                    user.setStatus(Constants.USER_STATUS.Active.toString());
                    user.setActive(true);
                }else{
                    return "Invalid Action Type";
                }
                userRepository.save(user);

            }
            return "The given users are successfully deleted";
        }catch(Exception e){
            return e.getMessage();
        }

    }

    public String saveUserInGivenNamespace(User user, Datastore datastore) {
        Key taskKey = datastore.newKeyFactory()
                .setKind("User")
                .newKey(String.valueOf(UUID.randomUUID()));
        Entity newEntity = Entity.newBuilder(taskKey)
                .set("firstName", user.getFirstName())
                .set("lastName", user.getLastName())
                .set("email", user.getEmail())
                .set("phoneNumber", user.getPhoneNumber())
                .set("isActive", false)
                .set("isVerified", false)
                .set("createdDt", String.valueOf(LocalDateTime.now(ZoneOffset.UTC)))
                .set("updatedDt", String.valueOf(LocalDateTime.now(ZoneOffset.UTC)))
                .set("createdBy", "fpa")
                .set("modifiedBy", "fpa")
                .build();
        datastore.put(newEntity);
        // Company companyCreated = (Company) gcpStorageRepository.save(company);
        return user.getFirstName() + " is successfully created";
    }

    public LoginResponse userSignUp(User user,String token) {
        LoginResponse loginResponse=new LoginResponse();
        List<UserRoleMapping> userRoleMappings = new ArrayList<>();

        try{
            log.info(" user sign-up start");
            log.info(token);
            byte[] decodedBytes = Base64.getDecoder().decode(token);
            String decodedString =  new String(decodedBytes);
            JSONObject jsonObject = new JSONObject(decodedString);
            // validate the token
            if(user.getEmail().equals(jsonObject.get("email"))){
                LoginDTO loginDTO = new LoginDTO();
                loginDTO.setEmail(user.getEmail());
                loginDTO.setPassword(user.getPassword());
                User userToBeSaved = new User();
                userToBeSaved.setUserType(Constants.SIGN_UP_USER_DEFAULT_TYPE);
                userToBeSaved.setActive(true);
                userToBeSaved.setStatus(Constants.USER_STATUS.Active.toString());
                userToBeSaved.setVerified(true);
                userToBeSaved.setPassword(Base64.getEncoder()
                        .encodeToString(user.getPassword().getBytes()));
                userToBeSaved.setOrganizationName(user.getOrganizationName());
                userToBeSaved.setFirstName(user.getFirstName());
                userToBeSaved.setLastName(user.getLastName());
                userToBeSaved.setEmail(user.getEmail());
                userToBeSaved.setPhoneNumber(user.getPhoneNumber());

                // added user details in Postgres
                User userCreated =  userRepository.save(userToBeSaved);
                loginResponse.setUser(userCreated);

                     userRoleMappings.add(UserRoleMapping.builder()
                            .userId(userCreated.getId())
                            .roleName(Constants.SIGN_UP_USER_DEFAULT_TYPE)
                            .build());

                // map user and roles in user role mapping table
                 userRoleMappingRepository.saveAll(userRoleMappings);
                 // set claims
                String jwtToken = jwtUtil.generateToken(user.getEmail());
                loginResponse.setIdToken(jwtToken);
                loginResponse.setMessage(Constants.SIGN_UP);
            }else{
                loginResponse.setError(true);
                loginResponse.setMessage(ErrorMessage.UNAUTHORIZED);
            }
            return loginResponse;
        } catch(DataIntegrityViolationException e){
            loginResponse.setMessage(Constants.EMAIL_ALREADY_EXISTS);
            loginResponse.setError(true);
            return loginResponse;
        } catch (Exception e){
            log.error(ErrorMessage.ERROR+e.getMessage());
            if(e.getMessage().contains("WEAK_PASSWORD")){
                loginResponse.setMessage("Password should be at least 6 characters");
            }else if(e.getMessage().contains("EMAIL_EXISTS")){
                loginResponse.setMessage("Email already exists");
            } else if(e.getMessage().contains("MISSING_EMAIL")){
                loginResponse.setMessage("Email is mandatory");
            } else{
                loginResponse.setMessage(ErrorMessage.ERROR+e.getMessage());
            }
            loginResponse.setError(true);
            return loginResponse;
        }
    }

    @Override
    public String resetPasswordEmail(ResetPassword resetPassword) {
        try{
        log.info(" user reset password start");
        resetPassword.setRequestType("PASSWORD_RESET");
        User userFromDb = userRepository.findByEmail(resetPassword.getEmail());
        if(userFromDb!=null){
            sendMailForPasswordReset(resetPassword);
            return "Mail sent successfully";
        }else{
            return "User does not exist";
        }
        }catch (Exception e){
            return ErrorMessage.RESET_PASSWORD_ERROR+e.getMessage();
        }
    }

    @Override
    public String forgotPasswordEmail(String email) {
        ResetPassword resetPassword= new ResetPassword();
        resetPassword.setEmail(email);
        return resetPasswordEmail(resetPassword);
    }

    @Override
    public String resetPassword(String resetPassword, String token) {
        try{
            byte[] decodedBytes = Base64.getDecoder().decode(token);
            String decodedString =  new String(decodedBytes);
            JSONObject jsonObject = new JSONObject(decodedString);
            User userPasswordToReset = userRepository.findByEmail(jsonObject.get("email").toString());
            // validate the token
            if(null!= userPasswordToReset) {
                userPasswordToReset.setPassword(Base64.getEncoder()
                        .encodeToString(resetPassword.getBytes()));
                userPasswordToReset.setActive(true);
                userPasswordToReset.setStatus(Constants.USER_STATUS.Active.toString());
                userPasswordToReset.setVerified(true);
                // added user details in Postgres
                userRepository.save(userPasswordToReset);
                return "Password has been successfully reset";
            }else{
                return "User Does not exists";
            }
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public  LoginResponse userLogin(LoginDTO loginDTO){
        LoginResponse response = new LoginResponse();

        try{
            User userfromDB = userRepository.findByEmail(loginDTO.getEmail());
            if(null!=userfromDB) {
                byte[] decodedBytes = Base64.getDecoder().decode(userfromDB.getPassword());
                String decodedPassword = new String(decodedBytes);

                if (decodedPassword.equals(loginDTO.getPassword())) {
                    log.info(" user login start");
                    loginDTO.setReturnSecureToken(true);
                    log.info(String.valueOf(loginDTO));
                    String jwtToken = jwtUtil.generateToken(loginDTO.getEmail());
                    response.setIdToken(jwtToken);
                    response.setMessage(SuccessMessage.LOGIN_SUCCESS);
                    response.setUser(userfromDB);
                    log.info(" user login end");
                    return response;
                } else {
                    response.setError(true);
                    response.setMessage(ErrorMessage.INVALID_CREDENTIALS);
                    return response;
                }
            }else{
                response.setError(true);
                response.setMessage(ErrorMessage.INVALID_CREDENTIALS);
                return response;
            }

        }catch (Exception e){
            log.error(ErrorMessage.ERROR+e.getMessage());
            response.setError(true);
            response.setMessage(ErrorMessage.ERROR+e.getMessage());
            return response;
        }
    }

    @Override
    public ResponseDTO inviteUsers(String orgName, List<UserDTO> users) {
        try {
            for (UserDTO user : users) {
                User newUser = new User();
                newUser.setEmail(user.getEmail());
                newUser.setFirstName(user.getFirstName());
                newUser.setLastName(user.getLastName());
                newUser.setPhoneNumber(user.getPhoneNumber());
                newUser.setActive(false);
                newUser.setVerified(false);
                newUser.setUserType(user.getRoles().toString());
                newUser.setOrganizationName(orgName);
                newUser.setCreatedBy(Long.valueOf(1));
                newUser.setStatus(Constants.USER_STATUS.Pending.toString());
                User userCreated = userRepository.save(newUser);

                List<UserRoleMapping> userRoleMappings = new ArrayList<>();
                //Map<String,Map<String, Set<Actions>>> claimsData = new HashMap<>();
                sendMailToOrganization(userCreated);
                for (String role : user.getRoles()) {
                    userRoleMappings.add(UserRoleMapping.builder()
                            .userId(userCreated.getId())
                            .roleName(role)
                            .build());
                    //Map<String, Set<Actions>> permissionObject = rolePermissionMappingService.userClaimData(role);
                    //claimsData.put(role, permissionObject);
                    userRoleMappingRepository.saveAll(userRoleMappings);

                }
            }
            return ResponseDTO.builder()
                    .message(String.format(SuccessMessage.USER_INVITED_SUCCESS))
                    .isError(false)
                    .build();
        } catch (Exception e) {
            return ResponseDTO.builder()
                    .message(e.getMessage())
                    .isError(true)
                    .build();
        }
    }

    public String lockOrUnlock(String action, List<Long> ids) {
        try{
            List<User> users = userRepository.findByIdIn(ids);
            if(users.size() == 0){
                return "Invalid user";
            }
            for (User user:
                    users) {
                if(action.equalsIgnoreCase("lock")) {
                    user.setActive(false);
                    user.setStatus(Constants.USER_STATUS.Locked.toString());
                }
                else if(action.equalsIgnoreCase("unlock")){
                    user.setActive(true);
                    user.setStatus(Constants.USER_STATUS.Active.toString());
                }
                else
                    return "Invalid Action Type";
                userRepository.save(user);
            }
            return "Users updated successfully";

        }catch(Exception e){
            return e.getMessage();
        }

    }

    private void sendMailToOrganization(User user) {
        EmailTemplate emailTemplate = EmailTemplate.builder()
                .recipient(user.getEmail())
                .recipientName(user.getFirstName())
                .tenantName(user.getOrganizationName())
                .subject(Constants.WELCOME_TO_MSCIQ)
                .build();
        emailService.sendResetPasswordEmail(emailTemplate);
    }

    private void sendMailForPasswordReset(ResetPassword resetPassword) {
        EmailTemplate emailTemplate = EmailTemplate.builder()
                .recipient(resetPassword.getEmail())
                .subject(Constants.RESET_PASSWORD)
                .build();
        emailService.resetPasswordEmail(emailTemplate);
    }
}
