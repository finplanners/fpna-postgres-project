package com.msciq.storage.common;

import java.time.LocalDateTime;

public class Constants {

    public static final String FROM_EMAIL = "seenuvasan.muthaiyan@ideas2it.com";

    public static final String USER = "user";

    public static final String FIREBASE_BASE_URL = "https://identitytoolkit.googleapis.com/v1/accounts:";

    public static final String FIREBASE_API_KEY="AIzaSyAcaGszpo7Ia0tC9RXVysyh8lf6EazEaaE";

    // Bucket name prefixes
    public static final String ORGANIZATION_PREFIX = "msciq_";
    public static final String GROUP_COMPANY_PREFIX = "goc_";
    public static final String COMPANY_PREFIX = "comp_";

    public static final int WELCOME_EMAIL_TEMPLATE=4430212;

    public static final String UI_BASE_URL="http://localhost:3000";

    public static final int THREE=3;
    public static final int FIFTEEN=15;

    public static final int TEN=10;
    public static final int HUNDRED=100;

    public static final String SIGN_UP_USER_DEFAULT_TYPE="Default_Global_Admin";

    public static final String DEPARTMENT_NAME = "Department Name";

    public static final String COMPANY_NAME = "Company Name";

    public static final String GROUP_COMPANY_NAME = "Group Company Name";

    public static final String GROUP_COMPANY_CODE = "Group Company Code";

    public static final String BUSINESS_UNIT_NAME = "Business Unit Name";

    public static final String LOCATION_NAME = "Location Name";

    public static final String CURRENCY = "Currency";

    public static final String FISCAL_YEAR = "Fiscal Year";

    public static final String DEPARTMENT_CODE = "Department Code";

    public static final String COMPANY_CODE = "Company Code";

    public static final String BUSINESS_UNIT_CODE = "Business Unit Code";

    public static final String LOCATION_CODE = "Location Code";

    public static final LocalDateTime DEFAULT_ACTIVE_FROM_DATE = LocalDateTime.of(2000, 01, 01, 00, 00, 00);

    public static final String WELCOME_TO_MSCIQ = "Welcome to MSCIQ!!";

    public static final String MESSAGE = "message";

    public static final String STATUS = "status";

    public static final String TIMESTAMP = "timestamp";

    public static final String TEMPLATE = "Template";

    public static final String GLACCOUNT = "General Ledger Accounts";
    public static final String ROLE_PERMISSION = "Role Permission";

    public static final String ROLE = "Role";

    public static final String PERMISSION = "Permission";

    public static final String USER_ROLE = "User Role";

    public static final String FORMAT_DDMMYYYY = "dd/MM/yyyy HH:mm";
    public static final String DATE_TIME_FORMATTER = "d/M/yyyy";

    public static final int ZERO = 0;

    public static final String JSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String SUCCESS = "SUCCESS";

    public static final String NO_DATA_FOUND = "No Data Found";

    public static final String ERROR = "ERROR";

    public static final int ONE = 1;

    public static final long SIXTY = 60;

    public static final long TWENTY_FOUR = 24;

    public static final String HOUR_SEPERATOR = ":";

    public static final String DOT = ".";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String TIMEZONE_UTC = "UTC";

    public static final String SIGN_UP = "Your account has been activated successfully";

    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";

    public static final String RESET_PASSWORD = "Reset Password!!";
    public static final String BUDGET_CATEGORY = "Budget category";
    public static final String TEMPLATE_TYPE = "Template type";

    public enum USER_STATUS  {Active,Pending,Deleted,Locked};


    public static final String PACKAGE_CORE_PLATFORM = "com.mdtlabs.coreplatform";
    public static final String SPICE_LOGGER = "SpiceLogger";
    public static final int INACTIVE_SESSION_EXPIRE_HOURS = 5;

    // Common Symbols & Strings
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String ASTERISK_SYMBOL = "*";
    public static final String FORWARD_SLASH = "/";
    public static final String COMMA = ",";
    public static final String EQUALS = "=";
    public static final String AND = "and";
    public static final String GENDER_MALE = "Male";
    public static final String GENDER_FEMALE = "Female";

