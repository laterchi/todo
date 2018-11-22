/**
 * @description
 */
package late.todo.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import late.comm.utils.CustomSystemUtil;
import late.todo.entity.QTodoMastEntity;
import late.todo.entity.TodoMastEntity;
import late.todo.eum.TodoMastStatus;
import late.todo.service.ITodoMastService;

/**
 * 待办事项定时邮件
 * 
 * @projectName todo
 * @packageName late.todo.scheduled
 * @fileName TodoMailScheduled.java
 * @author chijingjia
 * @createTime :2018年10月7日 下午3:30:04
 * @version: v1.0
 */
@Component
public class TodoMailScheduled {
	@Autowired
	ITodoMastService todoMastService;
	@Autowired
	JavaMailSender jms;

	@Value("${late.mail.sendto}")
	private String mailSendTo;

	@Scheduled(cron = "0 10 9-21 * * ?")
	public void sendTodoList() {
		// 建立邮件消息
		SimpleMailMessage mainMessage = new SimpleMailMessage();
		// 发送者
		mainMessage.setFrom("cjj957225907@163.com");
		// 接收者
		mainMessage.setTo(mailSendTo);
		// 发送的标题
		mainMessage.setSubject("工作提醒");
		TodoMastEntity todoMastEntity = new TodoMastEntity();

		Sort sort = new Sort(Direction.DESC, QTodoMastEntity.todoMastEntity.lvl.getMetadata().getName(),
				QTodoMastEntity.todoMastEntity.id.getMetadata().getName(),
				QTodoMastEntity.todoMastEntity.status.getMetadata().getName());
		Pageable pageable = PageRequest.of(0, 15, sort);
		/**
		 * 处理中的工作
		 */
		todoMastEntity.setStatus(TodoMastStatus.PROCESS);
		Page<TodoMastEntity> todoList = todoMastService.findByEntity(todoMastEntity, pageable);
		StringBuilder text = new StringBuilder();
		long totalCnt;

		if ((totalCnt = todoList.getTotalElements()) == 0L) {
			text.append("干的漂亮，没有剩余工作");
		} else {
			text.append(String.format("剩余 %d 项工作没有完成，需要努力啊。\n\n", totalCnt));
			text.append("工作列表：").append("\n");
			for (TodoMastEntity todoEntity : todoList) {
				text.append("Lvl：").append(todoEntity.getLvl())//
						.append("    内容：").append(todoEntity.getTitle())//
						.append(" 状态：").append(todoEntity.getStatus().name())//
						.append("\n");
			}
		}
		text.append("\n\n");

		/**
		 * 未开始的工作
		 */
		todoMastEntity = new TodoMastEntity();
		todoMastEntity.setStatus(TodoMastStatus.NEW);
		todoList = todoMastService.findByEntity(todoMastEntity, pageable);

		if ((totalCnt = todoList.getTotalElements()) == 0L) {
			text.append("没有未开始的工作");
		} else {
			text.append(String.format("仍有%ld份工作未开始，别忘记呦。", totalCnt));
		}
		text.append("\n\n");

		text.append("Send by ").append(CustomSystemUtil.HOSTNAME).append("@").append(CustomSystemUtil.INTERNET_IP);

		// 发送的内容
		mainMessage.setText(text.toString());

		jms.send(mainMessage);
		return;
	}

}
