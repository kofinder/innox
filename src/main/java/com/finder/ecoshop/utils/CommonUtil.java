package com.finder.ecoshop.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

public class CommonUtil {
	
	
	public static String changeDateToString(String format, Date date) {
		if (date == null) {
			return null;
		}

		if (format == null || format.trim().isEmpty()) {
			format = CommonConstant.STD_DATE_TIME_FORMAT;
		}

		return new SimpleDateFormat(format).format(date);
	}
	
	public static String changedTimeToString(String format, Time time) {
		if (time == null) {
			return null;
		}

		if (isEmpty(format)) {
			format = CommonConstant.STD_TIME_FORMAT;
		}

		return new SimpleDateFormat(format).format(time);
	}

	public static Time changedStringToTime(String format, String timeString) {

		if (timeString == null || timeString.trim().isEmpty()) {
			return null;
		}

		if (format == null || format.trim().isEmpty()) {
			format = CommonConstant.STD_TIME_FORMAT_24;
		}

		return java.sql.Time.valueOf(timeString);

	}

	public static Date changeStringToDate(String format, String dateString) {

		if (dateString == null || dateString.trim().isEmpty()) {
			return null;
		}

		if (format == null || format.trim().isEmpty()) {
			format = CommonConstant.STD_DATE_TIME_FORMAT;
		}

		try {
			return new SimpleDateFormat(format).parse(dateString.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String changeFormatOfDateString(String fromFormat, String toFormat, String dateString) {

		if (fromFormat == null || toFormat == null || dateString == null || fromFormat.trim().isEmpty()
				|| toFormat.trim().isEmpty() || dateString.trim().isEmpty()) {
			return "";
		}

		Date tempDate = CommonUtil.changeStringToDate(fromFormat, dateString);

		if (tempDate == null) {
			return "";
		}

		String formattedString = CommonUtil.changeDateToString(toFormat, tempDate);

		return (formattedString == null) ? "" : formattedString;
	}

	public static Date getStartTimeOfTheDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static Date getEndTimeOfTheDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	

	// check null or empty string
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("") || str.isEmpty()) {
			return true;
		}

		return false;
	}
	
	public static boolean isValidDateFormat(String format, String dateString) {
		if (format == null || format.trim().isEmpty()) {
			format = CommonConstant.STD_DATE_TIME_FORMAT;
		}

		if (dateString == null || dateString.trim().isEmpty()) {
			return false;
		}

		try {
			Date date = new SimpleDateFormat(format).parse(dateString.trim());
			System.out.println(date);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	public static boolean isValidStringForDateOnlyFormat(String dateString) {
		String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(dateString).matches();
	}

	public static boolean isValidStringForDateTimeFormat(String dateTimeString) {
		String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9] ((1[0-2]|0?[1-9]):([0-5][0-9]) ([AaPp][Mm]))$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(dateTimeString).matches();
	}
	
	public static String getTodayOrYesterdayStringWithTime(Date date) {
		if (date == null) {
			return "";
		}

		Calendar calendar = fromDateToCalendar(date);
		Calendar now = Calendar.getInstance();

		if ((now.get(Calendar.MONTH) == calendar.get(Calendar.MONTH))
				&& (now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR))) {

			if (now.get(Calendar.DATE) == calendar.get(Calendar.DATE)) {
				return ("TODAY(" + CommonUtil.changeDateToString(CommonConstant.STD_TIME_FORMAT, date) + ")");
			} else if (now.get(Calendar.DATE) - calendar.get(Calendar.DATE) == 1) {
				return ("YESTERDAY(" + CommonUtil.changeDateToString(CommonConstant.STD_TIME_FORMAT, date) + ")");
			}

		}

		return "";
	}

	public static Calendar fromDateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Date getYesterdayDate() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static int getWeeksOfCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}

