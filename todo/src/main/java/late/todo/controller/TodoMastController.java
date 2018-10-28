/**
 * @description
 */
package late.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import late.todo.entity.GlobalTimeEntity;
import late.todo.entity.TodoMastEntity;
import late.todo.eum.TodoMastStatus;
import late.todo.service.ITodoMastService;

/**
 * 
 * @projectName todo
 * @packageName late.todo.controller
 * @fileName TodoController.java
 * @author chijingjia
 * @createTime :2018年10月6日 下午8:23:24
 * @version: v1.0
 */
@RestController
@RequestMapping(value = "/todo")
@Api(value="aaaa")
public class TodoMastController {

	@Autowired
	private ITodoMastService todoMastService;

	/**
	 * 查找分页记录
	 * 
	 * @methodName findTodoMast
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午8:54:19
	 * @version v1.0
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/getAll", method = { RequestMethod.GET })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") })
	public Page<TodoMastEntity> findTodoMast(TodoMastEntity todoMastEntity, Pageable pageable) {
		return todoMastService.findByEntity(todoMastEntity, pageable);
	}

	/**
	 * 查找分页记录
	 * 
	 * @methodName findTodoMast
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午8:54:19
	 * @version v1.0
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addTodoMast(@RequestBody TodoMastEntity todoMastEntity,GlobalTimeEntity value) {
		todoMastService.insert(todoMastEntity);
	}

	/**
	 * 查找分页记录
	 * 
	 * @methodName findTodoMast
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午8:54:19
	 * @version v1.0
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteTodoMast(@PathVariable String id) {
		todoMastService.delete(id);
	}

	/**
	 * 查找分页记录
	 * 
	 * @methodName findTodoMast
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午8:54:19
	 * @version v1.0
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/upgrade/{id}/{type}", method = RequestMethod.PUT)
	public void gradeTodoMast(@PathVariable String id, @PathVariable Character type) {
		todoMastService.grade(id, type);
	}

	/**
	 * 查找分页记录
	 * 
	 * @methodName findTodoMast
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午8:54:19
	 * @version v1.0
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/process", method = RequestMethod.PUT)
	public void processTodoMast(@RequestBody TodoMastEntity todoMastEntity) {
		todoMastService.changeStatus(todoMastEntity.getId(), TodoMastStatus.PROCESS);
	}

	/**
	 * 查找分页记录
	 * 
	 * @methodName findTodoMast
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午8:54:19
	 * @version v1.0
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public void modifyTodoMast(@RequestBody TodoMastEntity todoMastEntity) {
		todoMastService.modifyTodoMast(todoMastEntity);
	}

	/**
	 * 查找分页记录
	 * 
	 * @methodName findTodoMast
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午8:54:19
	 * @version v1.0
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/complete/{id}", method = RequestMethod.PUT)
	public void completeTask(@PathVariable String id) {
		todoMastService.complete(id);
	}

	/**
	 * 查找分页记录
	 * 
	 * @methodName findTodoMast
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午8:54:19
	 * @version v1.0
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/restart/{id}", method = RequestMethod.PUT)
	public void restartTask(@PathVariable String id) {
		todoMastService.restart(id);
	}

	/**
	 * 查找分页记录
	 * 
	 * @methodName findTodoMast
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午8:54:19
	 * @version v1.0
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/suspend/{id}", method = RequestMethod.PUT)
	public void suspendTask(@PathVariable String id) {
		todoMastService.suspend(id);
	}

	/**
	 * 创建附加信息
	 * 
	 * @methodName addAddl
	 * @author chijingjia
	 * @createTime 2018年10月14日 下午6:29:01
	 * @version v1.0
	 * @param id
	 */
	@RequestMapping(value = "/addAddl", method = RequestMethod.POST)
	public void addAddl(@RequestBody TodoMastEntity todoMast) {
		todoMastService.addAddl(todoMast);
	}

}
