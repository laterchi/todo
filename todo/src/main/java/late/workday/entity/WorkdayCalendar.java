/**
 * @description
 */
package late.workday.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

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
@EqualsAndHashCode(callSuper=false)
@Entity
public class WorkdayCalendar extends MaintainEntity{
	/**
	 * 年
	 */
	@Column(precision=4,scale=0)
	private int year;
	/**
	 * 月
	 */
	@Column(precision=4,scale=0)
	private int month;
	/**
	 * 日
	 */
	@Column(length=31)
	private String day;
	
}
