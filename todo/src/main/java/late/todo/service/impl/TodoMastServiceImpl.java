/**
 * @description
 */
package late.todo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import late.comm.utils.IntegerUtils;
import late.todo.entity.QTodoMastEntity;
import late.todo.entity.TodoAdditionalEntity;
import late.todo.entity.TodoMastEntity;
import late.todo.eum.TodoLevel;
import late.todo.eum.TodoMastStatus;
import late.todo.repo.ITodoMastRepository;
import late.todo.service.IDataBackupService;
import late.todo.service.ITodoMastService;

/**
 * 
 * @projectName todo
 * @packageName late.todo.service
 * @fileName TodoMastServiceImpl.java
 * @author chijingjia
 * @createTime :2018年10月7日 下午1:38:17
 * @version: v1.0
 */
@Service
public class TodoMastServiceImpl implements ITodoMastService {

	@Autowired
	private ITodoMastRepository todoMastRepository;
	@Autowired(required = false)
	private IDataBackupService backupService;

	@Override
	public Page<TodoMastEntity> findByEntity(TodoMastEntity todoMastEntity, Pageable pageable) {
		QTodoMastEntity qTodoMastEntity = QTodoMastEntity.todoMastEntity;
		Predicate pre = qTodoMastEntity.id.isNotNull();
		if (IntegerUtils.isNotEmpty(todoMastEntity.getId())) {
			pre = ExpressionUtils.and(pre, qTodoMastEntity.id.eq(todoMastEntity.getId()));
		}
		if (StringUtils.isNotEmpty(todoMastEntity.getTitle())) {
			pre = ExpressionUtils.and(pre, qTodoMastEntity.title.like("%" + todoMastEntity.getTitle() + "%"));
		}
		if (todoMastEntity.getStatus() != null) {
			pre = ExpressionUtils.and(pre, qTodoMastEntity.status.eq(todoMastEntity.getStatus()));
		}
		return todoMastRepository.findAll(pre, pageable);
	}

	@Override
	public TodoMastEntity insert(TodoMastEntity todoMastEntity) {
		todoMastEntity.setStatus(TodoMastStatus.NEW);
		todoMastEntity.setLvl(TodoLevel.NORMAL);
		return todoMastRepository.save(todoMastEntity);
	}

	@Override
	public void delete(Integer id) {
		todoMastRepository.deleteById(id);
	}

	@Override
	public void grade(Integer id, char type) {
		TodoMastEntity todoMastEntity = todoMastRepository.findById(id).get();
		todoMastEntity.setLvl(type == '0' ? todoMastEntity.getLvl().nextLvl() : todoMastEntity.getLvl().lastLvl());
		todoMastRepository.save(todoMastEntity);
	}

	@Override
	public void changeStatus(Integer id, TodoMastStatus status) {
		TodoMastEntity todoMastEntity = todoMastRepository.findById(id).get();
		todoMastEntity.setStatus(status);
		// Query query = new
		// Query(Criteria.where(QTodoMastEntity.todoMastEntity.id.getMetadata().getName()).is(id));
		// Update update =
		// Update.update(QTodoMastEntity.todoMastEntity.status.getMetadata().getName(),
		// status);
		// todoMastEntity = mongoTemplate.findAndModify(query, update,
		// TodoMastEntity.class);
		todoMastRepository.save(todoMastEntity);
	}

	@Override
	public void modifyTodoMast(TodoMastEntity todoMastEntity) {
		todoMastRepository.save(todoMastEntity);
	}

	@Override
	public void complete(Integer id) {
		TodoMastEntity todoMastEntity = todoMastRepository.findById(id).get();
		todoMastEntity.setStatus(TodoMastStatus.COMPLETE);
		todoMastRepository.save(todoMastEntity);
	}

	@Override
	public void restart(Integer id) {
		TodoMastEntity todoMastEntity = todoMastRepository.findById(id).get();
		todoMastEntity.setStatus(TodoMastStatus.PROCESS);
		todoMastRepository.save(todoMastEntity);
	}

	@Override
	public void suspend(Integer id) {
		TodoMastEntity todoMastEntity = todoMastRepository.findById(id).get();
		todoMastEntity.setStatus(TodoMastStatus.SUSPENDED);
		todoMastRepository.save(todoMastEntity);
	}

	@Override
	public void addAddl(TodoMastEntity todoMast) {
		TodoMastEntity todoMastInStorage = todoMastRepository.findById(todoMast.getId()).get();
		List<TodoAdditionalEntity> addls = todoMastInStorage.getAddls();
		int maxSeq = 0;
		if (addls == null) {
			addls = new ArrayList<>();
			todoMastInStorage.setAddls(addls);
		}
		for (TodoAdditionalEntity addlEntity : addls) {
			if (maxSeq < addlEntity.getSeqno()) {
				maxSeq = addlEntity.getSeqno();
			}
		}

		for (TodoAdditionalEntity addlEntity : todoMast.getAddls()) {
			addlEntity.setSeqno(++maxSeq);
			addls.add(addlEntity);
		}

		todoMastRepository.save(todoMastInStorage);
	}

	@Override
	public void export() {
		backupService.export(ITodoMastRepository.class.getName());
	}

}
