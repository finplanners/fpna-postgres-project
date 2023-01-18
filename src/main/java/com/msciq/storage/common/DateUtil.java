package com.msciq.storage.common;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * This is used to calculate the past day from particular date
 *
 * @author AkashGopinath Created on 25 Aug 2022
 */
public class DateUtil {

	private DateUtil() {
	}

	/**
	 * this is used to calculate days since particular date
	 * 
	 * @param date - input date
	 * @return long - days since past
	 */
	public static long daysSincePast(Date date) {
		if (null != date) {
			LocalDate dateBefore = getZonedDateTime(date).toLocalDate();
			LocalDate dateAfter = getZonedDateTime(new Date()).toLocalDate();
			return ChronoUnit.DAYS.between(dateBefore, dateAfter);
		}
		return Constants.ZERO;
	}

	/**
	 * This method is used to calculate year since particular date in string
	 * 
	 * @param dateString - input date string
	 * @return int - years since past input date
	 */
	public static int yearsSincePast(String dateString) {
		if (StringUtils.isNotBlank(dateString)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER);
			LocalDate dateTimeDob = LocalDate.parse(dateString, formatter);
			return Period.between(dateTimeDob, LocalDate.now()).getYears();
		}
		return Constants.ZERO;
	}

	/**
	 * This method is used to calculate year since particular date
	 * 
	 * @param date -input date
	 * @return int - years since past
	 */
	public static int yearsSincePast(Date date) {
		if (null != date) {
			LocalDate dateBefore = getZonedDateTime(date).toLocalDate();
			LocalDate dateAfter = getZonedDateTime(new Date()).toLocalDate();
			return Period.between(dateBefore, dateAfter).getYears();
		}
		return Constants.ZERO;
	}

	/**
	 * This method is used to get zonedDateTime
	 * 
	 * @param date - input date
	 * @return ZonedDateTime - zoned date time response
	 */
	public static ZonedDateTime getZonedDateTime(Date date) {
		Instant timeStamp = date.toInstant();
		String zoneId = String.valueOf(ZoneId.of(FieldConstants.UTC));
		return timeStamp.atZone(ZoneId.of(zoneId));
	}

	/**
	 * This method is used to format date
	 * 
	 * @param dateStr   - input date
	 * @param formatStr - format type
	 * @return Date - converted date into specified format
	 */
	public static Date formatDate(String dateStr, String formatStr, Boolean isTimezonechange) {
		System.out.println("coming inside " + dateStr);
		DateFormat df = null;
		Date date = null;
		try {
			if (!CommonUtil.isNull(dateStr, formatStr)) {
				System.out.println("CommonUtil inside " + dateStr + "==" + formatStr);
				df = new SimpleDateFormat(formatStr);
				date = df.parse(dateStr);
				if (isTimezonechange) {
					date = new Date(date.getTime() - Calendar.getInstance().getTimeZone().getOffset(date.getTime()));
				}
			}
		} catch (ParseException ex) {
			System.out.println("Exception" + ex.getMessage());
			return null;
		}
		System.out.println("date" + date);
		return date;
	}
	
	
	
	
	/**
	 * This method is used to format date on string
	 * 
	 * @param dateStr - date to be formatted
	 * @return Date - formatted date
	 */
	public static Date formatDate(String dateStr) {
		return !CommonUtil.isNull(dateStr) ? formatDate(dateStr, Constants.JSON_DATE_FORMAT,true) : null;
	}

	/**
	 * This method is used to format date by given format
	 * 
	 * @param date   - date to be formatted
	 * @param format - format type
	 * @return Date - formatted date
	 */
	public static Date formatDate(Date date, String format,Boolean isTimeZoneChange) {
		if(!CommonUtil.isNull(date)) {
			System.out.println("date note null");
		}else {
			System.out.println("date is null");
		}
		return !CommonUtil.isNull(date) ? formatDate(StringUtil.getDateString(date,format), format,isTimeZoneChange) : null;
	}
	

	
	 
	/**
	 * Used to get the two dates difference days / month / year.
	 *
	 * @param fromDate - start date
	 * @param toDate   - end date
	 * @param type     - type of difference
	 * @return int - difference in date
	 */
	public static int getCalendarDiff(Date fromDate, Date toDate, int type) {
		Calendar fCal = Calendar.getInstance();
		Calendar tCal = Calendar.getInstance();
		fCal.setTime(fromDate);
		tCal.setTime(toDate);
		if (!CommonUtil.isNull(fromDate, toDate)) {
			return (tCal.get(type) - fCal.get(type));
		}
		return Constants.ZERO;
	}

	/**
	 * Used to get the two dates difference years.
	 *
	 * @param fromDate - start date
	 * @param toDate   - end date
	 * @return int - difference in date
	 */
	public static int getDiffYears(Date fromDate, Date toDate) {
		return getCalendarDiff(fromDate, toDate, Calendar.YEAR);
	}

	/**
	 * This method is used to subtract particular value in date
	 * 
	 * @param date           - from date
	 * @param daysToSubtract - count to be subtracted
	 * @return Date - subtracted date
	 */
	public static Date subtractDates(Date date, int daysToSubtract) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -daysToSubtract);
		return cal.getTime();
	}

	/**
	 * Used to get the date field.
	 *
	 * @param date - input date
	 * @param type - type of date field
	 * @return Integer - date field value
	 */
	public static Integer getDateField(Date date, int type) {
		Integer value = null;
		Calendar calendar = null;
		if (!CommonUtil.isNull(date, type)) {
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.get(type);
		}
		return value;
	}

	/**
	 * Used to get the date field in specific format.
	 *
	 * @param date       - input date
	 * @param dateFormat - date format
	 * @param type       - type of date field
	 * @return Integer - date field value
	 */
	public static Integer getDateField(String date, String dateFormat, int type) {
		return getDateField(formatDate(date, dateFormat,true), type);
	}

	/**
	 * Used to get the date field.
	 *
	 * @param date - input date
	 * @param type - type of date field
	 * @return Integer - date field value
	 */
	public static Integer getDateField(String date, int type) {
		return getDateField(formatDate(date), type);
	}
	
	/**
	 * Gets Day of week
	 * 
	 * @param dayOfWeek - input day of week
	 * @return Date - Date with day of week
	 */
	public static Date getDayOfWeek(int dayOfWeek) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		return c.getTime();
	}


	/**
	 * This method is used to get hours from string
	 * 
	 * @param loggedHours - input string
	 * @return float - hours value
	 */
	public static float getHoursFromString(String loggedHours) {
		loggedHours = loggedHours.replaceAll(Constants.HOUR_SEPERATOR, Constants.DOT);
		return Float.parseFloat(loggedHours);
	}

	/**
	 * This method is used to check the day is on future or not
	 * 
	 * @param loggedDate - input date
	 * @return boolean - true or false on value is future or not
	 */
	public static boolean isDayOnFuture(String loggedDate) {
		Date formattedDate = formatDate(loggedDate, Constants.DATE_FORMAT_YYYY_MM_DD,true);
		return new Date().before(formattedDate);
	}

	/**
	 * This method is used to get current day
	 * 
	 * @return date - current day
	 */
	public static Date getCurrentDay() {
		return new Date();
	}

	/**
	 * This method is used to get previous week start date
	 * 
	 * @return Date - start date of previous week
	 */
	public static Date getPreviousWeekStartDate() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -Constants.ONE);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 7);
		return c.getTime();
	}

	/**
	 * Calculates date for last weeks monday.
	 * 
	 * @return Date - first day of week
	 */
	public static Date getFirstDayOfWeek() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 7);
		return c.getTime();
	}
	
	/**
	 * Used to get the two dates difference in minutes.
	 *
	 * @param fromDate - start date
	 * @param toDate   - end date
	 * @return long - difference in minutes
	 */
	public static long getDiffInMinutes(Date fromDate, Date toDate) {
		long difference = fromDate.getTime() - toDate.getTime();
		return TimeUnit.MILLISECONDS.toMinutes(difference) % Constants.SIXTY;
	}

	/**
	 * Used to get the two dates difference in hours.
	 *
	 * @param fromDate - start date
	 * @param toDate   - end date
	 * @return long - difference in hours
	 */
	public static long getDiffInHours(Date fromDate, Date toDate) {
		long difference = fromDate.getTime() - toDate.getTime();
		return TimeUnit.MILLISECONDS.toHours(difference) % Constants.TWENTY_FOUR;
	}
	
	public static String getEndOfDay(String timeZone) {
		Calendar calendar = getCalendar(timeZone);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return getISOString(calendar);
	}

	public static Calendar getCalendar (String timeZone) {
        ZoneOffset zoneOffSet= ZoneOffset.of(timeZone);
        OffsetDateTime currentOffSetDateTime = OffsetDateTime.now(zoneOffSet);
        
        Date currentDate = Date.from(currentOffSetDateTime.toInstant());
        Calendar calendar = Calendar.getInstance();	
        calendar.setTime(currentDate);
        calendar.set(Calendar.ZONE_OFFSET, currentOffSetDateTime.getOffset().getTotalSeconds() * 1000);
        return calendar;
	}

	public static String getStartOfDay(String timeZone) {
        Calendar calendar = getCalendar(timeZone);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return getISOString(calendar);
	}

	public static String getISOString(Calendar calendar) {
		DateFormat df = new SimpleDateFormat(Constants.JSON_DATE_FORMAT); // Quoted "Z" to indicate UTC, no timezone
																			// offset
		df.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_UTC));
		String nowAsISO = df.format(calendar.getTime());
		System.out.println(nowAsISO);
		return nowAsISO;
	}

	/**
	 * Used to add the date.
	 * 
	 * @param date
	 * @param daysToAdd
	 * @return Date
	 */
	public static Date addDate(Date date, int daysToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, daysToAdd);
		return cal.getTime();
	}

	public static long getDateDiffInMinutes(Date dateFrom, Date dateTo) {

		if (null == dateFrom || null == dateTo) {
			return 0;
		}

		long differenceInTime = dateTo.getTime() - dateFrom.getTime();

		long differenceInYears = TimeUnit.MILLISECONDS.toDays(differenceInTime) / 365;
		long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInTime) % 365;
		long differenceInHours = TimeUnit.MILLISECONDS.toHours(differenceInTime) % 24;
		long differenceInMinutes = TimeUnit.MILLISECONDS.toMinutes(differenceInTime) % 60;

		return ((differenceInYears * 365 * 24 * 60) + (differenceInDays * 24 * 60) + (differenceInHours * 60)
				+ differenceInMinutes);
	}

}
