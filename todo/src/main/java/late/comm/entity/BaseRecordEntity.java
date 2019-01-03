/**
 * @description
 */
package late.comm.entity;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @projectName todo
 * @packageName late.comm.entity
 * @fileName BaseRecordEntity.java
 * @author chijingjia
 * @createTime :2018年11月6日 上午10:14:05
 * @version: v1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
@MappedSuperclass
public abstract class BaseRecordEntity extends BaseEntity{
	
}
