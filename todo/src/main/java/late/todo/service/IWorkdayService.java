package late.todo.service;

import late.todo.entity.WorkdayCalendarEntity;

/**
 * 
 * @projectName todo
 * @packageName late.todo.service
 * @fileName IWorkdayService.java
 * @author chijingjia
 * @createTime :2018年11月13日 下午3:01:25
 * @version: v1.0
 */
public interface IWorkdayService {
	/**
	 * 
	 * @methodName init
	 * @author chijingjia
	 * @createTime 2018年11月13日 下午3:01:51
	 * @version v1.0
	 * @param workdayCalendarEntity
	 * @return
	 */
	WorkdayCalendarEntity init(WorkdayCalendarEntity workdayCalendarEntity);
}
