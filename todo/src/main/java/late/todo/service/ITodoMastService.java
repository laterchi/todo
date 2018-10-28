/**
 * @description
 */
package late.todo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import late.todo.entity.TodoMastEntity;
import late.todo.eum.TodoMastStatus;

/**
 * 
 * @projectName todo
 * @packageName late.todo.service
 * @fileName ITodoMastService.java
 * @author chijingjia
 * @createTime :2018年10月7日 下午1:37:51
 * @version: v1.0
 */
public interface ITodoMastService {

	/**
	 * 根据对象查找
	 * 
	 * @methodName findByEntity
	 * @author chijingjia
	 * @createTime 2018年10月7日 下午1:39:21
	 * @version v1.0
	 * @param todoMastEntity
	 * @param pageable
	 * @return
	 */
	Page<TodoMastEntity> findByEntity(TodoMastEntity todoMastEntity, Pageable pageable);

	/**
	 * 保存
	 * 
	 * @methodName insert
	 * @author chijingjia
	 * @createTime 2018年10月7日 下午1:40:46
	 * @version v1.0
	 * @param todoMastEntity
	 * @return
	 */
	TodoMastEntity insert(TodoMastEntity todoMastEntity);

	/**
	 * 保存
	 * 
	 * @methodName insert
	 * @author chijingjia
	 * @createTime 2018年10月7日 下午1:40:46
	 * @version v1.0
	 * @param todoMastEntity
	 * @return
	 */
	void delete(String id);

	/**
	 * 升降级操作
	 * 
	 * @methodName grade
	 * @author chijingjia
	 * @createTime 2018年10月7日 下午3:57:47
	 * @version v1.0
	 * @param id
	 *            主键
	 * @param type
	 *            0-升级 1-降级
	 */
	void grade(String id, char type);

	/**
	 * 变更状态
	 * 
	 * @methodName changeStatus
	 * @author chijingjia
	 * @createTime 2018年10月7日 下午4:17:07
	 * @version v1.0
	 * @param id
	 */
	void changeStatus(String id, TodoMastStatus status);

	/**
	 * 维护待办信息
	 * 
	 * @methodName modifyTodoMast
	 * @author chijingjia
	 * @createTime 2018年10月7日 下午9:40:38
	 * @version v1.0
	 * @param todoMastEntity
	 */
	void modifyTodoMast(TodoMastEntity todoMastEntity);

	/**
	 * 任务完成
	 * 
	 * @methodName process
	 * @author chijingjia
	 * @createTime 2018年10月8日 下午1:22:17
	 * @version v1.0
	 * @param id
	 */
	void complete(String id);

	/**
	 * 重启任务
	 * 
	 * @methodName restart
	 * @author chijingjia
	 * @createTime 2018年10月8日 下午6:02:59
	 * @version v1.0
	 * @param id
	 */
	void restart(String id);

	/**
	 * 任务挂起
	 * 
	 * @methodName suspend
	 * @author chijingjia
	 * @createTime 2018年10月9日 下午4:39:41
	 * @version v1.0
	 * @param id
	 */
	void suspend(String id);

	/**
	 * 添加附加信息
	 * 
	 * @methodName addAddl
	 * @author chijingjia
	 * @createTime 2018年10月14日 下午6:29:46
	 * @version v1.0
	 * @param todoMast
	 */
	void addAddl(TodoMastEntity todoMast);

}
