package com.hoolai.baobao.rbac.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hoolai.baobao.rbac.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_role")
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String roleId;

	@TableField(exist = false)
	private String roleName;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
