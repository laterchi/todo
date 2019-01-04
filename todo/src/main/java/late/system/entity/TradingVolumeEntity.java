/**
 * @description
 */
package late.system.entity;

import late.comm.entity.BaseLoggerEntity;
import late.comm.eum.TradeStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @projectName todo
 * @packageName late.system.entity
 * @fileName TradeMonitorEntity.java
 * @author chijingjia
 * @createTime :2019年1月3日 上午10:41:25
 * @version: v1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class TradingVolumeEntity extends BaseLoggerEntity {
	/**
	 * 时间点
	 */
	private String timePoint;
	/**
	 * 交易量
	 */
	private long cnt;
	/**
	 * 交易状态
	 */
	private TradeStatus tradeStatus;

}