    // Date Formate
    public static final String CURRENT_TIMESTAMP_DATE_FORMAT = "yyyy.MM.dd.HH.mm.ss";
    public static final String DATE = "DATE";
    public static String DATE_FORMAT_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss[XXX]";
    public static String DATE_FORMAT = "dd MMM, yyyy";

    public static final String USER_ENTITY = "User";
    public static final String ROLE_ENTITY = "Role";
    public static final String FORGOT_NOTIFICATION_SUBJECT = "Spice Forgot Password";
    public static final String RESET_NOTIFICATION_SUBJECT = "Spice Password Reset";
    public static final String CREATION_NOTIFICATION_SUBJECT = "Spice User Creation";
    public static final String FORGOT_PASSWORD = "Forgot Password";
    public static final String USER_CREATION = "User Creation";
    public static final String APP_URL_EMAIL = "app_url_email";
    public static final String NEW_USER_CREATION = "User_Creation";
    public static final String FORGOT_PASSWORD_USER = "Forgot_Password";
    public static final String NOTIFICAION_STATUS_PROCESSED = "Notification status processed";
    public static final String NOTIFICAION_STATUS_FAILED = "Notification status failed";
    public static final String SAVING_EMAIL_NOTIFICATION_ERROR = "Error while saving notification for email";
    public static final String HEADER_CLIENT = "client";

    // AESKEY
    public static final String AES_KEY = "Telec0unseL0r";
    public static final String AES_KEY_TOKEN = "te!e(0unsElor@321";

