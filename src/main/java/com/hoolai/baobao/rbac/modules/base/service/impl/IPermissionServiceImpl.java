package com.hoolai.baobao.rbac.modules.base.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.modules.base.entity.Permission;
import com.hoolai.baobao.rbac.modules.base.mapper.PermissionMapper;
import com.hoolai.baobao.rbac.modules.base.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("jdbc-admin")
public class IPermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

	@Override
	public List<Permission> findByUserId(String userId) {

		return this.getBaseMapper().findByUserId(userId);
	}

	@Override
	public List<Permission> findByLevelOrderBySortOrder(Integer level) {
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("level", level).orderByAsc("sort_order");
		return this.list(queryWrapper);
	}

	@Override
	public List<Permission> findByParentIdOrderBySortOrder(String parentId) {
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id", parentId).orderByAsc("sort_order");
		return this.list(queryWrapper);
	}

	@Override
	public List<Permission> findByTypeAndStatusOrderBySortOrder(Integer type, Integer status) {
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("type", type).eq("status", status).orderByAsc("sort_order");
		return this.list(queryWrapper);
	}

	@Override
	public List<Permission> findByTitle(String title) {
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("title", title).orderByAsc("sort_order");
		return this.list(queryWrapper);
	}

	@Override
	public List<Permission> findByTitleLikeOrderBySortOrder(String title) {
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("title", title).orderByAsc("sort_order");
		return this.list(queryWrapper);
	}
}
