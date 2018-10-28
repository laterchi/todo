/**
 * @description
 */
package late.todo.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import late.comm.eum.RecordStatus;
import late.comm.utils.IntegerUtils;
import late.todo.entity.GlobalTimeEntity;
import late.todo.entity.QGlobalTimeEntity;
import late.todo.repo.IGlobalTimeRepository;
import late.todo.service.IGlobalTimeService;

/**
 * 
 * @projectName todo
 * @packageName late.todo.service.impl
 * @fileName GlobalTimeService.java
 * @author chijingjia
 * @createTime :2018年10月8日 下午9:56:55
 * @version: v1.0
 */
@Service
public class GlobalTimeServiceImpl implements IGlobalTimeService {
	@Autowired
	IGlobalTimeRepository globalTimeRepository;

	@Override
	public GlobalTimeEntity insert(GlobalTimeEntity entity) {
		entity.setStatus(RecordStatus.NORMAL);
		return globalTimeRepository.save(entity);
	}

	@Override
	public Page<GlobalTimeEntity> findByEntity(GlobalTimeEntity entity, Pageable pageable) {
		QGlobalTimeEntity qTodoMastEntity = QGlobalTimeEntity.globalTimeEntity;
		Predicate pre = qTodoMastEntity.id.isNotNull();
		if (IntegerUtils.isNotEmpty(entity.getId())) {
			pre = ExpressionUtils.and(pre, qTodoMastEntity.id.eq(entity.getId()));
		}
		if (StringUtils.isNotEmpty(entity.getName())) {
			pre = ExpressionUtils.and(pre, qTodoMastEntity.name.like("%" + entity.getName() + "%"));
		}
		if (entity.getStatus() != null) {
			pre = ExpressionUtils.and(pre, qTodoMastEntity.status.eq(entity.getStatus()));
		}
		if (entity.getType() != null) {
			pre = ExpressionUtils.and(pre, qTodoMastEntity.type.eq(entity.getType()));
		}
		return globalTimeRepository.findAll(pre, pageable);
	}

}
