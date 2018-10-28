/**
 * @description
 */
package late.todo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import late.todo.entity.GlobalTimeEntity;

/**
 * 
 * @projectName todo
 * @packageName late.todo.service
 * @fileName IGlobalTimeService.java
 * @author chijingjia
 * @createTime :2018年10月8日 下午9:56:34
 * @version: v1.0
 */
public interface IGlobalTimeService {
	/**
	 * 保存时间信息
	 * 
	 * @methodName insert
	 * @author chijingjia
	 * @createTime 2018年10月8日 下午9:57:34
	 * @version v1.0
	 * @param entity
	 * @return
	 */
	GlobalTimeEntity insert(GlobalTimeEntity entity);

	/**
	 * 查询分页信息
	 * 
	 * @methodName findByEntity
	 * @author chijingjia
	 * @createTime 2018年10月8日 下午10:03:16
	 * @version v1.0
	 * @param entity
	 * @param pageable
	 * @return
	 */
	Page<GlobalTimeEntity> findByEntity(GlobalTimeEntity entity, Pageable pageable);

}
