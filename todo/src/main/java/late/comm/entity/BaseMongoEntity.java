/**
 * @description
 */
package late.comm.entity;

import lombok.Data;

/**
 * 
 * @projectName todo
 * @packageName late.comm.eum
 * @fileName BaseMongoEntity.java
 * @author chijingjia
 * @createTime :2018年10月8日 下午10:04:53
 * @version: v1.0
 */
@Data
public abstract class BaseMongoEntity {
	/**
	 * 主键
	 */
	private String id;
}
