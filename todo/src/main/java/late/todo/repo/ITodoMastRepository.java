/**
 * @description
 */
package late.todo.repo;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import late.todo.entity.TodoMastEntity;

/**
 * 
 * @projectName todo
 * @packageName late.todo.repo
 * @fileName TodoMastReposity.java
 * @author chijingjia
 * @createTime :2018年10月6日 下午8:44:25
 * @version: v1.0
 */
public interface ITodoMastRepository extends QuerydslPredicateExecutor<TodoMastEntity>, CrudRepository<TodoMastEntity, Integer> {

}
