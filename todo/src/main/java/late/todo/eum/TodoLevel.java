/**
 * @description
 */
package late.todo.eum;

/**
 * 待办级别
 * 
 * @projectName todo
 * @packageName late.todo.eum
 * @fileName TodoLevel.java
 * @author chijingjia
 * @createTime :2018年10月8日 下午6:04:26
 * @version: v1.0
 */
public enum TodoLevel {
	LOW, NORMAL, HIGH, WARN, URGENT;

	public TodoLevel nextLvl() {
		switch (this) {
		case LOW:
			return TodoLevel.NORMAL;
		case NORMAL:
			return TodoLevel.HIGH;
		case HIGH:
			return TodoLevel.WARN;
		case WARN:
			return TodoLevel.URGENT;
		default:
			return TodoLevel.URGENT;
		}
	}

	public TodoLevel lastLvl() {
		switch (this) {
		case URGENT:
			return TodoLevel.WARN;
		case WARN:
			return TodoLevel.HIGH;
		case HIGH:
			return TodoLevel.NORMAL;
		case NORMAL:
			return TodoLevel.LOW;
		default:
			return TodoLevel.NORMAL;
		}
	}
}
