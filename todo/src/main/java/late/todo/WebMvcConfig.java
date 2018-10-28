/**
 * @description
 */
package late.todo;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源配置
 * 
 * @projectName todo
 * @packageName late.todo
 * @fileName WebMvcConfig.java
 * @author chijingjia
 * @createTime :2018年10月6日 下午7:39:54
 * @version: v1.0
 * @deprecated resources名字起错为resource，导致未认为静态文件。修改后此类废弃
 */
//@Configuration
@Deprecated
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/resources/templates/**")) {
			registry.addResourceHandler("/resources/templates/**").addResourceLocations("classpath:/resources/templates/");
		}
	}

}
