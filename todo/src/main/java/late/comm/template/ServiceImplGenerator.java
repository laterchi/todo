/**
 * @description
 */
package late.comm.template;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.core.io.Resource;

import late.comm.entity.BaseEntity;

/**
 * 
 * @projectName todo
 * @packageName late.comm.template
 * @fileName ServiceImplGenerator.java
 * @author chijingjia
 * @createTime :2018年11月21日 下午1:37:07
 * @version: v1.0
 */
public class ServiceImplGenerator extends CodeGenerator {
	private static final String TEMPLATE_URL = "codetemplates/ServiceImpl.template";
	private static final Map<String, String> REPLACE_PARAM = new HashMap<>();

	public static void generate(Class<? extends BaseEntity> clazz) {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			String packageName = clazz.getPackage().getName();
			REPLACE_PARAM.put("PACKAGE_NAME", packageName.substring(0, packageName.length() - ".entity".length()) + ".service.impl");
			String nickName = clazz.getSimpleName();
			nickName = nickName.substring(0, nickName.length() - "Entity".length());
			REPLACE_PARAM.put("NICK_NAME", nickName);
			nickName = nickName.substring(0, 1).toLowerCase() + nickName.substring(1, nickName.length());
			REPLACE_PARAM.put("NICK_NAME_LOWER", nickName);
			Resource resource = resourceLoader.getResource("classpath:" + TEMPLATE_URL);
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
			System.out.println(codeText);
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
		}
	}

}
