package com.msciq.storage.common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdtlabs.coreplatform.common.Constants;
import com.mdtlabs.coreplatform.common.contexts.UserContextHolder;
import com.mdtlabs.coreplatform.common.exception.ServicesException;
import com.mdtlabs.coreplatform.common.model.dto.UserDTO;
import com.mdtlabs.coreplatform.common.model.entity.Role;

/**
 * <p>
 * Common utils for date diff, data validation etc.
 * </p>
 * 
 * @author Vigneshkumar created on Jun 30, 2022
 *
 */
public class CommonUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(CommonUtil.class);

    /**
     * Used to check null condition
     *
     * @param pObj - object to be checked
     * @return boolean - true or false on null check
     */
    public static boolean isNull(Object... pObj) {
        Boolean isNull = false;
        if (pObj == null) {
            isNull = true;
        } else {
            for (Object lObj : pObj) {
                if (lObj == null) {
                    isNull = true;
                } else if (lObj instanceof String) {
                    isNull = ((String) lObj).trim().equals(Constants.EMPTY);
                } else if (lObj instanceof Collection) {
                    isNull = (((Collection) lObj).isEmpty());
                } else if (lObj instanceof Map) {
                    isNull = (((Map) lObj).size() == Constants.ZERO);
                } else {
                    isNull = false;
                }
                if (Boolean.TRUE.equals(isNull)) {
                    break;
                }
            }
        }
        return isNull;
    }

    /**
     * Used to check not null condition
     *
     * @param pObj - object to be checked
     * @return boolean - true or false on null check
     */
    public static boolean isNotNull(Object... pObj) {
        return !isNull(pObj);
    }

    /**
     * Used to get the string to integer value.
     *
     * @param value - string value
     * @return int - converted integer value
     */
    public static int toInteger(String value) {
        int integerVal = Constants.ZERO;
        if (isNumeric(value)) {
            integerVal = Integer.parseInt(value);
        }
        return integerVal;
    }

    /**
     * Used to get the string to long value.
     *
     * @param value - string value
     * @return long - converted long value
     */
    public static long toLong(String value) {
        long integerVal = Constants.ZERO;
        if (isNumeric(value)) {
            integerVal = Long.parseLong(value);
        }
        return integerVal;
    }

    /**
     * Used to check the string objects contains numeric value.
     *
     * @param values - string to be checked
     * @return boolean - true or false on numeric check
     */
    public static boolean isNumeric(String... values) {
        if (values == null) {
            return false;
        } else {
            for (String str : values) {
                if (!NumberUtils.isCreatable(str)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Used to get list of long values.
     *
     * @param values - input string value
     * @param delim  - delimiter
     * @return List<Long> - list of long values
     */
    public static List<Long> getListOfLongValues(String values, String delim) {
        List<Long> listOfValues = null;
        if (!isNull(values)) {
            listOfValues = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(values, delim);
            while (tokenizer.hasMoreTokens()) {
                String value = tokenizer.nextToken();
                if (isNumeric(value)) {
                    listOfValues.add(new Long(value));
                }
            }
        }
        return listOfValues;
    }

    /**
     * Used to get list of int values.
     *
     * @param values - input string value
     * @param delim  - delimiter
     * @return List<Integer> - list of integer values
     */
    public static List<Integer> getListOfIntValues(String values,
            String delim) {
        List<Integer> listOfValues = null;
        if (!isNull(values)) {
            listOfValues = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(values, delim);
            while (tokenizer.hasMoreTokens()) {
                String value = tokenizer.nextToken();
                value = value != null ? value.trim() : value;
                if (isNumeric(value)) {
                    listOfValues.add(new Integer(value));
                }
            }
        }
        return listOfValues;
    }

    /**
     * Used to get list of comma separeted int values.
     *
     * @param commaSepratedValues - input string
     * @return List<Integer> - list of integer values
     */
    public static List<Integer> getListOfIntValues(String commaSepratedValues) {
        return getListOfIntValues(commaSepratedValues, Constants.COMMA);
    }

    /**
     * Used to get list of comma separeted long values.
     *
     * @param commaSepratedValues - input string
     * @return List<Long> - list of long values
     */
    public static List<Long> getListOfLongValues(String commaSepratedValues) {
        return getListOfLongValues(commaSepratedValues, Constants.COMMA);
    }

    /**
     * Used to get list of comma separeted string values.
     *
     * @param commaSepratedValues - input string
     * @return List<String> - list of string values
     */
    public static List<String> getListOfStringValues(
            String commaSepratedValues) {
        List<String> listOfValues = null;
        if (!isNull(commaSepratedValues)) {
            listOfValues = Arrays
                    .asList(commaSepratedValues.split(Constants.SEPARATOR));
        }
        return listOfValues;
    }

    /**
     * This method is used to get stack trace
     * 
     * @param throwable - throwable object
     * @return String - stack trace
     */
    public static String getStackTrace(Throwable throwable) {
        return ExceptionUtils.getStackTrace(throwable);
    }

    /**
     * This method is used to get message
     * 
     * @param throwable - throwable object
     * @return String - message trace
     */
    public static String getMessage(Throwable throwable) {
        return ExceptionUtils.getStackTrace(throwable);
    }

    /**
     * This method is used to get rounded value
     * 
     * @param value     - input value
     * @param precision - number of digits to round off
     * @return double - rounded off value
     */
    public static double getRoundedValue(double value, int precision) {
        try {
            int scale = (int) Math.pow(10, precision);
            return (double) Math.round(value * scale) / scale;
        } catch (Exception ex) {
            return value;
        }
    }

    /**
     * This method is used to get rounded value
     * 
     * @param value - input value
     * @return double - rounded off value
     */
    public static double getRoundedValue(double value) {
        return getRoundedValue(value, Constants.ONE);
    }

    /**
     * Used to get current time stamp
     * 
     * @return String - timestamp
     */
    public static String getCurrentTimeStamp() {
        return new SimpleDateFormat(Constants.CURRENT_TIMESTAMP_DATE_FORMAT)
                .format(new Date());
    }

    /**
     * Used to convert time from string to double
     * 
     * @param time - input time string
     * @return Double - converted double value
     */
    public static Double convertTimeStringToDouble(String time) {
        return Double
                .parseDouble(time.replace(":30", ".5").replace(":00", ".0"));
    }

    /**
     * <p>
     * Get the logged in employee from the spring {@code SecurityContextHolder}
     * and construct the string with format '[employeId - employeeCompanyId]'.
     * </p>
     *
     * @return String - Logged in employee as string with format '[employeId -
     *         employeeCompanyId]'.
     */
    public static String getLoggedInEmployeeLog() {
        UserDTO user = getLoggedInUser();
        String userId = Constants.EMPTY;
        String username = Constants.EMPTY;
        if (null != user) {
            userId = String.valueOf(user.getId());
            username = user.getUsername();
        }
        return StringUtil.constructString(Constants.OPEN_BRACKET, userId,
                Constants.HYPHEN, username, Constants.CLOSE_BRACKET);
    }

    /**
     * <p>
     * Get the logged in employee from the spring
     * </p>
     *
     * @return UserDTO - Logged in employee object.
     */
    public static UserDTO getLoggedInUser() {
        return new UserDTO();
    }

    /**
     * <p>
     * This method returns the http entity for the current user This entity is
     * used across for calling rest services
     * </p>
     * 
     * @return HttpEntity<String> - current entity
     */
    public static HttpEntity<String> getCurrentEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION,
                StringUtil.concatString(Constants.BEARER,
                        UserContextHolder.getUserDto().getAuthorization()));
        return new HttpEntity<>(headers);
    }

    /**
     * This method is used to create pagination
     * 
     * @param count            - size of entity
     * @param gridDisplayValue - grid display value
     * @return int - pagination
     */
    public static int createPagination(int count, int gridDisplayValue) {
        int totalPageNumber = count / gridDisplayValue;
        int remainder = count % gridDisplayValue;
        return (remainder == Constants.ZERO)
                ? (totalPageNumber + Constants.ZERO)
                : (totalPageNumber + Constants.ONE);
    }

    /**
     * Validate the given string has only letters and numbers
     * 
     * @param values
     * @return Boolean
     */
    public static boolean validatePatientSearchData(List<String> values) {

        String regex = "^[0-9a-zA-Z@._-]+$";

        boolean isInvalidData = values.stream()
                .allMatch(value -> (!Objects.isNull(value) && !value.isEmpty()
                        && !Pattern.matches(regex, value)));

        return isInvalidData;
    }

    /**
     * Validate the email
     * 
     * @param email
     * @return Boolean
     */
    public static boolean validateEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validate the phone number for all country formats
     * 
     * @param phoneNo
     * @return Boolean
     */
    public static boolean validatePhoneNumber(String phoneNo) {
        String regex = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNo);
        return matcher.matches();
    }

    /**
     * To get the user auth token
     * 
     * @return String
     */
    public static String getAuthToken() {
        return Constants.BEARER
                + UserContextHolder.getUserDto().getAuthorization();
    }

}
