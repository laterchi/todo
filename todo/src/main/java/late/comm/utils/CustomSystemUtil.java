/**
 * @description
 */
package late.comm.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 系统工具类，用于获取系统相关信息
 * 
 * @projectName todo
 * @packageName late.comm.utils
 * @fileName CustomSystemUtil.java
 * @author chijingjia
 * @createTime :2018年11月14日 上午9:22:34
 * @version: v1.0
 */
public class CustomSystemUtil {
	public static String INTRANET_IP = getIntranetIp(); // 内网IP
	public static String INTERNET_IP = getInternetIp(); // 外网IP
	public static String HOSTNAME = getHostname();

	private CustomSystemUtil() {
	}

	/**
	 * 机器名
	 * 
	 * @return 机器名
	 */
	private static String getHostname() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得内网IP
	 * 
	 * @return 内网IP
	 */
	private static String getIntranetIp() {
		try {
			CustomSystemUtil.HOSTNAME = InetAddress.getLocalHost().getHostName();
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得外网IP
	 * 
	 * @return 外网IP
	 */
	private static String getInternetIp() {
		try {
			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			Enumeration<InetAddress> addrs;
			while (networks.hasMoreElements()) {
				addrs = networks.nextElement().getInetAddresses();
				while (addrs.hasMoreElements()) {
					ip = addrs.nextElement();
					if (ip != null && ip instanceof Inet4Address && ip.isSiteLocalAddress()
							&& !ip.getHostAddress().equals(INTRANET_IP)) {
						return ip.getHostAddress();
					}
				}
			}

			// 如果没有外网IP，就返回内网IP
			return INTRANET_IP;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}