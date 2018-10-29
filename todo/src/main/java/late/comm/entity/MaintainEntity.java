/**
 * @description
 */
package late.comm.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @projectName todo
 * @packageName late.comm.entity
 * @fileName MaintainMongoEntity.java
 * @author chijingjia
 * @createTime :2018年10月13日 上午9:27:22
 * @version: v1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class MaintainEntity extends BaseEntity {
	/**
	 * 创建时间
	 */
	@CreatedDate

	private Date createTime;
	/**
	 * 最后维护时间
	 */
	@LastModifiedDate
	private Date modifyTime;

}
