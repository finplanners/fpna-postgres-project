package com.msciq.storage.common;

import java.time.LocalDateTime;

public class Constants {

    public static final String FROM_EMAIL = "seenuvasan.muthaiyan@ideas2it.com";

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

    public static final String SIGN_UP = "User Onboarded Successfully";

    public static final String EMAIL_ALREADY_EXISTS = "Email Already Exists";

    public static final String RESET_PASSWORD = "Reset Password!!";

    public enum USER_STATUS  {Active,Pending,Deleted,Locked};
}
