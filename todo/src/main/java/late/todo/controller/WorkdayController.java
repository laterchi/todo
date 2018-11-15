/**
 * @description
 */
package late.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import late.todo.entity.WorkdayCalendarEntity;
import late.todo.service.IWorkdayService;

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
@RequestMapping(value = "/workday")
public class WorkdayController {
	@Autowired
	IWorkdayService workdayService;
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
	@RequestMapping(value = "init", method = RequestMethod.GET)
	public WorkdayCalendarEntity init(WorkdayCalendarEntity workdayCalendar) {
		workdayService.init(workdayCalendar);
		return workdayCalendar;
	}
}
