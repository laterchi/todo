/**
 * @description
 */
package late.todo.service.impl;

import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import late.todo.entity.WorkdayCalendarEntity;
import late.todo.service.IWorkdayRemoteService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @projectName todo
 * @packageName late.todo.service.impl
 * @fileName WorkdayRemoteServiceImpl.java
 * @author chijingjia
 * @createTime :2018年11月13日 下午3:34:26
 * @version: v1.0
 */
@Slf4j
@Service
public class WorkdayRemoteServiceImpl implements IWorkdayRemoteService {
	@Autowired
	RestTemplate restTemplate;
	@Value(value = "${late.workday.url}")
	String remoteUrl;

	@Override
	public WorkdayCalendarEntity getWorkdayByMonth(WorkdayCalendarEntity workdayCalendarEntity) {
		String month = String.format("%04d%02d", workdayCalendarEntity.getYear(), workdayCalendarEntity.getMonth());
		log.debug("获取{}的日历", month);
		if (month.length() != "201801".length()) {
			return null;
		}
		JSONObject workdayJson = restTemplate.getForObject(remoteUrl.replace("$date", month), JSONObject.class);

		Calendar calendar = Calendar.getInstance();
		calendar.set(workdayCalendarEntity.getYear(), workdayCalendarEntity.getMonth(), 0);
		calendar.get(Calendar.DAY_OF_MONTH);

		char[] caleChars = new char[calendar.get(Calendar.DAY_OF_MONTH)];
		Arrays.fill(caleChars, '0');

		if (!Boolean.class.equals(workdayJson.getObject(month, Object.class).getClass())) {
			JSONObject holidays = workdayJson.getJSONObject(month);
			for (String date : holidays.keySet()) {
				int day = Integer.valueOf(date.substring(2, 4));
				caleChars[day] = ((String) holidays.get(date)).charAt(0);
				log.trace("日期:{},假期类型:{}", date, holidays.get(date));
			}
		} else {
			log.info(month + " 无节假日");
		}
		workdayCalendarEntity.setDay(caleChars);

		return workdayCalendarEntity;
	}

}
