/**
 * @description
 */
package late.todo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import late.comm.entity.BaseEntity;
import late.todo.eum.TodoLevel;
import late.todo.eum.TodoMastStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @projectName todo
 * @packageName late.todo.entity
 * @fileName TodoEntity.java
 * @author chijingjia
 * @createTime :2018年10月6日 下午8:23:39
 * @version: v1.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class TodoMastEntity extends BaseEntity{
	/**
	 * 描述
	 */
	private String title;
	/**
	 * 状态
	 */
	private TodoMastStatus status;
	/**
	 * 重要级别 0-9
	 */
	private TodoLevel lvl;
	/**
	 * 附加信息
	 */
	@OneToMany
	private List<TodoAdditionalEntity> addls;
}
