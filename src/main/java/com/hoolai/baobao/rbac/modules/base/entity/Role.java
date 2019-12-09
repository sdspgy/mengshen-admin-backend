package com.hoolai.baobao.rbac.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hoolai.baobao.rbac.base.BaseEntity;
import com.hoolai.baobao.rbac.common.constant.CommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	private Boolean defaultRole;

	private Integer dataType = CommonConstant.DATA_TYPE_ALL;

	private String description;

	@TableField(exist = false)
	private List<RolePermission> permissions;

	public Integer getDataType() {
		return dataType;
	}

	public String getName() {
		return name;
	}

	public List<RolePermission> getPermissions() {
		return permissions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDefaultRole(Boolean defaultRole) {
		this.defaultRole = defaultRole;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPermissions(List<RolePermission> permissions) {
		this.permissions = permissions;
	}
}
