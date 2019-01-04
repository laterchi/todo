/**
 * @description
 */
package late.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import late.system.entity.SystemMonitorEntity;
import late.system.entity.TradingVolumeEntity;
import late.system.service.ISystemMonitorService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @projectName todo
 * @packageName late.system.controller
 * @fileName SystemMonitorController.java
 * @author chijingjia
 * @createTime :2019年1月2日 下午1:56:20
 * @version: v1.0
 */
@RestController
@RequestMapping(value = "/sm")
@Api(value = "系统监控", tags = "系统监控")
@Slf4j
public class SystemMonitorController {
	@Autowired
	ISystemMonitorService monitorService;

	/**
	 * 获取系统信息
	 * 
	 * @methodName getSystemInfo
	 * @author chijingjia
	 * @createTime 2019年1月2日 下午2:57:54
	 * @version v1.0
	 * @return
	 */
	@GetMapping(value = "/get")
	public SystemMonitorEntity getSystemInfo() {
		String str = System.getProperty("java.library.path");
		log.debug("java.library.path:" + str);
		return monitorService.getSystemInfo();
	}

	/**
	 * 统计交易量
	 * 
	 * @methodName getTradeVolume
	 * @author chijingjia
	 * @createTime 2019年1月3日 上午10:45:48
	 * @version v1.0
	 * @return
	 */
	@GetMapping(value = "/tradeV")
	public List<TradingVolumeEntity> getTradeVolume() {
		return monitorService.getTradeVolume();
	}

}
