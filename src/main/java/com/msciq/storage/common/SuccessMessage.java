package com.msciq.storage.common;

import java.util.List;

public class SuccessMessage<T> {
    private String message;
    private Object data;
    private boolean status;
    private List<T> entityList;
    private Integer responseCode;
    private Integer totalCount;

    /**
     * <h1>Success message.</h1>
     *
     * @param status       -status is passed in this attribute.
     * @param message      - Message to be displayed to the user is passed in this
     *                     attribute.
     * @param entity       - object is passed in this attribute.
     * @param entityList   - list is passed in this attribute.
     * @param responseCode - response code is passed in this attribute.
     * @param totalCount   - total count is passed in this attribute.
     */
    public SuccessMessage(boolean status, String message, Object entity, List<T> entityList, Integer responseCode,
                          Integer totalCount) {
        this.setMessage(message);
        this.setData(entity);
        this.setEntityList(entityList);
        this.setStatus(status);
        this.setResponseCode(responseCode);
        this.setTotalCount(totalCount);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public static final String RESET_PASSWORD_SUCCESS
            = "Your account password has been updated successfully";

    public static final String BUSINESS_DEACTIVATED = "Business Unit(s) Deactivated";

    public static final String BUSINESS_UNIT_DELETED = "Business Unit(s) Deleted";

    public static final String BUSINESS_ACTIVATED = "Business Unit(s) Activated";

    public static final String PASSWORD_RESET_NOTIFICATION = "Password reset notification sent to your email";

    public static final String USER_INVITED_SUCCESS
            = "User invited successfully";

    public static final String USERS_INVITED_SUCCESS
            = "User(s) invited successfully";
    public static final String LOGIN_SUCCESS = "Logged in successfully";

    public static final String SUCCESS = " Success";
    public static final String SUCCESSFULLY_SAVED = "%s saved successfully";
    public static final String USER_NOT_SAVED = "User is not saved";
    public static final String USERS_ACTIVE = "Users activated successfully";
    public static final String USERS_LOCKED = "Users locked successfully";

    public static final String USERS_UPDATE_SUCCESS
            = "Users updated successfully";
    public static final String USERS_DELETE_SUCCESS
            = "Users deleted successfully";
}
