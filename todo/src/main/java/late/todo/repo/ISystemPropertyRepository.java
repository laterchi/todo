/**
 * @description
 */
package late.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import late.todo.entity.SystemPropertyEntity;

/**
 * 自动生成
 */
public interface ISystemPropertyRepository extends JpaRepository<SystemPropertyEntity, Integer> {
	SystemPropertyEntity findOneByName(String name);
}