    // Application
    public static final String ISSUER = "SpiceApplication";
    public static final String ROLE_ID_PARAM = "roleId";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String ERROR_USER_BLOCKED = "User is blocked.";
    public static final String TOKEN_ISSUER = "User";
    public static final String AUTH_TOKEN_SUBJECT = "Auth_Token";
    public static final String REFRESH_TOKEN_SUBJECT = "Auth_Token";
    public static final Object WEB = "Web";
    public static final long EXPIRY_HOURS = 12;
    public static final long THOUSAND = 1000;
    public static final String SALT_KEY = "spice_uat";
    public static final String HASHING_CODE = "SHA-512";
    public static final String GET_CLASS = "getClass";
    public static final int NUMBER_THREE = 3;
    public static final String STRING_ZERO = "0";
    public static final String STRING_TWO = "2";
    public static final String STRING_THREE = "3";
    public static final String STRING_FOUR = "4";
    public static final String STRING_FIVE = "5";
    public static final String LOG_PREFIX_REQUEST = "|>";
    public static final String LOG_PREFIX_RESPONSE = "|<";
    public static final String SPLIT_CONTENT = "\r\n|\r|\n";
    public static final String BEARER = "Bearer ";
    public static final long AUTH_TOKEN_EXPIRY_MINUTES = 30;
    public static final long REFRESH_TOKEN_EXPIRY_HOURS = 8;
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    public static final String INFO_USER_EXIST = "Login employee isEnabled : ";
    public static final String CONTENT_TEXT_TYPE = "text/x-json;charset=UTF-8";
    public static final String CACHE_HEADER_NAME = "Cache-Control";
    public static final String CACHE_HEADER_VALUE = "no-cache";
    public static final String RSA = "RSA";
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    public static final String AUTHORIZATION = "Authorization";
    public static final String USER_ID_PARAM = "userId";
    public static final String USER_DATA = "userData";
    public static final String APPLICATION_TYPE = "applicationType";
    public static final String ANONYMOUS_USER = "anonymousUser";
    public static final String HIBERNATE_EJB_INTERCEPTOR = "hibernate.ejb.interceptor";
    public static final String HIBERNATE_SESSION_FACTORY = "hibernate.session_factory.interceptor";
    public static final String AUDIT = "Audit";
    public static final String GET = "get";
    public static final String MASTER_DATA = "master_data";
    public static final String APPLICATION_JSON = "application/json";
    public static final String DOCUMENTATION_SWAGGER = "documentation.swagger";
    public static final String DOCUMENTATION_SWAGGER_SERVICES = "documentation.swagger.services";
    public static final String TOKEN = "token";
    public static final String EMAIL = "email";
    public static final String ROLE_IDS = "roleIds";
    public static final String JWE_TOKEN = "jweToken";
    public static final String JWE_REFRESH_TOKEN = "jweRefreshToken";
    public static final String CLIENT = "client";
    public static final String NOTIFICATION_INSTANCE = "NOTIFICATION-SERVICE";
    public static final String LOGGER = "Logger";
    public static final int TWO = 2;
    public static final int INT_THREE = 3;
    public static final int FOUR = 4;
    public static final Boolean BOOLEAN_TRUE = Boolean.TRUE;
    public static final Boolean BOOLEAN_FALSE = Boolean.FALSE;
    public static final String TEXT = "text/*";
    public static final String APPLICATION_XML = "application/*+xml";
    public static final String APPLICATIONJSON = "application/*+json";
    public static final String EXP = "exp";
    public static final String AUTHORITY = "authority";
    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/YYYY";
    public static final String SEPARATOR = "\\s*,\\s*";
    public static final int WEB_NOTIFICATION_LIMIT = 5;
    public static final String NOTIFICATION_ID = "notificationId";
    public static final String TRUE = "true";
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String MAIL_SMTP_HOST = "mail.smtp.host";
    public static final String MAIL_SMTP_PORT = "mail.smtp.port";
    public static final String MAIL_SMTP_SSL_TRUST = "mail.smtp.ssl.trust";
    public static final String MAIL_SMTP_SSL_PROTOCALS = "mail.smtp.ssl.protocols";
    public static final String SMTP_GMAIL_COM = "smtp.gmail.com";
    public static final String SMTP_PORT = "587";
    public static final String SMTP_SSL_PROTOCAL = "TLSv1.2";
    public static final String TEXT_HTML_CHARSET = "text/HTML; charset=UTF-8";
    public static final String FORMAT = "format";
    public static final String FLOWED = "flowed";
    public static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
    public static final String ENCODING = "8bit";
    public static final String UTF_8 = "UTF-8";
    public static final String NOTIFICATION = "NOTIFICATION";
    public static final String AUTH_API = "Auth API";
    public static final String VERSION = "1.0";
    public static final String DOCUMENTATION_AUTH_API = "Documentation Auth API v1.0";
    public static final String DOCUMENTATION_USER_API = "Documentation User API v1.0";
    public static final String USER_API = "User API";
    public static final String API_ROLES_MAP_CLEARED = "api permission map cleared";
    public static final String CREATE = "create";
    public static final String UPDATE = "update";
    public static final String UPDATED_AT = "updatedAt";

    // Tenant
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_SCHEMA = "Bearer ";
    public static final String TENANT_IDS_CLAIM = "tenantId";
    public static final String ROLE_ID_CLAIM = "roleId";
    public static final String ROLE_NAME_CLAIM = "roleName";
    public static final String HEADER_TENANT_ID = "TenantId";

    public static final String TENANT_FILTER_NAME = "tenantFilter";
    public static final String TENANT_PARAMETER_NAME = "tenantId";
    public static final String TENANT_PARAMETER_TYPE = "int";
    public static final String TENANT_COLUMN_NAME = "tenant_id";

    public static final int SUPER_ADMIN_ROLE_ID = 0;
    public static final String SUPER_ADMIN_ROLE_NAME = "ROLE_SUPER_ADMIN";
    public static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";
    public static final String USER_ROLE_NAME = "ROLE_USER";

    public static final int SUPER_TENANT_ADMINISTRATOR_ID = 0;
    public static final String USER_SELECTED_TENANT = "userTenant";

    // Spring Components and warning
    public static final String SERIAL = "serial";
    public static final String ERROR_MESSAGE_PROPERTY = "/error_messages.properties";
    public static final String RAWTYPES = "rawtypes";
    public static final String UNCHECKED = "unchecked";
    public static final String UNUSED = "unused";
    public static final String PASSWORDENCODER = "passwordEncoder";
    public static final String TEMPLATE_MESSAGE_PROPERTY = "classpath:template_message.properties";

