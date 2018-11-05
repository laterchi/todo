/**
 * @description
 */
package late.todo.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import late.comm.backup.BackupConstants;
import late.comm.entity.BaseEntity;
import late.comm.utils.DateUtils;
import late.todo.MyTodoStarter;
import late.todo.service.IDataBackupService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @projectName todo
 * @packageName late.todo.service.impl
 * @fileName DataBackupServiceImpl.java
 * @author chijingjia
 * @createTime :2018年10月31日 上午9:05:25
 * @version: v1.0
 */
@Slf4j
@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DataBackupServiceImpl implements IDataBackupService {

	@Override
	public void imp(ApplicationContext applicationContext) {
		log.info("导入开始");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			File impFile = new File(BackupConstants.EXPORT_FOLDER);
			File[] file = impFile.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith(BackupConstants.IMPORT_PREFIX);
				}
			});
			if (file != null && file.length > 0) {
				impFile = file[0];
			} else {
				log.info("无导入文件，忽略导入");
				return;
			}
			fr = new FileReader(impFile);
			log.debug("导入文件：" + impFile);
			br = new BufferedReader(fr);
			String line = null;
			String repoName = null;
			Class entityClazz = null;
			List<BaseEntity> data = null;
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("{")) {
					if (data != null && data.size() != 0) {
						saveData(data, (JpaRepository<BaseEntity, String>) applicationContext.getBean(repoName));
					}
					data = new ArrayList<>();
					if (line.endsWith("#")) {
						continue;
					}
					repoName = line.split("#")[0];
					String clazzName = line.split("#")[1];
					entityClazz = Class.forName(clazzName);
				} else {
					BaseEntity entity = (BaseEntity) JSONObject.parseObject(line, entityClazz);
					data.add(entity);
				}
			}
			if (data.size() != 0) {
				saveData(data, (JpaRepository<BaseEntity, String>) applicationContext.getBean(repoName));
			}
			impFile.delete();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			log.info("导入结束");
		}
	}

	@Override
	public void backup(ApplicationContext applicationContext) {
		log.info("备份开始");
		Map<String, JpaRepository> repoMap = applicationContext.getBeansOfType(JpaRepository.class);
		File backupFile = new File(BackupConstants.EXPORT_FOLDER + File.separator + BackupConstants.BACKUP_PREFIX
				+ DateUtils.formatDateTimeStr(new Date()));
		if (!backupFile.getParentFile().exists()) {
			backupFile.getParentFile().mkdirs();
		}
		File[] outFiles = backupFile.getParentFile().listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith(BackupConstants.BACKUP_PREFIX);
			}
		});
		if (outFiles != null) {
			for (File file : outFiles) {
				file.delete();
			}
		}
		for (String repoName : repoMap.keySet()) {
			expData(backupFile, repoName, repoMap.get(repoName));
		}
		log.info("备份结束");
	}

	@Override
	public void export(String repoName) {
		log.info("导出开始");
		Class<?> repoClazz;
		try {
			repoClazz = Class.forName(repoName);
		} catch (ClassNotFoundException e) {
			log.error("导出异常");
			e.printStackTrace();
			return;
		}
		JpaRepository<BaseEntity, Integer> repo = (JpaRepository<BaseEntity, Integer>) MyTodoStarter.getBeanOfType(repoClazz)
				.get(repoClazz.getSimpleName());
		File backupFile = new File(BackupConstants.EXPORT_FOLDER + File.separator + BackupConstants.EXPORT_PREFIX
				+ DateUtils.formatDateTimeStr(new Date()));
		if (!backupFile.getParentFile().exists()) {
			backupFile.getParentFile().mkdirs();
		}

		expData(backupFile, repoName, repo);
		log.info("导出结束");
	}

	/**
	 * 导出数据
	 * 
	 * @methodName expData
	 * @author chijingjia
	 * @createTime 2018年10月31日 上午9:08:11
	 * @version v1.0
	 * @param outFile
	 * @param repoName
	 * @param repository
	 */
	public static void expData(File outFile, String repoName, JpaRepository<BaseEntity, Integer> repository) {
		Iterable<BaseEntity> allRecord = null;
		FileOutputStream ops = null;
		FileChannel channel = null;
		try {
			allRecord = repository.findAll();
			ops = new FileOutputStream(outFile, true);
			channel = ops.getChannel();
			ByteBuffer buffer = null;
			byte[] repoClsName = (repoName + "#").getBytes();
			buffer = ByteBuffer.allocate(repoClsName.length);
			buffer.put(repoClsName);
			buffer.flip();
			channel.write(buffer);
			boolean getClsName = true;
			for (BaseEntity entity : allRecord) {
				String clsName = null;
				if (getClsName) {
					clsName = entity.getClass().getName();
					getClsName = false;
				} else {
					clsName = "";
				}
				entity.setId(null);
				byte[] entitBuffer = (clsName + '\n' + JSONObject.toJSONString(entity)).getBytes();
				buffer = ByteBuffer.allocate(entitBuffer.length);
				buffer.put(entitBuffer);
				buffer.flip();
				channel.write(buffer);
			}
			if (getClsName) {
				byte[] entitBuffer = "\n".getBytes();
				buffer = ByteBuffer.allocate(entitBuffer.length);
				buffer.put(entitBuffer);
				buffer.flip();
				channel.write(buffer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				channel.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			try {
				ops.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 保存数据
	 * 
	 * @methodName saveData
	 * @author chijingjia
	 * @createTime 2018年10月14日 下午6:29:01
	 * @version v1.0
	 * @param id
	 */
	public static void saveData(List<BaseEntity> data, JpaRepository<BaseEntity, String> repo) {
		log.debug("保存数据", "保存内容", repo.getClass(), "记录数", data.size());
		for (BaseEntity entity : data) {
			repo.save(entity);
		}
	}

}
