package com.hoolai.baobao.rbac.common.vo;

import com.hoolai.h5.util.annotation.JavaBean;

import java.io.Serializable;

@JavaBean
public class City implements Serializable {

	String country;

	String province;

	String city;

	public String getCountry() {
		return country;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}
}
