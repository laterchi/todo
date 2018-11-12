/**
 * @description
 */
package late.workday.controller;

import org.springframework.web.bind.annotation.RestController;

import late.workday.entity.WorkdayCalendar;

/**
 * 
 * @projectName todo
 * @packageName late.workday.controller
 * @fileName WorkdayController.java
 * @author chijingjia
 * @createTime :2018年11月9日 上午10:01:50
 * @version: v1.0
 */
@RestController
public class WorkdayController {

	/**
	 * 实例化日历
	 * 
	 * @methodName init
	 * @author chijingjia
	 * @createTime 2018年11月9日 上午10:02:37
	 * @version v1.0
	 * @param workdayCalendar
	 * @return
	 */
	public WorkdayCalendar init(WorkdayCalendar workdayCalendar) {
		return workdayCalendar;
	}
}
