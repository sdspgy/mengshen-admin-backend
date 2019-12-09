package com.hoolai.baobao.rbac.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PageVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pageNumber;

	private int pageSize;

	private String sort;

	private String order;

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getSort() {
		return sort;
	}

	public String getOrder() {
		return order;
	}
}
