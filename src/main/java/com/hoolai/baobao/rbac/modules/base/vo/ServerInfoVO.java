package com.hoolai.baobao.rbac.modules.base.vo;

import com.hoolai.h5.util.annotation.JavaBean;

@JavaBean
public class ServerInfoVO {

	private String address;

	private String name;

	private int port;

	private boolean success;

	private String msg;

	private String requestUrl;

	public ServerInfoVO(String address, String name, int port) {
		this.address = address;
		this.name = name;
		this.port = port;
	}

	public ServerInfoVO(String address, int port, String name, boolean success, String msg, String requestUrl) {
		this.address = address;
		this.port = port;
		this.name = name;
		this.success = success;
		this.msg = msg;
		this.requestUrl = requestUrl;
	}
}