    // Date Formate
    public static final String TEN_DIGITS_DECIMAL_FORMAT = "#.##########";
    public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd";
    public static final String WEEK = "WEEK";
    public static final String DATE_COLUMN = "date";
    public static final String DAY = "DAY";
    public static final String MONTH = "MONTH";
    public static final String YEARS = "YEARS";
    public static final String END = "End";
    public static final String BEGIN = "Begin";
    public static final String MMM_YY_FORMAT = "MMM-yy";
    public static final String DD_MM_YYYY = "dd-mm-yyyy";
    public static final String ACTIVE = "active";

    public static final String NONE = "NONE";
    public static final String ENROLLED = "ENROLLED";
    public static final String SCREENED = "SCREENED";
    public static final String OBSERVATION = "OBSERVATION";

    public static final String HIGH = "High";
    public static final String LOW = "Low";
    public static final String MODERATE = "Moderate";
    public static final String HIGHER_MODERATE = "Higher Moderate";
    public static final String BOTH_MODERATE = "Both Moderate";
    public static final String BOTH_HIGHER_MODERATE = "Both Higher Moderate";
    public static final String GLUCOSE_MODERATE = "Glucose Moderate";

    public static final String SCREENING = "screening";
    public static final String ASSESSMENT = "assessment";
    public static final String ENROLLMENT = "enrollment";
    public static final String MEDICAL_REVIEW = "medicalreview";

    public static final String SYSTOLIC = "systolic";
    public static final String DIASTOLIC = "diastolic";

    public static final String RBS = "rbs";
    public static final String FBS = "fbs";

    public static final String DBM_GLUCOSE_MODERATE = "Take your prescribed medication and come back in 3 days for a BP recheck. Your doctor may ask you to come for medical review";
    public static final String DBM_BOTH_MODERATE = "Take your prescribed medication and come back in 3 days for a BP recheck. Your doctor may ask you to come for medical review.";
    public static final String DBM_HIGHER_MODERATE = "Take your prescribed medication and come back in 3 days for a BP recheck. Your doctor may ask you to come for medical review";
    public static final String DBM_MODERATE = "Take your prescribed medication and come back in 3 days for a BP recheck. Your doctor may ask you to come for medical review";
    public static final String DBM_HIGH = "Go to the hospital or clinic for further evaluation";
    public static final String DBM_LOW = "Great job! Continue to check blood pressure and take prescribed medications";

    public static final int BP_THRESHOLD_SYSTOLIC = 140;
    public static final int BP_THRESHOLD_DIASTOLIC = 90;
    public static final String BG_PROVISIONAL_FREQUENCY_NAME = "Physician Approval Pending";
    public static final String FREQUENCY_BP_CHECK = "BP Check";
    public static final String FREQUENCY_BG_CHECK = "BG Check";
    public static final String FREQUENCY_MEDICAL_REVIEW = "Medical Review";
    public static final String FREQUENCY_HBA1C_CHECK = "Hba1c Check";
    public static final String MEDICAL_REVIEW_FREQUENCY = "Medical Review Frequency";
    public static final String BP_CHECK_FREQUENCY = "Blood Pressure Check Frequency";
    public static final String BG_CHECK_FREQUENCY = "Blood Glucose Check Frequency";
    public static final String HBA1C_CHECK_FREQUENCY = "Hba1c Check Frequency";

    public static final String LABEL = "label";
    public static final String VALUE = "value";
    public static final String FREQUENCY_KEY = "frequencyKey";
    public static final String DIABETES_UNCONTROLLED_OR_POORLY_CONTROLLED = "Diabetes: Uncontrolled/Poorly controlled";
    public static final String DIABETES_WELL_CONTROLLED = "Diabetes: Well-controlled";
    public static final String PRE_DIABETES = "Pre-Diabetic";

