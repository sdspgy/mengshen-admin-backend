package com.hoolai.baobao.rbac.modules.base.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.modules.base.entity.RolePermission;
import com.hoolai.baobao.rbac.modules.base.mapper.RolePermissionMapper;
import com.hoolai.baobao.rbac.modules.base.service.IRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("jdbc-admin")
public class IRolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

	@Override
	public List<RolePermission> findByPermissionId(String permissionId) {
		QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("permission_id", permissionId);
		return this.list(queryWrapper);
	}

	@Override
	public List<RolePermission> findByRoleId(String roleId) {
		QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("role_id", roleId);
		return this.list(queryWrapper);
	}

	@Override
	public void deleteByRoleId(String roleId) {
		QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("role_id", roleId);
		this.remove(queryWrapper);
	}
}
