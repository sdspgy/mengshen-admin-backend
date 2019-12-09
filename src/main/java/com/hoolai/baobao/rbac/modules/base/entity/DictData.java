package com.hoolai.baobao.rbac.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hoolai.baobao.rbac.base.BaseEntity;
import com.hoolai.baobao.rbac.common.constant.CommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dict_data")
public class DictData extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String title;

	private String value;

	private BigDecimal sortOrder;

	private Integer status = CommonConstant.STATUS_NORMAL;

	private String description;

	private String dictId;

	public String getDictId() {
		return dictId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Integer getStatus() {
		return status;
	}
}