    public static final String KNOWN_DIABETES_PATIENT = "known";
    public static final String GLUCOSE_UNIT_MG_DL = "mg/dL";
    public static final String GLUCOSE_UNIT_MMOL_L = "mmol/L";
    public static final String OTHER = "Other";
    public static final String UNIT = "unit";

    public static final String NEW = "NEW";
    public static final String MEDICAL_REVIEW_COMPLETED = "MEDICAL_REVIEW_COMPLETED";

    public static final String HYPERTENSION = "Hypertension";
    public static final String DIABETES = "Diabetes";
    public static final String DEFAULT = "default";

    public static final String IS_PRESCRIPTION = "is_prescription";
    public static final String IS_INVESTIGATION = "is_investigation";
    public static final String IS_MEDICAL_REVIEW = "is_medical_review";

    public static final String MG_DL = "mg/dL";
    public static final String MMOL_L = "mmol/L";
    public static final String WORKFLOW_SCREENING = "Screening";
    public static final String WORKFLOW_ENROLLMENT = "Enrollment";
    public static final String WORKFLOW_ASSESSMENT = "Assessment";
    public static String SIGNATURE_DATE_FORMAT = "yyyyMMddHHmmssSSS";

    public static final String PHQ4 = "PHQ4";
    public static final String PHQ9 = "PHQ9";
    public static final String GAD7 = "GAD7";
    public static final Integer FBS_MMOL_L = 7;
    public static final Integer RBS_MMOL_L = 11;
    public static final Integer FBS_MG_DL = 126;
    public static final Integer RBS_MG_DL = 198;
    public static final String MODULE = "Module";
    public static final String INPUT_FORM = "Input_form";
    public static final String CONSENT_FORM = "Consent_form";

    public static final String TABLE_USER_TOKEN = "user_token";
    public static final String LAST_SESSION_TIME = "last_session_time";
    public static final String TYPE = "type";
    public static final String FORMS = "forms";
    public static final String OPTIONS = "options";
    public static final String QUESTIONS = "questions";
    public static final String TEMPLATE_TYPE_ENROLL_PATIENT = "ENROLL_PATIENT";
    public static final Object ENTITY = "entity";
    public static final String TEMPLATE_TYPE_RED_RISK = "RED_RISK";

    // Lifestyle type
    public static final String SMOKE = "smoke";
    public static final String ALCOHOL = "alcohol";
    public static final String NUT = "nut";
    public static final String HOURS_PER_WEEK = "hrs/week";

    public static final String ACCOUNT = "account";
    public static final String REGION = "Country";
    public static final String OPERATING_UNIT = "operatingunit";
    public static final String COUNTRY = "country";
    public static final String SESSSION_ALIVE = "Session alive";
    public static final String COUNT = "count";
    public static final String DATA = "data";
    public static final String REFRESH_TOKEN = "RefreshToken";
    public static final String EMAIL_FORM_USER = "User";
    public static final String CREATED_AT = "createdAt";
    public static final String SEARCH_TERM = "[^a-zA-Z0-9]*";

    public static final String DIABETES_MELLITUS_TYPE_1 = "Diabetes Mellitus Type 1";
    public static final String DIABETES_MELLITUS_TYPE_2 = "Diabetes Mellitus Type 2";

    public static final String HYPER_TENSION = "Hypertenstion";
    public static final String PRE_HYPER_TENSION = "Pre-Hypertension";

