/**
 * @description
 */
package late.todo.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 
 * @projectName todo
 * @packageName late.todo.prop
 * @fileName FcrProperties.java
 * @author chijingjia
 * @createTime :2019年1月3日 上午10:48:55
 * @version: v1.0
 */
@Component
@ConfigurationProperties(prefix="late.fcr")
@PropertySource("classpath:application.yml")
@Data
public class FcrProperties {
	private String url;
	private String user;
	private String pwd;
	private String schema;
}
