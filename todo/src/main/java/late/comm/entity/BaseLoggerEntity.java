/**
 * @description
 */
package late.comm.entity;

import java.sql.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @projectName todo
 * @packageName late.comm.entity
 * @fileName BaseLoggerEntity.java
 * @author chijingjia
 * @createTime :2019年1月2日 下午1:59:09
 * @version: v1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseLoggerEntity extends BaseEntity {

	/**
	 * 登记时间
	 */
	@CreatedDate
	private Date logTime;
}
