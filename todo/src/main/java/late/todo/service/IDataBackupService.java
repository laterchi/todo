/**
 * @description
 */
package late.todo.service;

import org.springframework.context.ApplicationContext;

/**
 * 
 * @projectName todo
 * @packageName late.todo.service
 * @fileName IDataBackupService.java
 * @author chijingjia
 * @createTime :2018年10月31日 上午9:04:53
 * @version: v1.0
 */
public interface IDataBackupService {

	/**
	 * 导入
	 * 
	 * @methodName imp
	 * @author chijingjia
	 * @createTime 2018年10月31日 上午9:06:23
	 * @version v1.0
	 * @param applicationContext
	 */
	void imp(ApplicationContext applicationContext);

	/**
	 * 导出
	 * 
	 * @methodName export
	 * @author chijingjia
	 * @createTime 2018年10月31日 上午9:06:28
	 * @version v1.0
	 * @param applicationContext
	 */
	void backup(ApplicationContext applicationContext);
}
