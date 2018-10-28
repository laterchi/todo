/**
 * @description
 */
package late.todo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import late.todo.entity.GlobalTimeEntity;

/**
 * 
 * @projectName todo
 * @packageName late.todo.repo
 * @fileName IGlobalTimeRepository.java
 * @author chijingjia
 * @createTime :2018年10月8日 下午9:58:14
 * @version: v1.0
 */
public interface IGlobalTimeRepository
		extends MongoRepository<GlobalTimeEntity, String>, QuerydslPredicateExecutor<GlobalTimeEntity> {

}
