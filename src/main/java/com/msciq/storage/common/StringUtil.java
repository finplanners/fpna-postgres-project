package com.msciq.storage.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>
 * String utils for string validation etc.
 * </p>
 * 
 * @author Rajkumar created on Nov 02, 2022
 *
 */
public class StringUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	/**
	 * This method is used to get date string
	 * 
	 * @param date      - input date
	 * @param formatStr - format type of string
	 * @return String - date string
	 */
	public static String getDateString(Date date, String formatStr) {
		String dateStr = null;
		DateFormat df = null;
		try {
			if (!CommonUtil.isNull(date, formatStr)) {
				df = new SimpleDateFormat(formatStr);
				dateStr = df.format(date);
			}
		} catch (Exception ex) {
			return null;
		}
		return dateStr;
	}
}
