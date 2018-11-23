/**
 * @description
 */
package late.todo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import late.todo.entity.SystemPropertyEntity;

/**
 * 自动生成
 */
public interface ISystemPropertyService {

	/**
	 * 根据对象查找
	 */
	Page<SystemPropertyEntity> findByEntity(SystemPropertyEntity systemPropertyEntity, Pageable pageable);

	/**
	 * 保存
	 * 
	 */
	SystemPropertyEntity insert(SystemPropertyEntity systemPropertyEntity);

	/**
	 * 删除
	 * 
	 */
	void delete(Integer id);

	/**
	 * 根据name获取对象
	 * 
	 * @methodName getByName
	 * @author chijingjia
	 * @createTime 2018年11月23日 上午9:16:26
	 * @version v1.0
	 * @param name
	 * @return
	 */
	SystemPropertyEntity getByName(String name);

}