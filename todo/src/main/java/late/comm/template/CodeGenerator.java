/**
 * @description
 */
package late.comm.template;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import late.comm.entity.BaseEntity;
import late.todo.entity.GlobalTimeEntity;
import late.todo.entity.SystemPropertyEntity;

/**
 * 代码生成
 * 
 * @projectName todo
 * @packageName late.comm.template
 * @fileName CodeGenerator.java
 * @author chijingjia
 * @createTime :2018年11月21日 下午1:36:16
 * @version: v1.0
 */
public class CodeGenerator {
	protected static ResourceLoader resourceLoader = new DefaultResourceLoader(); 

	/**
	 * 
	 * @methodName main
	 * @author chijingjia
	 * @createTime 2018年11月21日 下午1:36:16
	 * @version v1.0
	 * @param args
	 */
	public static void main(String[] args) {
		generate(SystemPropertyEntity.class);
	}

	public static void generate(Class<? extends BaseEntity> clazz) {
		IServiceGenerator.generate(clazz);
		ServiceImplGenerator.generate(clazz);
		RepositoryGenerator.generate(clazz);
	}

}
