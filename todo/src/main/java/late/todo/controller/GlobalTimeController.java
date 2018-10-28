/**
 * @description
 */
package late.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import late.todo.entity.GlobalTimeEntity;
import late.todo.service.IGlobalTimeService;

/**
 * 时间控制器
 * 
 * @projectName todo
 * @packageName late.todo.controller
 * @fileName HaltTimeController.java
 * @author chijingjia
 * @createTime :2018年10月8日 下午9:54:34
 * @version: v1.0
 */
@RestController
@RequestMapping("/time")
public class GlobalTimeController {
	@Autowired
	IGlobalTimeService globalTimeService;

	/**
	 * 保存时间信息
	 * 
	 * @methodName insertTime
	 * @author chijingjia
	 * @createTime 2018年10月8日 下午10:00:01
	 * @version v1.0
	 * @param timeEntity
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insertTime(@RequestBody GlobalTimeEntity timeEntity) {
		globalTimeService.insert(timeEntity);
	}

	/**
	 * 保存时间信息
	 * 
	 * @methodName insertTime
	 * @author chijingjia
	 * @createTime 2018年10月8日 下午10:00:01
	 * @version v1.0
	 * @param timeEntity
	 */
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public Page<GlobalTimeEntity> find(GlobalTimeEntity timeEntity, Pageable pageable) {
		return globalTimeService.findByEntity(timeEntity, pageable);
	}
}
