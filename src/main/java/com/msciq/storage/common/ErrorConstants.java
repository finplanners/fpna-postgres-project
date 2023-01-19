package com.msciq.storage.common;

/**
 * <p>
 * To define the common static error parameter used all over the application.
 * </p>
 *
 * @author Niraimathi S created on Nov 16, 2022
 */
public final class ErrorConstants {
    public ErrorConstants() {
    }

    public static final String COUNTRY_ID_NOT_NULL = "Country id must not be null";
    public static final String SITE_ID_NOT_NULL = "Site Id should not be null";
    public static final String TENANT_ID_NOT_NULL = "Tenant Id should not be null";
    public static final String PROGRAM_NAME_NOT_EMPTY = "Program name must not be empty";
    public static final String ACCOUNT_WORKFLOW_NAME_NOT_EMPTY = "Account workflow name should not be empty";
    public static final String ACCOUNT_WORKFLOW_VIEWSCREEN_NOT_NULL = "View screens should not be empty or null";
    public static final String TYPE_NOT_NULL = "Type should not be empty";
    public static final String FORM_INPUT_NOT_NULL = "Form input should not be empty ";
    public static final String CATEGORY_NOT_NULL = "Category should not be empty";
    public static final String ACCOUNT_NAME_NOT_NULL = "Account name should not be empty";
    public static final String CLINICAL_WORKFLOW_NOT_NULL = "Clinical workflows should not be null ";
    public static final String CLINICAL_WORKFLOW_SIZE = "Clinical workflows should contains minimum one value";

