package com.hoolai.baobao.rbac.modules.base.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.modules.base.entity.Role;
import com.hoolai.baobao.rbac.modules.base.entity.UserRole;
import com.hoolai.baobao.rbac.modules.base.mapper.UserRoleMapper;
import com.hoolai.baobao.rbac.modules.base.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("jdbc-admin")
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

	@Override
	public List<Role> findByUserId(String userId) {
		return this.getBaseMapper().findByUserId(userId);
	}

	@Override
	public List<UserRole> findByRoleId(String roleId) {
		QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("role_id", roleId);
		return this.list(queryWrapper);
	}

	@Override
	public void deleteByUserId(String userId) {
		QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		this.remove(queryWrapper);
	}
}
