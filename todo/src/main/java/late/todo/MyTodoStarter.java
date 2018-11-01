package late.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import late.todo.service.IDataBackupService;

/**
 * 待办内容
 * 
 * @projectName todo
 * @packageName late.todo
 * @fileName MyTodoStarter.java
 * @author chijingjia
 * @createTime :2018年10月6日 下午7:16:47
 * @version: v1.0
 */
@SpringBootApplication
@EnableScheduling
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public class MyTodoStarter {
	static ApplicationContext applicationContext;

	/**
	 * 启动方法
	 * 
	 * @methodName main
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午7:17:03
	 * @version v1.0
	 * @param args
	 */
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(MyTodoStarter.class, args);

		IDataBackupService backupService = applicationContext.getBean(IDataBackupService.class);
		backupService.imp(applicationContext);
		backupService.backup(applicationContext);
	}

}
