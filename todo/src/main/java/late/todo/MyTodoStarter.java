package late.todo;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import late.comm.entity.MaintainMongoEntity;

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
@EnableMongoAuditing
public class MyTodoStarter {
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
		SpringApplication.run(MyTodoStarter.class, args);
	}

	@Bean
	public AuditorAware<MaintainMongoEntity> myAuditorProvider() {
		return new AuditorAware<MaintainMongoEntity>() {
			@Override
			public Optional<MaintainMongoEntity> getCurrentAuditor() {
				return Optional.empty();
			}
		};
	}

}
