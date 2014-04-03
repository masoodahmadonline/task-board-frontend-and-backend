package web.service.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtility {

    public static final String DATE_TIME_FORMAT_TYPE_1 = "dd-MM-yyyy, hh:mm a";
    public static final String DATE_TIME_FORMAT_TYPE_2 = "yyyy-MM-dd hh:mm a";
    public static final String DATE_TIME_FORMAT_TYPE_3 = "dd-MM-yyyy, hh:mm";
    public static final String DATE_FORMAT_TYPE_1 = "dd-MM-yyyy";
    public static final String DATE_FORMAT_TYPE_2 = "dd-MMM-yy";
    public static final String DATE_FORMAT_TYPE_3 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_TYPE_4 = "dd-MMM-yyyy";
    public static final String BILLING_MONTH_FORMAT_TYPE_1 = "MM-yyyy";
    public static final String ONLY_TIME_FORMAT_TYPE_1 = "hh:mm a";

    public static SimpleDateFormat SIMPLE_DATE_FORMAT_TYPE_1 = new SimpleDateFormat(
            DATE_FORMAT_TYPE_1);

	public static final String DATE_DAY = "day";

	public static final String DATE_MONTH = "month";

	public static final String DATE_YEAR = "year";

	public static final long MILLISECS_PER_MINUTE = 60 * 1000;

	/**
	 * Number of milliseconds per hour, except when a leap second is inserted.
	 */
	public static final long MILLISECS_PER_HOUR = 60 * MILLISECS_PER_MINUTE;

	/**
	 * Number of leap seconds per day expect on <BR/>1. days when a leap second
	 * has been inserted, e.g. 1999 JAN 1. <BR/>2. Daylight-savings "spring
	 * forward" or "fall back" days.
	 */
	protected static final long MILLISECS_PER_DAY = 24 * MILLISECS_PER_HOUR;

	public static String formatDate(Date date, String format) {
		Date dateToBeFormatted = date;
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(dateToBeFormatted);
	}

	public static String formatDateWithoutTime(Date date) {
		if (date == null) {
			return "";
		}
		Date dateToBeFormatted = date;
		DateFormat dateFormat = new SimpleDateFormat(
				DATE_FORMAT_TYPE_1);
		return dateFormat.format(dateToBeFormatted);
	}

	public static String formatDateWithoutTimeForDB(Date date) {
		if (date == null) {
			return "";
		}
		Date dateToBeFormatted = date;
		DateFormat dateFormat = new SimpleDateFormat(
				DATE_FORMAT_TYPE_4);
		return dateFormat.format(dateToBeFormatted);
	}

	/*public static Date getDate(String strDate) {
		DateFormat dateFormat;
		Date date = null;

		try {
			dateFormat = new SimpleDateFormat(
					DATE_FORMAT_TYPE_1);
			if (isValidDate(strDate)) {
				date = dateFormat.parse(strDate);
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}*/
	
	
	public static Date getDateWithTime(String strDate) {
		DateFormat dateFormat;
		Date date = null;

		try {
			dateFormat = new SimpleDateFormat(
					DATE_TIME_FORMAT_TYPE_1);
			
				date = dateFormat.parse(strDate);
			
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateWithTwentyFourHourTime(String strDate) {
		DateFormat dateFormat;
		Date date = null;

		try {
			dateFormat = new SimpleDateFormat(
					DATE_TIME_FORMAT_TYPE_3);
			
				date = dateFormat.parse(strDate);
			
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateInYearFirstFormate(String strDate) {
		DateFormat dateFormat;
		Date date = null;

		try {
			dateFormat = new SimpleDateFormat(
					DATE_FORMAT_TYPE_3);
			
				date = dateFormat.parse(strDate);
			
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}
	
	
	
	public static java.sql.Date convertDate(Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	public static String getCurrentDate() {
		Date date = new Date();
		Date dateToBeFormatted = date;
		DateFormat dateFormat = new SimpleDateFormat(
				DATE_FORMAT_TYPE_1);
		return dateFormat.format(dateToBeFormatted);
	}

	/*public static Date getCurrentDateWithOutTime() {
		Date currentDate = new Date();
		String strDate = formatDateWithoutTime(currentDate);
		return getDate(strDate);
	}*/

	public static String getCustomDate(String dateFormatParameter) {
		Date date = new Date();
		Date dateToBeFormatted = date;
		DateFormat dateFormat = new SimpleDateFormat(dateFormatParameter);
		return dateFormat.format(dateToBeFormatted);
	}
	public static String getCustomDate(String dateFormatParameter,Date date) {
		//Date date = new Date();
		Date dateToBeFormatted = date;
		DateFormat dateFormat = new SimpleDateFormat(dateFormatParameter);
		return dateFormat.format(dateToBeFormatted);
	}

	@SuppressWarnings("deprecation")
	/*public static boolean isValidDate(String date) {
		if (org.apache.commons.validator.DateValidator.getInstance().isValid(date, DATE_FORMAT_TYPE_1, true)) {
			return true;
		} else if (org.apache.commons.validator.DateValidator.getInstance().isValid(date,
				DATE_TIME_FORMAT_TYPE_1, true)) {
			return true;
		} else if (org.apache.commons.validator.DateValidator.getInstance().isValid(date,
				DATE_TIME_FORMAT_TYPE_2, true)) {
			return true;
		}
		
		return false;
	}*/

	public static Date changeDate(String changeParameter, int changeValue,
			Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		if (changeParameter.equals(DateUtility.DATE_DAY)) {
			cal.add(Calendar.DAY_OF_MONTH, changeValue);
		} else if (changeParameter.equals(DateUtility.DATE_MONTH)) {
			cal.add(Calendar.MONTH, changeValue);
		} else if (changeParameter.equals(DateUtility.DATE_YEAR)) {
			cal.add(Calendar.YEAR, changeValue);
		}
		return cal.getTime();
	}
	
	public static long getDobYearByAge(int ageInYears)
	{
		long dobYear = 1900;
		
		try
		{
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());			
			dobYear = calendar.get(calendar.YEAR) - ageInYears;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return dobYear;
	}

	public static int getDifferenceInDays(Date firstDate, Date secondDate) {
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(firstDate);
		Calendar calendar2 = new GregorianCalendar();
		calendar2.setTime(secondDate);
		Long time1 = calendar1.getTimeInMillis()
				+ calendar1.getTimeZone()
						.getOffset(calendar1.getTimeInMillis());
		Long time2 = calendar2.getTimeInMillis()
				+ calendar2.getTimeZone()
						.getOffset(calendar2.getTimeInMillis());

		Long returnValue = (time2 - time1) / MILLISECS_PER_DAY;
		return Math.abs(returnValue.intValue());
	}

	public static int getDifferenceInDaysWithCurrentDate(Date date) {
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(date);
		Calendar calendar2 = new GregorianCalendar();
		calendar2.setTime(new Date());
		Long time1 = calendar1.getTimeInMillis()
				+ calendar1.getTimeZone()
						.getOffset(calendar1.getTimeInMillis());
		Long time2 = calendar2.getTimeInMillis()
				+ calendar2.getTimeZone()
						.getOffset(calendar2.getTimeInMillis());

		Long returnValue = (time2 - time1) / MILLISECS_PER_DAY;
		return Math.abs(returnValue.intValue());
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static int getDaysInMonth(GregorianCalendar c) {
		int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		daysInMonths[1] += c.isLeapYear(c.get(GregorianCalendar.YEAR)) ? 1 : 0;
		return daysInMonths[c.get(GregorianCalendar.MONTH)];
	}

	/**
	 * month should be from 0 to 11
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysInMonth(int year, int month) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(year, month, 1);
		return getDaysInMonth(calendar);
	}

	public static Long getCurrentYear()
	{
		Calendar calendar = new GregorianCalendar();
		return new Long(calendar.get(Calendar.YEAR));
	}
	
	public static void main(String[] args) throws ParseException
	{
		Calendar myBirthDate = Calendar.getInstance();
		myBirthDate.clear();
		myBirthDate.setTime(new Date());
		Calendar now = Calendar.getInstance();
		Calendar clone = (Calendar) myBirthDate.clone(); // Otherwise changes are been reflected.
		int years = -1;
		while (!clone.after(now)) {
		    clone.add(Calendar.YEAR, 1);
		    years++;
		}
		System.out.println(years); //
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dateFormat2 = new SimpleDateFormat("dd-MMM-yyyy");
		
		Date date=dateFormat.parse("01-01-1992");
		;
		System.out.println(dateFormat2.parse(dateFormat2.format(date)));
		
		/*
		 DATE_TIME_FORMAT_TYPE_1: dd-MM-yyyy, hh:mm a
		 06-04-2009, 06:53 P
		Date date = new Date();
		System.out.println(DateValidator.isValid("11-12-2009", DATE_FORMAT_TYPE_1));
		System.out.println();
		String date = "12-03-2011";
		Date currentDate=new Date();
		Date tempDate=DateUtility.getDate(date);
		int a = DateUtility.getDifferenceInDaysWithCurrentDate(DateUtility.getDate(date));
		( lastDate.getTime() - earlyDate.getTime() )/ MILLSECS_PER_DAY
		System.out.println("Current Date"+currentDate.toString());
		System.out.println("Temp Date"+tempDate.toString());
		
		Long a=(currentDate.getTime() - tempDate.getTime())/MILLISECS_PER_DAY;
		System.out.println( a);*/
		 
//		 String date = "10-03-2011";
//		 	Date currentDate=new Date();
//			Date myDate=DateUtility.getDate(date);
//			long millisecond = 24*60*60*1000; 
//			Long diffrence=(currentDate.getTime() - myDate.getTime())/millisecond;
//		 
//		System.out.println(diffrence);
	}
}