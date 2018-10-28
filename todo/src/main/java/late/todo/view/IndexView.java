/**
 * @description
 */
package late.todo.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @projectName todo
 * @packageName late.todo.view
 * @fileName IndexView.java
 * @author chijingjia
 * @createTime :2018年10月6日 下午7:34:51
 * @version: v1.0
 */
@Controller(value = "/")
public class IndexView {

	/**
	 * 
	 * @methodName index
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午7:35:14
	 * @version v1.0
	 * @return
	 */
	@RequestMapping(value = { "/index", "/index.html" }, method = { RequestMethod.GET })
	public String index() {
		return "index.html";
	}
	
}
