/**
 * @description
 */
package late.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import late.comm.utils.CustomSystemUtil;
import late.todo.service.IIPRemoteService;
import late.todo.service.IMailService;
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
@RequestMapping(value = "/mail")
public class MailController {
	@Autowired(required = false)
	JavaMailSender jms;
	@Autowired(required = false)
	ITodoMastService todoMastService;
	@Autowired(required = false)
	IMailService iMailService;
	@Autowired
	IIPRemoteService ipRemoteService;

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
		// jms.send(mainMessage);
		System.out.println(CustomSystemUtil.HOSTNAME);
		return "1";
	}

	@GetMapping(value = "/flag")
	public char getMailFlag() {
		return iMailService.getMailFlag();
	}

	@PutMapping(value = "/start")
	public void beginSendMail() {
		iMailService.beginSendMail();
	}

	@PutMapping(value = "/stop")
	public void stopSendMail() {
		iMailService.stopSendMail();
	}

	@GetMapping(value = "/ip")
	public void getIP() {
		ipRemoteService.getInternetIP();
	}

}
