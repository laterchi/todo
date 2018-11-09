/**
 * @description
 */
package late.comm.entity;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public abstract class MaintainEntity extends BaseEntity {
	/**
	 * 创建时间
	 */
	@CreatedDate
	private Date createTime;
	/**
	 * 实际创建时间-for 导入
	 */
	private Date createTimeReal;
	/**
	 * 最后维护时间
	 */
	@LastModifiedDate
	private Date modifyTime;

	public void setCreateTimeReal(Date createTimeReal) {
		if (createTimeReal == null) {
			createTimeReal = this.createTime;
		}
		this.createTimeReal = createTimeReal;
	}

	public Date getCreatetimeReal() {
		return this.createTimeReal == null ? this.createTime : this.createTimeReal;
	}

}
