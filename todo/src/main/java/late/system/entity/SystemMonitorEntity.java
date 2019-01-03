/**
 * @description
 */
package late.system.entity;

import java.util.List;

import late.comm.entity.BaseLoggerEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @projectName todo
 * @packageName late.system.entity
 * @fileName SystemMonitorEntity.java
 * @author chijingjia
 * @createTime :2019年1月2日 下午1:57:25
 * @version: v1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemMonitorEntity extends BaseLoggerEntity {
	/**
	 * CPU信息
	 */
	List<CPUMonitorEntity> cpuMonitors;
	/**
	 * 内存信息
	 */
	List<MemoryMonitorEntity> memoryMonitors;
	/**
	 * 磁盘信息
	 */
	List<DiskMonitorEntity> diskMonitors;
}
