/**
 * @description
 */
package late.system.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import late.comm.eum.TradeStatus;
import late.system.entity.CPUMonitorEntity;
import late.system.entity.DiskMonitorEntity;
import late.system.entity.MemoryMonitorEntity;
import late.system.entity.SystemMonitorEntity;
import late.system.entity.TradingVolumeEntity;
import late.system.service.ISystemMonitorService;
import late.todo.prop.FcrProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * 自动生成
 */
@Service
@Slf4j
public class SystemMonitorServiceImpl implements ISystemMonitorService {
	@Autowired
	FcrProperties fcrProperties;

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
				log.debug("第" + (i + 1) + "块CPU信息");
				log.debug("CPU的总量MHz:    " + info.getMhz());// CPU的总量MHz
				log.debug("CPU生产商:    " + info.getVendor());// 获得CPU的卖主，如：Intel
				log.debug("CPU类别:    " + info.getModel());// 获得CPU的类别，如：Celeron
				log.debug("CPU缓存数量:    " + info.getCacheSize());// 缓冲存储器数量
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

	@Override
	public List<TradingVolumeEntity> getTradeVolume() {
		List<TradingVolumeEntity> tradeVolumeEntities = new ArrayList<>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(fcrProperties.getUrl(), fcrProperties.getUser(), fcrProperties.getPwd());
			Statement stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder()//
					.append("SELECT lpad(to_char(r.date_sys, 'HH24'),2,'0')||lpad(trunc(to_char(r.date_sys, 'mi')/ 30) * 30, 2, '0'),\n")
					.append("       COUNT(1),\n")//
					.append("       CASE\n")//
					.append("         WHEN r.cod_reply > 10 THEN\n")//
					.append("          'FATAL'\n")//
					.append("         ELSE\n")//
					.append("          'SUCCESS'\n")//
					.append("       END\n")//
			;
			if (fcrProperties.getSchema() == null || fcrProperties.getSchema().isEmpty()) {
				sql.append("  FROM rec_txn_log r\n");
			} else {
				sql.append("  FROM ").append(fcrProperties.getSchema()).append(".rec_txn_log r\n");
			}
			sql.append(" WHERE r.ref_txn_no LIKE\n")//
			;

			if (fcrProperties.getSchema() == null || fcrProperties.getSchema().isEmpty()) {
				sql.append("       (SELECT to_char(t.dat_process, 'yyyyMMdd') || '%' FROM ba_bank_mast t)\n");
			} else {
				sql.append("       (SELECT to_char(t.dat_process, 'yyyyMMdd') || '%' FROM ").append(fcrProperties.getSchema())
						.append(".ba_bank_mast t)\n");
			}
			sql.append(
					" GROUP BY lpad(to_char(r.date_sys, 'HH24'),2,'0')||lpad(trunc(to_char(r.date_sys, 'mi')/ 30) * 30, 2, '0'),\n")//
					.append("          CASE\n")//
					.append("            WHEN r.cod_reply > 10 THEN\n")//
					.append("             'FATAL'\n")//
					.append("            ELSE\n")//
					.append("             'SUCCESS'\n")//
					.append("          END\n")//
					.append(" ORDER BY lpad(to_char(r.date_sys, 'HH24'),2,'0')||lpad(trunc(to_char(r.date_sys, 'mi')/ 30) * 30, 2, '0') ASC")//
			;

			ResultSet rs = stmt.executeQuery(sql.toString());

			for (int i = 0; i < 24 * 2; i++) {
				TradingVolumeEntity tvEntity = new TradingVolumeEntity();
				String timePoint = null;
				timePoint = StringUtils.leftPad(((Integer) (i / 2)).toString(), 2, '0');
				timePoint += i % 2 == 0 ? "00" : "30";
				tvEntity.setTimePoint(timePoint);
				tvEntity.setTradeStatus(TradeStatus.SUCCESS);
				tradeVolumeEntities.add(tvEntity);
			}
			for (int i = 0; i < 24 * 2; i++) {
				TradingVolumeEntity tvEntity = new TradingVolumeEntity();
				tvEntity.setTimePoint(tradeVolumeEntities.get(i).getTimePoint());
				tvEntity.setTradeStatus(TradeStatus.FATAL);
				tvEntity.setCnt(Math.round(30));
				tradeVolumeEntities.add(tvEntity);
			}

			while (rs.next()) {
				// TradingVolumeEntity tvEntity = new TradingVolumeEntity();
				String timePoint = rs.getString(1);
				for (int i = 0; i < tradeVolumeEntities.size(); i++) {
					if (tradeVolumeEntities.get(i).getTimePoint().equals(timePoint)) {
						int idx;
						switch (TradeStatus.valueOf(rs.getString(3))) {
						case SUCCESS:
							idx = i;
							break;
						case FATAL:
							idx = i + 24 * 2;
							break;
						default:
							idx = i;
						}
						tradeVolumeEntities.get(idx).setCnt(rs.getInt(2));
						break;
					}
				}
				// tvEntity.setTimePoint(rs.getString(2));
				// tvEntity.setCnt(rs.getInt(3));
				// tvEntity.setTradeStatus(TradeStatus.valueOf(rs.getString(4)));
				// tradeVolumeEntities.add(tvEntity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return tradeVolumeEntities;
	}

}
