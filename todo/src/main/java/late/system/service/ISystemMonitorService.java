/**
 * @description
 */
package late.system.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import late.system.entity.CPUMonitorEntity;
import late.system.entity.DiskMonitorEntity;
import late.system.entity.MemoryMonitorEntity;
import late.system.entity.SystemMonitorEntity;
import late.system.entity.TradingVolumeEntity;

/**
 * 自动生成
 */
public interface ISystemMonitorService {

	/**
	 * 根据对象查找
	 */
	Page<SystemMonitorEntity> findByEntity(SystemMonitorEntity systemMonitorEntity, Pageable pageable);

	/**
	 * 保存
	 * 
	 */
	SystemMonitorEntity insert(SystemMonitorEntity systemMonitorEntity);

	/**
	 * 删除
	 * 
	 */
	void delete(Integer id);

	/**
	 * 获取系统信息
	 * 
	 * @methodName getSystemInfo
	 * @author chijingjia
	 * @createTime 2019年1月2日 下午2:55:16
	 * @version v1.0
	 * @return
	 */
	SystemMonitorEntity getSystemInfo();

	/**
	 * 获取CPU信息
	 * 
	 * @methodName getCPUInfo
	 * @author chijingjia
	 * @createTime 2019年1月2日 下午2:38:37
	 * @version v1.0
	 * @return
	 */
	List<CPUMonitorEntity> getCPUInfo();

	/**
	 * 获取内存信息
	 * 
	 * @methodName getCPUInfo
	 * @author chijingjia
	 * @createTime 2019年1月2日 下午2:38:37
	 * @version v1.0
	 * @return
	 */
	List<MemoryMonitorEntity> getMemInfo();

	/**
	 * 获取硬盘信息
	 * 
	 * @methodName getCPUInfo
	 * @author chijingjia
	 * @createTime 2019年1月2日 下午2:38:37
	 * @version v1.0
	 * @return
	 */
	List<DiskMonitorEntity> getDiskInfo();

	/**
	 * 统计交易量
	 * 
	 * @methodName getTradeVolume
	 * @author chijingjia
	 * @createTime 2019年1月3日 上午10:45:23
	 * @version v1.0
	 * @return
	 */
	List<TradingVolumeEntity> getTradeVolume();

}
