/**
 * @description
 */
package late.todo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import late.comm.entity.MaintainEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @projectName todo
 * @packageName late.todo.entity
 * @fileName SystemPropertyEntity.java
 * @author chijingjia
 * @createTime :2018年11月23日 上午9:05:13
 * @version: v1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class SystemPropertyEntity extends MaintainEntity {
	/**
	 * 配置名
	 */
	@Column(length = 20, nullable = false, unique = true)
	private String name;
	/**
	 * 配置值
	 */
	@Column(length = 20, nullable = false)
	private String value;
	/**
	 * 描述
	 */
	@Column(length = 200, nullable = true)
	private String desc;
}
