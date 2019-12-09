package com.hoolai.baobao.rbac.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hoolai.baobao.rbac.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_role_permission")
public class RolePermission extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String roleId;

	private String permissionId;

	public String getRoleId() {
		return roleId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
}