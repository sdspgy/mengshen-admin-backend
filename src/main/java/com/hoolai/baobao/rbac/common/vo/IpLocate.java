package com.hoolai.baobao.rbac.common.vo;

import com.hoolai.h5.util.annotation.JavaBean;

import java.io.Serializable;

@JavaBean
public class IpLocate implements Serializable {

	private String retCode;

	private City result;

	public String getRetCode() {
		return retCode;
	}

	public City getResult() {
		return result;
	}
}