    public static final String AVG_SYSTOLIC_NOT_NULL = "Average systolic should not be empty";
    public static final String AVG_DIASTOLIC_NOT_NULL = "Average diastolic should not be empty";
    public static final String IS_REGULAR_SMOKER = "IsRegularSmoker should not be empty";
    public static final String SYMPTOM_NAME_NOT_NULL = "Symptom name should not be empty";
    public static final String SYMPTOM_ID_NOT_NULL = "Symptom id should not be null";
    public static final String COMPLIANCE_NAME_NOT_NULL = "Compliance name should not be empty";
    public static final String COMPLIANCE_ID_NOT_NULL = "Compliance Id should not be empty";
    public static final String BPLOG_DETAILS_NOT_EMPTY = "BPLog details should not be empty";
    public static final String BPLOG_EDTAILS_MIN_SIZE = "BpLog details should contains minimum 2 values";
    public static final String FIRST_NAME_NOT_NULL = "Firstname should not be empty";
    public static final String LAST_NAME_NOT_NULL = "Lastname should not be empty";
    public static final String INITIAL_NOT_NULL = "Initial should not be empty";
    public static final String PHONE_NUMBER_NOT_NULL = "Phone number should not be empty";
    public static final String PHONE_NUMBER_CATEGORY_NOT_NULL = "Phone number category should not be empty";
    public static final String COUNTRY_NOT_NULL = "Country should not be empty";
    public static final String COUNTY_NOT_NULL = "County should not be empty";
    public static final String SUB_COUNTY_NOT_NULL = "Sub county should not be empty";
    public static final String AGE_NOT_NULL = "Age should not be null";
    public static final String AGE_MIN_VALUE = "Age should be greater than 0";
    public static final String GENDER_NOT_NULL = "Gender should not be null";
    public static final String IS_PROVISIONAL_NOT_NULL = "IsProvisional should not be null";
    public static final String PATIENT_TRACK_ID_NOT_NULL = "PatientTrackId should not be null";
    public static final String PATIENT_VISIT_ID_NOT_NULL = "PatientVisitId should not be null";
    public static final String IS_REVIEWED_NOT_NULL = "IsReviewed should not be null";
    public static final String LABTEST_ID_NOT_NULL = "LabtestId should not be null";
    public static final String PATIENT_LABTEST_RESULT_NAME_NOT_NULL = "PatientLabtest result name should not be empty";
    public static final String RESULT_VALUE_NOT_NULL = "Result value should not be null";
    public static final String UNIT_NOT_NULL = "Unit should not be empty";
    public static final String DISPLAY_ORDER_NOT_NULL = "Display order should be empty";
    public static final String DISPLAY_ORDER_MIN_VALUE = "Display order should be greater then 0";
    public static final String NATIONAL_ID_NOT_NULL = "National Id should not be empty";
    public static final String LONGITUDE_NOT_NULL = "Longitude should not be empty";
    public static final String LATITUDE_NOT_NULL = "Latitude should not be empty";
    public static final String IS_REFER_ASSESSMENT_NOT_NULL = "IsReferAssessment should not be null";
    public static final String MEDICATION_ID_NOT_NULL = "Medication Id should not be null";
    public static final String DOSAGE_FREQUENCY_NOT_NULL = "Dosage frequency should not be null";
    public static final String DOSAGE_UNIT_NOT_NULL = "Dosage unit should not be null";
    public static final String PRESCRIBED_DAYS = "Prescribed days should not be empty";
    public static final String MEDICATION_NAME_NOT_NULL = "Medication name should not be empty";
    public static final String DOSAGE_UNIT_VALUE_NOT_NULL = "Dosage unit value should not be empty";
    public static final String DOSAGE_UNIT_NAME_NOT_NULL = "Dosage unit name should not be empty";
    public static final String DOSAGE_FREQUENCY_NAME_NOT_NULL = "Dosage frequency name should not be empty";
    public static final String DOSAGE_FORM_NAME_NOT_NULL = "Dosage form name should not be empty";
    public static final String COUNTRY_NAME_NOT_NULL = "Country name should not be empty";
    public static final String COUNTRYCODE_NOT_NULL = "Country code should not be empty";
    public static final String UNIT_MEASUREMENT_NOT_NULL = "Unit measurement should not be empty";
    public static final String IS_ACTIVE_NOT_NULL = "IsActive should not be null";
    public static final String SUB_COUNTY_NAME_NOT_NULL = "Sub county name should not be null";
    public static final String CLASSIFICATION_NAME_NOT_NULL = "Classification name should not be empty";
    public static final String BRAND_NAME_NOT_NULL = "Classification name should not be empty";
    public static final String CLASSIFICATION_ID_NOT_NULL = "Classification Id should not be null";
    public static final String BRAND_ID_NOT_NULL = "Brand Id should not be null";
    public static final String DOSAGE_FORM_ID_NOT_NULL = "Dosage form Id should not be null";
    public static final String NOTIFICAION_STATUS_FAILED = "Notification status failed";
	public static final String SAME_PASSWORD = "New password cannot be same as old password";
	public static final String PASSWORD_ERROR = "Password cannot be same as user name";
	public static final String ERROR_USERNAME_PASSWORD_BLANK = "No Username and / or Password Provided";
	public static final String ERROR_INVALID_USER = "Invalid credentials";
	public static final String ERROR_ACCOUNT_DISABLED = "Disabled Account";
	public static final String ERROR_INVALID_ATTEMPTS = "Account locked due to multiple invalid login attempts.";
	public static final String INFO_USER_NOT_EXIST = "Username does not exist : ";
	public static final String INFO_USER_PASSWORD_NOT_MATCH = "Password doesn't match for the user : ";
	public static final String EXCEPTION_TOKEN_UTILS = "Exception occured while loading token utills";
	public static final String INVALID_USER_ERROR = "{ \"error\": \"Invalid User\"}";
	public static final String LOGIN_ERROR = "Login Error ";
	public static final String ERROR_JWE_TOKEN = "Error while creating jwe token ";
	public static final String TOKEN_EXPIRED = "Token expired";
	public static final String JOSE_EXCEPTION = "JOSE Excpetion ococcured while loading token utills";
	public static final String EXCEPTION_DURING_TOKEN_UTIL = "Exception occured while loading token utills";
	public static final String RESOLVER_ERROR = "Error message construction using resolver Error Message";
	public static final String LINK_EXPIRED = "Link has expired.";
	public static final String SAVING_EMAIL_NOTIFICATION_ERROR = "Error while saving notification for email";
	public static final String EMAIL_NOT_NULL = "Username/email Id should not be empty";
	public static final String ERROR_UPDATE_PASWORD = "Error while update user password : ";
	public static final String ERROR_USER_DOESNT_ROLE = "You don't have a permission. Please contact administrator";
	
		//public static final String COUNTRY_NOT_NULL = "Country should not be empty";
	 public static final String CURRENCY_NOT_NULL = "Currency should not be empty";
	 public static final String GC_CODE_NOT_NULL = "Group company code should not be empty";
	 public static final String GC_NAME_NOT_NULL = "Group company name should not be empty";
	 public static final String FISCAL_YEAR_NOT_NULL = "Fiscal year should not be empty";
	 
	 public static final String COMPNAY_CODE_NOT_NULL = "Company code should not be empty";
	 public static final String COMPNAY_NAME_NOT_NULL = "Company name should not be empty";
	 
	 public static final String BU_CODE_NOT_NULL = "BU code should not be empty";
	 public static final String BU_NAME_NOT_NULL = "BU name should not be empty";
	 
	 public static final String DEPARTMENT_CODE_NOT_NULL = "Department code should not be empty";
	 public static final String DEPARTMENT_NAME_NOT_NULL = "Department name should not be empty";
	 
	 public static final String ACTIVATION_DATE_FORMAT = "Please provide a activation date in the format of dd/MM/yyyy.";
	 public static final String END_DATE_FORMAT = "Please provide a end date in the format of dd/MM/yyyy.";
	 
	 public static final String FIS_CAL_KEY_NULL = "Fiscal calendar key should not be empty";
	 public static final String FIS_CAL_DESCRIPTION_NULL = "Fiscal calendar description should not be empty";
}
