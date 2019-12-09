package com.hoolai.baobao.rbac.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hoolai.baobao.rbac.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dict")
public class Dict extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String title;

	private String type;

	private String description;

	private BigDecimal sortOrder;

	public String getType() {
		return type;
	}
}