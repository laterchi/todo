/**
 * @description
 */
package late.todo.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 阿里云配置
 * 
 * @projectName todo
 * @packageName late.todo.prop
 * @fileName AliyunProperties.java
 * @author chijingjia
 * @createTime :2019年2月10日 下午4:51:23
 * @version: v1.0
 */
@Component
@ConfigurationProperties(prefix = "late.aliyun")
@PropertySource("classpath:application.yml")
@Data
public class AliyunProperties {
	/**
	 * key
	 */
	private String accessKeyId;
	/**
	 * 密钥
	 */
	private String accessKeySecret;
	/**
	 * 域名
	 */
	private String domainName;
}
