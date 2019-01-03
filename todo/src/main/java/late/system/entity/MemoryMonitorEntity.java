/**
 * @description
 */
package late.system.entity;

import late.comm.entity.BaseLoggerEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @projectName todo
 * @packageName late.system.entity
 * @fileName CUPMonitorEntity.java
 * @author chijingjia
 * @createTime :2019年1月2日 下午2:02:19
 * @version: v1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemoryMonitorEntity extends BaseLoggerEntity {
	/**
	 * CPU编号
	 */
	private int cpuNo;
	/**
	 * 使用率
	 */
	private double used;
	/**
	 * 频率
	 */
	private double speed;
}
