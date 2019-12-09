package com.hoolai.baobao.rbac.modules.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hoolai.baobao.rbac.modules.base.entity.RolePermission;

import java.util.List;

public interface IRolePermissionService extends IService<RolePermission> {

	/**
	 * 通过permissionId获取
	 *
	 * @param permissionId
	 * @return
	 */
	List<RolePermission> findByPermissionId(String permissionId);

	/**
	 * 通过roleId获取
	 *
	 * @param roleId
	 */
	List<RolePermission> findByRoleId(String roleId);

	/**
	 * 通过roleId删除
	 *
	 * @param roleId
	 */
	void deleteByRoleId(String roleId);

}
