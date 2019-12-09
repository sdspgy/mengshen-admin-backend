package com.hoolai.baobao.rbac.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hoolai.baobao.rbac.base.BaseEntity;
import com.hoolai.baobao.rbac.common.utils.ObjectUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_log")
public class Log extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	private Integer logType;

	private String requestUrl;

	private String requestType;

	private String requestParam;

	private String username;

	private String ip;

	private String ipInfo;

	private Integer costTime;

	public void setName(String name) {
		this.name = name;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setIpInfo(String ipInfo) {
		this.ipInfo = ipInfo;
	}

	public void setCostTime(Integer costTime) {
		this.costTime = costTime;
	}

	/**
	 * 转换请求参数为Json
	 *
	 * @param paramMap
	 */
	public void setMapToParams(Map<String, String[]> paramMap) {

		this.requestParam = ObjectUtil.mapToString(paramMap);
	}

}