	public static int totalPages(int totalCount, int showItemCount) {
		int totalPage = 0;
		if (totalCount > 0) {
			totalPage = totalCount / showItemCount;
			if ((totalCount % showItemCount) > 0) {

				totalPage += 1;
			}

		}
		return totalPage;
	}

	public static int[] calculatePagination(int totalCount, int pageNumber, int totalPage) {
		int[] countArray = new int[2];
		int previousNo = 0;
		int nextNo = 0;
		int showPaginCount = CommonConstant.SHOW_PAGINATION_COUNT;

		if (pageNumber <= showPaginCount) {
			previousNo = 0;
			nextNo = showPaginCount;
			if (nextNo < totalPage) {
				nextNo += 1;
			} else {
				nextNo = totalPage + 1;
			}
		} else if (pageNumber > showPaginCount) {

			previousNo = pageNumber / showPaginCount * showPaginCount;
			nextNo = previousNo + showPaginCount + 1;
			if (nextNo > totalPage) {
				previousNo -= nextNo - totalPage - 1;
				nextNo = totalPage + 1;

			}

		}

		countArray[0] = previousNo;
		countArray[1] = nextNo;
		return countArray;

	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static String isoTimeZone(Date date) {

		// Calendar now = Calendar.getInstance();

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		TimeZone timeZone = now.getTimeZone();

		DateFormat df = new SimpleDateFormat(CommonConstant.STD_ISO_TIME_FORMAT);

		df.setTimeZone(timeZone);

		return df.format(date);
	}

	public static String parseString(String str) {
		return HtmlUtils.htmlEscape(str);
	}

	public static int calculateAge(Date dateOfBirth) {
		return Period.between(convertToLocalDate(dateOfBirth), convertToLocalDate(new Date())).getYears();
	}

	public static int calculateCreatedTime(Date createdTime) {
		return Period.between(convertToLocalDate(createdTime), convertToLocalDate(new Date())).getYears();
	}

	public static LocalDate convertToLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Date addDate(Date date, int addDay) {
		Calendar cs = Calendar.getInstance();
		cs.setTime(date);
		cs.add(Calendar.DATE, addDay);
		return cs.getTime();
	}

	// write base64 file to given file path with given file name
	public static void writeBase64File(String data, String name, String saveDirectory)
			throws IllegalStateException, IOException {
		byte[] decoded = Base64.decodeBase64(data);
		File dest = new File(saveDirectory, name);
		FileUtils.writeByteArrayToFile(dest, decoded);
	}

	public static int calculateDayLeft(String startDate, String endDate, String currentDate) {

		if (CommonUtil.isEmpty(startDate) || CommonUtil.isEmpty(endDate)) {
			return 0;
		}

		DateTime start = new DateTime(startDate);
		DateTime end = new DateTime(endDate);
		DateTime current = new DateTime(currentDate);
		if (current.isBefore(start)) {
			return -1;
		} else if (!current.isBefore(end)) {
			return 0;
		}

		// calculate days left of between start date and end date
		return Days.daysBetween(current, end).getDays();
	}

	public static int calculateDay(String startDate, String currentDate) {

		if (CommonUtil.isEmpty(startDate) || CommonUtil.isEmpty(currentDate)) {
			return 0;
		}
		DateTime start = DateTime.parse(startDate);
		DateTime current = new DateTime(currentDate);
		// calculate day value of between start date and current date
		return Days.daysBetween(start, current).getDays();
	}

	public static BigDecimal calcualtePercentage(BigDecimal obtained, BigDecimal goal) {
		if (obtained.compareTo(goal) == 1) {
			return new BigDecimal(100);
		}
		return (obtained.multiply(new BigDecimal(100))).divide(goal, 2, RoundingMode.HALF_UP);
	}

	public static BigDecimal calculateRequired(BigDecimal obtained, BigDecimal goal) {
		if (obtained.compareTo(goal) == 1) {
			return BigDecimal.ZERO;
		}

		return goal.subtract(obtained).setScale(2, RoundingMode.HALF_UP);

	}

	public static boolean isNumeric(String strNum) {
		try {
			new BigDecimal(strNum);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static String prepareImagePath(String imagePath, HttpServletRequest req) {
		String urlScheme = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
		String basePath = urlScheme + "/" + CommonConstant.IMAGE_PATH + "/";

		if (!isEmpty(imagePath)) {
			return basePath + imagePath;
		}

		return urlScheme + req.getContextPath() + "/" + CommonConstant.IMAGE_RESOURCES_PATH + "/"
				+ CommonConstant.DEFAULT_LOGO;
	}

	public static String formatBigDecimalAsCurrency(BigDecimal amount, String currencyCode) {
		if (amount != null) {
			amount = amount.setScale(0, RoundingMode.HALF_UP);
		}
		DecimalFormat df = new DecimalFormat("###,###.##");
		return df.format(amount) + " " + currencyCode;
	}

	public static synchronized String generateTransactionRefNo() {
		int rN = new Random().nextInt(9) + 1;
		return rN + "" + new Date().getTime();
	}

	public static String getImageBasePath(HttpServletRequest req) {
		String url = req.getRequestURL().toString();
		String basePath = url.substring(0, url.indexOf(req.getRequestURI()));
		basePath = basePath + CommonConstant.IMAGE_PATH;
		return basePath;
	}

	public static double getPercentageDiscount(BigDecimal originalPrice, BigDecimal sellingPrice) {

		if (originalPrice == null || originalPrice.compareTo(BigDecimal.ZERO) <= 0 || sellingPrice == null
				|| sellingPrice.compareTo(BigDecimal.ZERO) <= 0) {
			return 0;
		}

		try {
			BigDecimal discountPrice = originalPrice.subtract(sellingPrice);
			BigDecimal percentageOfDiscount = discountPrice.divide(originalPrice, 2, RoundingMode.HALF_UP)
					.multiply(new BigDecimal(100));

			return percentageOfDiscount.doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void multipartZipFileSave(MultipartFile clientFile,String basePath) throws IOException{		
		String fileName = clientFile.getOriginalFilename();
		File dest = new File(basePath, fileName);
		FileUtils.writeByteArrayToFile(dest, clientFile.getBytes());
	}
	
	 public static String unzipForImage(String zipFileDir, String zipFileName,String unzipDir) {
		 	if(!new File(unzipDir).exists()){
		 		new File(unzipDir).mkdirs();
		 	}
	        String zipFilePath = zipFileDir + File.separator + zipFileName;
	        String entryName = "";
	        try{
	            System.out.println("zipFilePath = " + zipFilePath);
	            ZipFile zipFile = new ZipFile(zipFilePath);

	            Enumeration<? extends ZipEntry> entries = zipFile.entries();

	            while(entries.hasMoreElements()){
	                ZipEntry entry = entries.nextElement();
	                if(entry.isDirectory()){
	                    System.out.print("dir  : " + entry.getName());
	                    entryName = entry.getName();
	                    String destPath = unzipDir + File.separator + entry.getName();
	                    System.out.println(" => " + destPath);
	                    File file = new File(destPath);
	                    file.mkdirs();
	                } else {
	                    String destPath = unzipDir + File.separator + entry.getName();
	                	try(InputStream inputStream = zipFile.getInputStream(entry);
	                        FileOutputStream outputStream = new FileOutputStream(destPath);
	                    ){
	                        int data = inputStream.read();
	                        while(data != -1){
	                            outputStream.write(data);
	                            data = inputStream.read();
	                        }
	                    }
	                    System.out.println("file : " + entry.getName() + " => " + destPath);
	                }
	            }
	            return entryName;
	        } catch(Exception e){
	        	e.printStackTrace();
	            throw new RuntimeException("Error unzipping file " + zipFilePath, e);
	        }
	    }
	
	

}
