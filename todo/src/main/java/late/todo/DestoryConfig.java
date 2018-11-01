/**
 * @description
 */
package late.todo;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import late.todo.service.IDataBackupService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @projectName todo
 * @packageName late.todo
 * @fileName DestoryConfig.java
 * @author chijingjia
 * @createTime :2018年10月31日 上午9:03:01
 * @version: v1.0
 */
@Slf4j
@Component
public class DestoryConfig {
	@Autowired
	IDataBackupService dataBackupService;

	/**
	 * 关闭时执行
	 * 
	 * @methodName saveDataToFile
	 * @author chijingjia
	 * @createTime 2018年10月31日 上午9:03:40
	 * @version v1.0
	 */
	@PreDestroy
	public void saveDataToFile() {
		log.info("系统关闭-保存数据信息");
		dataBackupService.backup(MyTodoStarter.applicationContext);
		log.info("系统关闭-保存数据信息结束");
	}
}
