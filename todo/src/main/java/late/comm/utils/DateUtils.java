/**
 * @description
 */
package late.comm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @projectName todo
 * @packageName late.comm.utils
 * @fileName DateUtils.java
 * @author chijingjia
 * @createTime :2018年10月28日 下午6:36:24
 * @version: v1.0
 */
public class DateUtils {
	/**
	 * 日期字符串
	 */
	private static final String DEFAULT_DATETIME_STR_FORMATTER = "yMdHmsS";

	/**
	 * 日期字符串
	 * 
	 * @methodName formatDateTimeStr
	 * @author chijingjia
	 * @createTime 2018年10月28日 下午6:38:28
	 * @version v1.0
	 * @param date
	 * @return
	 */
	public static String formatDateTimeStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_STR_FORMATTER);
		return sdf.format(date);
	}
	
	public static void main(String[] args) {
		System.out.println(formatDateTimeStr(new Date()));
	}
}
