/**
 * @description
 */
package late.todo.entity;

import late.comm.entity.MaintainMongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 附加信息
 * 
 * @projectName todo
 * @packageName late.todo.entity
 * @fileName TodoAdditionalEntity.java
 * @author chijingjia
 * @createTime :2018年10月14日 下午5:36:56
 * @version: v1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TodoAdditionalEntity extends MaintainMongoEntity {
	/**
	 * 序号
	 */
	private int seqno;
	/**
	 * 信息描述
	 */
	private String desc;
}
