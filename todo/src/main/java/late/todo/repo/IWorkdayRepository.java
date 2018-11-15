/**
 * @description
 */
package late.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import late.todo.entity.WorkdayCalendarEntity;

/**
 * 
 * @projectName todo
 * @packageName late.workday.repo
 * @fileName IWorkdayRepository.java
 * @author chijingjia
 * @createTime :2018年11月13日 下午2:21:14
 * @version: v1.0
 */
public interface IWorkdayRepository extends JpaRepository<WorkdayCalendarEntity, Integer> {
	/**
	 * 根据年月查找记录
	 * @methodName findAllByYearAndMonth
	 * @author chijingjia
	 * @createTime 2018年11月13日 下午5:05:14
	 * @version v1.0
	 * @param year
	 * @param month
	 * @return
	 */
	WorkdayCalendarEntity getOneByYearAndMonth(Integer year, Integer month);
}
