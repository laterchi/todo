/**
 * @description
 */
package late.todo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import late.comm.entity.MaintainEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @projectName todo
 * @packageName late.workday.entity
 * @fileName WorkdayCalendar.java
 * @author chijingjia
 * @createTime :2018年11月6日 上午10:13:42
 * @version: v1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "year", "month" }) })
public class WorkdayCalendarEntity extends MaintainEntity {
	/**
	 * 年
	 */
	@Column(precision = 4, scale = 0)
	private int year;
	/**
	 * 月
	 */
	@Column(precision = 4, scale = 0)
	private int month;
	/**
	 * 日
	 */
	@Column(length = 31)
	private char[] day;

}
