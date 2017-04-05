package com.lancoo.tasker.audio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * Create in 2015年11月17日下午3:00:26 by DoubleMeng Liu.
 * If you have any suggestions, send e-mail to abc@doulemeng.com.
 */
public class AudioTool {
	public static String inputStream2String(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		try {
			while ((i = is.read()) != -1) {
				baos.write(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toString();
	}

	
	public static InputStream string2InputStream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
	
	public static String uriEncode(String uri){
		return URI.create(uri.replace(" ", "%20")).toASCIIString();
	}
	/**
	 * 
	 * @param dateStrA
	 * @param dateStrB
	 * @return 0:= ,  -1:A<B  ,  1:A>B.   error:-2
	 */
	public static int compareDateStr(String dateStrA,String dateStrB,String format){
		try {
			Date dateA =parseDateFormatStr(dateStrA, format);
			Date dateB =parseDateFormatStr(dateStrB, format);
			if (dateA.getTime()==dateB.getTime()) {
				return 0;
			}else if (dateA.getTime()>dateB.getTime()) {
				return 1;
			}else {
				return -1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String capFirst(String str) {
		char[] cs = str.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}
	
	
	public static String getDateFormatStr(long milliseconds, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(new Date(milliseconds));
	}
	
	public static String getTimeStrNow(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
	
	public static Date parseDateFormatStr(String dateFormatStr, String format) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.parse(dateFormatStr);
	}
	
	public static String millisToString(long millis, boolean text) {
		boolean negative = millis < 0;
		millis = Math.abs(millis);
		millis /= 1000;
		int sec = (int) (millis % 60);
		millis /= 60;
		int min = (int) (millis % 60);
		millis /= 60;
		int hours = (int) millis;

		String time;
		DecimalFormat format = (DecimalFormat) NumberFormat
				.getInstance(Locale.CHINA);
		format.applyPattern("00");
		if (text) {
			if (millis > 0)
				time = (negative ? "-" : "") + hours + "h" + format.format(min)
						+ "min";
			else if (min > 0)
				time = (negative ? "-" : "") + min + "min";
			else
				time = (negative ? "-" : "") + sec + "s";
		} else {
			if (millis > 0)
				time = (negative ? "-" : "") + hours + ":" + format.format(min)
						+ ":" + format.format(sec);
			else
				time = (negative ? "-" : "") + min + ":" + format.format(sec);
		}
		return time;
	}
	
}
