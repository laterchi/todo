/**
 * @description
 */
package late.todo.service;

/**
 * 自动生成
 */
public interface IMailService {

	/**
	 * 获取标志
	 * 
	 * @methodName getMailFlag
	 * @author chijingjia
	 * @createTime 2018年11月22日 上午9:11:30
	 * @version v1.0
	 * @return
	 */
	char getMailFlag();

	/**
	 * 开始发送邮件
	 * 
	 * @methodName beginSendMail
	 * @author chijingjia
	 * @createTime 2018年11月22日 上午9:11:26
	 * @version v1.0
	 */
	void beginSendMail();

	/**
	 * 停止发送邮件
	 * 
	 * @methodName stopSendMail
	 * @author chijingjia
	 * @createTime 2018年11月22日 上午9:11:22
	 * @version v1.0
	 */
	void stopSendMail();

	/**
	 * 邮件服务可用
	 * 
	 * @methodName mailUseable
	 * @author chijingjia
	 * @createTime 2018年11月22日 上午9:20:27
	 * @version v1.0
	 * @return
	 */
	boolean mailUseable();

}