package com.hoolai.baobao.rbac.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.hoolai.baobao.rbac.common.vo.IpLocate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class IpInfoUtil {

	/**
	 * 获取客户端IP地址
	 *
	 * @param request 请求
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (ip.equals("127.0.0.1")) {
				//根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	/**
	 * 获取IP返回地理信息
	 *
	 * @param ip ip地址
	 * @return
	 */
	public String getIpCity(String ip) {

		if (ip.contains("127.0.0.1") || ip.contains("localhost")) {
			return "本机IP";
		}
		if (innerIP(ip)) {
			return "局域网IP";
		}
		String GET_IP_LOCATE = "http://ip.taobao.com/service/getIpInfo.php?ip=";
		if (null != ip) {
			String url = GET_IP_LOCATE + ip;
			String result = "未知";
			try {
				String json = HttpUtil.get(url, 3000);
				IpLocate locate = JSON.parseObject(json, IpLocate.class);
				if (("200").equals(locate.getRetCode())) {
					if (StrUtil.isNotBlank(locate.getResult().getProvince())) {
						result = locate.getResult().getProvince() + " " + locate.getResult().getCity();
					} else {
						result = locate.getResult().getCountry();
					}
				}
			} catch (Exception e) {
			}
			return result;
		}
		return null;
	}

	private boolean innerIP(String ip) {

		//匹配10.0.0.0 - 10.255.255.255的网段
		String pattern_10 = "^(\\D)*10(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){3}";

		//匹配172.16.0.0 - 172.31.255.255的网段
		String pattern_172 = "172\\.([1][6-9]|[2]\\d|3[01])(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){2}";

		//匹配192.168.0.0 - 192.168.255.255的网段
		String pattern_192 = "192\\.168(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){2}";

		//合起来写
		String pattern = "((192\\.168|172\\.([1][6-9]|[2]\\d|3[01]))"
						+ "(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){2}|"
						+ "^(\\D)*10(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){3})";
		Pattern reg = Pattern.compile(pattern);
		Matcher match = reg.matcher(ip);
		return match.find();
	}
}
