/**
 * @description
 */
package late.comm.utils;

/**
 * 
 * @projectName todo
 * @packageName late.comm.entity
 * @fileName IntegerUtils.java
 * @author chijingjia
 * @createTime :2018年10月28日 下午4:41:00
 * @version: v1.0
 */
public class IntegerUtils {
	/**
	 * 判断空值
	 * 
	 * @methodName isEmpty
	 * @author chijingjia
	 * @createTime 2018年10月28日 下午4:42:14
	 * @version v1.0
	 * @param val
	 * @return
	 */
	public static boolean isEmpty(Integer value) {
		return value == null || value == 0;
	}

	/**
	 * 判断不为空
	 * 
	 * @methodName isNotEmpty
	 * @author chijingjia
	 * @createTime 2018年10月28日 下午4:42:45
	 * @version v1.0
	 * @param id
	 * @return
	 */
	public static boolean isNotEmpty(Integer value) {
		return !isEmpty(value);
	}
}
