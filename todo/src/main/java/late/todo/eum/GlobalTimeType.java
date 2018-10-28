/**
 * @description
 */
package late.todo.eum;

/**
 * 休息时间类型
 * 
 * @projectName todo
 * @packageName late.todo.eum
 * @fileName HaltTimeType.java
 * @author chijingjia
 * @createTime :2018年10月8日 下午9:37:37
 * @version: v1.0
 */
public enum GlobalTimeType {
	HOLIDAY// 节假日 判断日期是否在区间
	, WORKTIME // 工作时间，范围内一定执行
	, OVERTIME // 加班时间，WorkTime外执行
	, BREAKTIME// 休息时间
}
