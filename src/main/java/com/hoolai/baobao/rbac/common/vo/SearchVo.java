package com.hoolai.baobao.rbac.common.vo;

import com.hoolai.h5.util.annotation.JavaBean;

import java.io.Serializable;

@JavaBean
public class SearchVo implements Serializable {

	private String startDate;

	private String endDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
