/**
 * @description
 */
package late.todo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import late.todo.entity.SystemPropertyEntity;
import late.todo.repo.ISystemPropertyRepository;
import late.todo.service.ISystemPropertyService;

/**
 * 自动生成
 */
@Service
public class SystemPropertyServiceImpl implements ISystemPropertyService {
	@Autowired
	ISystemPropertyRepository systemPropertyRepository;

	@Override
	public Page<SystemPropertyEntity> findByEntity(SystemPropertyEntity systemPropertyEntity, Pageable pageable) {
		return systemPropertyRepository.findAll(pageable);
	}

	@Override
	public SystemPropertyEntity insert(SystemPropertyEntity systemPropertyEntity) {
		return systemPropertyRepository.save(systemPropertyEntity);
	}

	@Override
	public void delete(Integer id) {
		systemPropertyRepository.deleteById(id);
	}

	@Override
	public SystemPropertyEntity getByName(String name) {
		return systemPropertyRepository.findOneByName(name);
	}

}
