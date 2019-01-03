/**
 * @description
 */
package late.comm.template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.core.io.Resource;

import late.comm.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @projectName todo
 * @packageName late.comm.template
 * @fileName RepositoryGenerator.java
 * @author chijingjia
 * @createTime :2018年11月21日 下午1:37:46
 * @version: v1.0
 */
@Slf4j
public class RepositoryGenerator extends CodeGenerator {
	private static final String TEMPLATE_URL = "codetemplates/Repository.template";
	private static final Map<String, String> REPLACE_PARAM = new HashMap<>();

	public static void generate(Class<? extends BaseEntity> clazz) {
		FileReader fr = null;
		BufferedReader br = null;
		OutputStream ops = null;
		try {
			String packageName = clazz.getPackage().getName();
			packageName = packageName.substring(0, packageName.length() - ".entity".length()) + ".repo";
			REPLACE_PARAM.put("PACKAGE_NAME", packageName);
			String nickName = clazz.getSimpleName();
			nickName = nickName.substring(0, nickName.length() - "Entity".length());
			REPLACE_PARAM.put("NICK_NAME", nickName);
			String nickNameLower = null;
			nickNameLower = nickName.substring(0, 1).toLowerCase() + nickName.substring(1, nickName.length());
			REPLACE_PARAM.put("NICK_NAME_LOWER", nickNameLower);
			Resource resource = resourceLoader.getResource("classpath:" + TEMPLATE_URL);

			String outputFileName = new StringBuilder("src/main/java/")//
					.append(packageName.replaceAll("\\.", "/")).append(File.separator)//
					.append("I").append(nickName).append("Repository")//
					.append(".java")//
					.toString();
			File outFile = new File(outputFileName);
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}

			fr = new FileReader(resource.getFile());
			br = new BufferedReader(fr);
			String line;
			StringBuilder codeText = new StringBuilder();
			while ((line = br.readLine()) != null) {
				if (line.indexOf("$") > -1) {
					for (Entry<String, String> entry : REPLACE_PARAM.entrySet()) {
						line = line.replaceAll("\\$\\{" + entry.getKey() + "\\}", entry.getValue());
					}
				}
				codeText.append(line).append("\n");
			}
			ops = new FileOutputStream(outputFileName, override);
			ops.write(codeText.toString().getBytes());
			ops.flush();
			log.info("生成文件" + outputFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			try {
				br.close();
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

}
