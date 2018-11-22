/**
 * @description
 */
package late.todo.service.impl;

import org.springframework.stereotype.Service;

import late.todo.service.IMailService;

/**
 * 自动生成
 */
@Service
public class MailServiceImpl implements IMailService {
	/**
	 * 发送邮件标志
	 */
	private static char FLG_SEND_MAIL = '0';
	/**
	 * 标志-可用
	 */
	private static char USEABLE = '0';
	/**
	 * 标志-不可用
	 */
	private static char DISABLE = '1';

	@Override
	public char getMailFlag() {
		return FLG_SEND_MAIL;
	}

	@Override
	public void beginSendMail() {
		if (FLG_SEND_MAIL == DISABLE) {
			FLG_SEND_MAIL = USEABLE;
		}
	}

	@Override
	public void stopSendMail() {
		if (FLG_SEND_MAIL == USEABLE) {
			FLG_SEND_MAIL = DISABLE;
		}
	}

	@Override
	public boolean mailUseable() {
		return FLG_SEND_MAIL == USEABLE;
	}

}