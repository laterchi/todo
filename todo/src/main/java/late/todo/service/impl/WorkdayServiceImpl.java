/**
 * @description
 */
package late.todo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import late.todo.entity.WorkdayCalendarEntity;
import late.todo.repo.IWorkdayRepository;
import late.todo.service.IWorkdayRemoteService;
import late.todo.service.IWorkdayService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @projectName todo
 * @packageName late.todo.service.impl
 * @fileName WorkdayServiceImpl.java
 * @author chijingjia
 * @createTime :2018年11月13日 下午2:37:30
 * @version: v1.0
 */
@Slf4j
@Service
public class WorkdayServiceImpl implements IWorkdayService {
	@Autowired
	IWorkdayRemoteService workdayRemoteService;
	@Autowired
	IWorkdayRepository workdayRepository;

	@Override
	public WorkdayCalendarEntity init(WorkdayCalendarEntity workdayCalendarEntity) {
		WorkdayCalendarEntity workdayCheck = workdayRepository.getOneByYearAndMonth(workdayCalendarEntity.getYear(),
				workdayCalendarEntity.getMonth());
		if (workdayCheck != null) {
			log.info("日历已存在，忽略。");
			return workdayCheck;
		}
		workdayRemoteService.getWorkdayByMonth(workdayCalendarEntity);

		workdayRepository.save(workdayCalendarEntity);
		return null;
	}

}
