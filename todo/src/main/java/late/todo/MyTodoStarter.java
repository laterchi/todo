package late.todo;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import late.todo.cons.SystemPropertyConstants;
import late.todo.entity.SystemPropertyEntity;
import late.todo.service.IDataBackupService;
import late.todo.service.ISystemPropertyService;
import lombok.extern.slf4j.Slf4j;

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
@ComponentScan(value = "late.workday.controller")
@ComponentScan(value = "late.workday.entity")
@ComponentScan(value = "late.workday.repo")
@Slf4j
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
		ISystemPropertyService systemPropertyService = applicationContext.getBean(ISystemPropertyService.class);
		SystemPropertyEntity systemPropertyEntity = systemPropertyService.getByName(SystemPropertyConstants.PROP_IMPONSTART_FLAG);
		if (systemPropertyEntity != null && "Y".equals(systemPropertyEntity.getValue())) {
			IDataBackupService backupService = applicationContext.getBean(IDataBackupService.class);
			backupService.imp(applicationContext);
			// backupService.backup(applicationContext);
		} else {
			log.info(String.format("loadOnStartup 标志为%s,不执行导入。", systemPropertyEntity.getValue()) );
		}
	}

	/**
	 * 
	 * @methodName getBean
	 * @author chijingjia
	 * @createTime 2018年11月5日 下午8:01:54
	 * @version v1.0
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> Map<String, T> getBeanOfType(Class<T> clazz) {
		return applicationContext.getBeansOfType(clazz);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
