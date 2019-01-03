/**
 * @description
 */
package late.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import late.system.entity.CPUMonitorEntity;
import late.system.entity.DiskMonitorEntity;
import late.system.entity.MemoryMonitorEntity;
import late.system.entity.SystemMonitorEntity;
import late.system.service.ISystemMonitorService;

/**
 * 自动生成
 */
@Service
public class SystemMonitorServiceImpl implements ISystemMonitorService {

	@Override
	public Page<SystemMonitorEntity> findByEntity(SystemMonitorEntity systemMonitorEntity, Pageable pageable) {
		return null;
	}

	@Override
	public SystemMonitorEntity insert(SystemMonitorEntity systemMonitorEntity) {
		return null;
	}

	@Override
	public void delete(Integer id) {
		return;
	}

	@Override
	public SystemMonitorEntity getSystemInfo() {
		SystemMonitorEntity systemMonitorEntity = new SystemMonitorEntity();
		systemMonitorEntity.setCpuMonitors(getCPUInfo());
		systemMonitorEntity.setMemoryMonitors(getMemInfo());
		systemMonitorEntity.setDiskMonitors(getDiskInfo());
		return systemMonitorEntity;
	}

	@Override
	public List<CPUMonitorEntity> getCPUInfo() {
		List<CPUMonitorEntity> monitorEntities = new ArrayList<>();
		try {
			Sigar sigar = new Sigar();
			CpuInfo[] infos = sigar.getCpuInfoList();
			CpuPerc[] cpuList = null;
			cpuList = sigar.getCpuPercList();
			for (int i = 0; i < cpuList.length; i++) {// 不管是单块CPU还是多CPU都适用
				CpuInfo info = infos[i];
				CpuPerc perc = cpuList[i];
				System.out.println("第" + (i + 1) + "块CPU信息");
				System.out.println("CPU的总量MHz:    " + info.getMhz());// CPU的总量MHz
				System.out.println("CPU生产商:    " + info.getVendor());// 获得CPU的卖主，如：Intel
				System.out.println("CPU类别:    " + info.getModel());// 获得CPU的类别，如：Celeron
				System.out.println("CPU缓存数量:    " + info.getCacheSize());// 缓冲存储器数量
				CPUMonitorEntity cpuMonitorEntity = new CPUMonitorEntity();
				cpuMonitorEntity.setCpuNo(i);
				cpuMonitorEntity.setUsed(perc.getCombined());
				cpuMonitorEntity.setSpeed(info.getMhz());
				monitorEntities.add(cpuMonitorEntity);
			}
		} catch (SigarException e) {
			e.printStackTrace();
		}
		return monitorEntities;
	}

	@Override
	public List<MemoryMonitorEntity> getMemInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiskMonitorEntity> getDiskInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