    public static final String CLIENT_SPICE_MOBILE = "spice mobile";
    public static final String CLIENT_SPICE_WEB = "spice web";
    public static final String ROLE_HEALTH_COACH = "HEALTH_COACH";
    public static final String ROLE_HEALTH_SCREENER = "HEALTH_SCREENER";
    public static final String ROLE_HRIO = "HRIO";
    public static final String ROLE_LAB_TECHNICIAN = "LAB_TECHNICIAN";
    public static final String ROLE_NURSE = "NURSE";
    public static final String ROLE_NUTRITIONIST = "NUTRITIONIST";
    public static final String ROLE_PHARMACIST = "PHARMACIST";
    public static final String ROLE_PHYSICIAN_PRESCRIBER = "PHYSICIAN_PRESCRIBER";
    public static final String ROLE_PROVIDER = "PROVIDER";
    public static final String ROLE_RED_RISK_USER = "ROLE_RED_RISK";
    public static final String ROLE_ACCOUNT_ADMIN = "ACCOUNT_ADMIN";
    public static final String ROLE_OPERATING_UNIT_ADMIN = "OPERATING_UNIT_ADMIN";
    public static final String ROLE_REGION_ADMIN = "REGION_ADMIN";
    public static final String ROLE_REPORT_ADMIN = "REPORT_ADMIN";
    public static final String ROLE_SUPER_ADMIN = "SUPER_ADMIN";
    public static final String ROLE_SYSTEM_ADMIN = "SYSTEM_ADMIN";
    public static final String ROLE_SUPER_USER = "SUPER_USER";

    public static final String PATIENT_LIST = "patientList";
    public static final String TOTAL_COUNT = "totalCount";

    public static final String INPUT_ID = "in_id";
    public static final String INPUT_TENANT_ID = "in_tenant_id";
    public static final String UPDATE_VIRTUAL_ID = "update_virtual_id";


    public static final String FORMAT_MMDDYYHHMM = "MM/dd/yy HH:mm";

    public static final String DEFAULT_MASK = "MM/dd/yy";

    public static final String FORMAT_MMDDYY = "MM/dd/yy";

    public static final String FORMAT_MMMyyyy = "MMM yyyy";

    public static final String FORMAT_MMyy = "MM/yy";

    public static final String FORMAT_MMyyyy = "MM/yyyy";

    public static final String FORMAT_ddMMMyyyy = "dd-MMM-yyyy";

    public static final String FORMAT_ddMMMyyyyHHMM = "dd-MMM-yyyy HH:mm:ss";

    public static final String FORMAT_DDMMMYYYY = "dd MMM yyyy";

    public static final String FORMAT_MMDDYYHHMMSS = "MM/dd/yy HH:mm:ss";

    public static final String FORMAT_DDMMYYHHMMSS = "dd/MM/yy HH:mm:ss";

    public static final String FORMAT_DDMMYYYYHHMMSS = "dd/MM/yyyy HH:mm:ss";

    public static final String HOUR = "HOURS";

    public static final String FORMAT_yyyyMMdd = "yyyy-MM-dd";

    public static final String FORMAT_ddMMYYYY = "dd-MM-yyyy";

    public static final String FORMAT_ddMMyyyyhhmma = "dd-MM-yyyy hh:mm a";

    public static final String DEFAULT_TIME_ZONE ="GMT";

    public static final String FORMAT_EEEMMMdHHmmssyyyy = "EEE MMM d HH:mm:ss yyyy";

    public static final String FORMAT_EEEdMMMyyyyHHmmssaZ = "EEE dd MMM yyyy hh:mm:ss a z";

    public static final String FORMAT_MMMMddyyyyHHmmssa = "MMMM dd,yyyy HH:mm a";

    public static final String FORMAT_MMMMddyyyyHHmmss = "MMMM dd,yyyy HH:mm";

    public static final String FORMAT_yyyyMMddHHmmssSSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";


    public static final String[] WEEK_DAYS = { "Sunday", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday" };

    private static final String FORMAT_YYYYMMDD = "yyyy/MM/dd";

    public static final String FORMAT_MMDDYYYYHHMM = "MM/dd/yyyy HH:mm";

    public static final String FORMAT_MMDDYYYYHHMMSS = "MM/dd/yyyy HH:mm:ss";

    public static final String FORMAT_yyyyMMddHHmmssSSS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String FORMAT_DDMMYYHHMM = "dd/MM/yy HH:mm";

    public static final String FORMAT_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_ddMMMyyyyHHmm = "dd MMM yyyy HH:mm:ss";

    public static final String PROFIT_CENTER = "Profit center";

    public static final String PRODUCT_GROUP = "Product group";

    public static final String PROJECT = "Project";

}
