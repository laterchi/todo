/**
 * @description
 */
package late.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import late.todo.service.ITodoMastService;

/**
 * 
 * @projectName todo
 * @packageName late.todo.controller
 * @fileName MailController.java
 * @author chijingjia
 * @createTime :2018年10月7日 下午2:44:52
 * @version: v1.0
 */
@RestController
public class MailController {
	@Autowired
	JavaMailSender jms;
	@Autowired
	ITodoMastService todoMastService;

	@GetMapping("/send")
	public String send() { // 建立邮件消息
		SimpleMailMessage mainMessage = new SimpleMailMessage();
		// 发送者
		mainMessage.setFrom("cjj957225907@163.com");
		// 接收者
		mainMessage.setTo("chijingjia@tfrunning.com.cn");
		// 发送的标题
		mainMessage.setSubject("工作提醒");
		// 发送的内容
		mainMessage.setText("hello world");
		jms.send(mainMessage);
		return "1";
	}
}
