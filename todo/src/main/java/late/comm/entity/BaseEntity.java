/**
 * @description
 */
package late.comm.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * 
 * @projectName todo
 * @packageName late.comm.entity
 * @fileName BaseEntity.java
 * @author chijingjia
 * @createTime :2018年10月28日 上午10:07:08
 * @version: v1.0
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
}
