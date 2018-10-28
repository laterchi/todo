package late.todo;

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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alibaba.fastjson.JSONObject;

import late.comm.backup.BackupConstants;
import late.comm.entity.BaseMongoEntity;
import late.comm.utils.DateUtils;

/**
 * 待办内容
 * 
 * @projectName todo
 * @packageName late.todo
 * @fileName MyTodoStarter.java
 * @author chijingjia
 * @createTime :2018年10月6日 下午7:16:47
 * @version: v1.0
 */
@SpringBootApplication
@EnableScheduling
@Configuration
@EnableMongoAuditing
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MyTodoStarter {
	/**
	 * 启动方法
	 * 
	 * @methodName main
	 * @author chijingjia
	 * @createTime 2018年10月6日 下午7:17:03
	 * @version v1.0
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(MyTodoStarter.class, args);

		imp(applicationContext);

		backup(applicationContext);
	}

	/**
	 * 导入
	 * 
	 * @methodName imp
	 * @author chijingjia
	 * @createTime 2018年10月28日 下午7:59:06
	 * @version v1.0
	 * @param applicationContext
	 */
	private static void imp(ApplicationContext applicationContext) {
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
			}
			fr = new FileReader(impFile);
			br = new BufferedReader(fr);
			String line = null;
			String repoName = null;
			List<BaseMongoEntity> data = null;
			while ((line = br.readLine()) != null) {
				if (repoName == null) {
					data = new ArrayList<>();
				}
				if (!line.startsWith("{")) {
					if (data != null) {
						saveData(data, (CrudRepository<BaseMongoEntity, String>) applicationContext.getBean(repoName));
					}
					repoName = line;
					data = new ArrayList<>();
				} else {
					BaseMongoEntity entity = (BaseMongoEntity) JSONObject.parse(line);
					data.add(entity);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 备份
	 * 
	 * @methodName backup
	 * @author chijingjia
	 * @createTime 2018年10月28日 下午7:56:05
	 * @version v1.0
	 */
	public static void backup(ApplicationContext applicationContext) {
		Map<String, MongoRepository> repoMap = applicationContext.getBeansOfType(MongoRepository.class);
		File backupFile = new File(BackupConstants.EXPORT_FOLDER + File.separator + BackupConstants.BACKUP_PREFIX
				+ DateUtils.formatDateTimeStr(new Date()));
		if (!backupFile.getParentFile().exists()) {
			backupFile.getParentFile().mkdirs();
		}
		File[] outFiles = backupFile.listFiles(new FilenameFilter() {
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
	}

	/**
	 * 创建附加信息
	 * 
	 * @methodName addAddl
	 * @author chijingjia
	 * @createTime 2018年10月14日 下午6:29:01
	 * @version v1.0
	 * @param id
	 */
	public static void expData(File outFile, String repoName, MongoRepository repository) {
		Iterable<BaseMongoEntity> allRecord = null;
		FileOutputStream ops = null;
		FileChannel channel = null;
		try {
			allRecord = repository.findAll();
			ops = new FileOutputStream(outFile, true);
			channel = ops.getChannel();
			ByteBuffer buffer = null;
			byte[] repoClsName = (repoName + "\n").getBytes();
			buffer = ByteBuffer.allocate(repoClsName.length);
			buffer.put(repoClsName);
			buffer.flip();
			channel.write(buffer);
			for (BaseMongoEntity entity : allRecord) {
				entity.setId(null);
				byte[] entitBuffer = (JSONObject.toJSONString(entity) + "\n").getBytes();
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
	 * 创建附加信息
	 * 
	 * @methodName addAddl
	 * @author chijingjia
	 * @createTime 2018年10月14日 下午6:29:01
	 * @version v1.0
	 * @param id
	 */
	public static void saveData(List<BaseMongoEntity> data, CrudRepository<BaseMongoEntity, String> repo) {
		for (BaseMongoEntity entity : data) {
			repo.save(entity);
		}
	}

}
