package com.hoolai.baobao.rbac.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hoolai.baobao.rbac.base.BaseEntity;
import com.hoolai.baobao.rbac.common.constant.CommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_server")
public class ServerInfo extends BaseEntity {

	private String address;

	private String name;

	private int port = CommonConstant.DEFAULT_PORT;

	private Integer status = CommonConstant.STATUS_NORMAL;

	public ServerInfo() {
	}

	public ServerInfo(String address, String name) {
		this.address = address;
		this.name = name;
	}

	public ServerInfo(String address, String name, int port) {
		this.address = address;
		this.name = name;
		this.port = port;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isEnable() {
		return status == CommonConstant.STATUS_NORMAL;
	}

	public void disable() {
		this.status = CommonConstant.STATUS_DISABLE;
	}

	public void enable() {
		this.status = CommonConstant.STATUS_NORMAL;
	}
}
