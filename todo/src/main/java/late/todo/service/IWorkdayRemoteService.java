/**
 * @description
 */
package late.todo.service;

import late.todo.entity.WorkdayCalendarEntity;

/**
 * 
 * @projectName todo
 * @packageName late.todo.service
 * @fileName IWorkdayRemoteService.java
 * @author chijingjia
 * @createTime :2018年11月13日 下午3:34:11
 * @version: v1.0
 */
public interface IWorkdayRemoteService {
	/**
	 * 根据月份获取节假日
	 * 
	 * @methodName getWorkday
	 * @author chijingjia
	 * @createTime 2018年11月13日 下午3:35:34
	 * @version v1.0
	 * @param date
	 * @return
	 */
	WorkdayCalendarEntity getWorkdayByMonth(WorkdayCalendarEntity workdayCalendarEntity);
}
