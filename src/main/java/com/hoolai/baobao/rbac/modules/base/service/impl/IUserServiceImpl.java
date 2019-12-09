package com.hoolai.baobao.rbac.modules.base.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoolai.baobao.rbac.common.vo.SearchVo;
import com.hoolai.baobao.rbac.modules.base.entity.Permission;
import com.hoolai.baobao.rbac.modules.base.entity.Role;
import com.hoolai.baobao.rbac.modules.base.entity.User;
import com.hoolai.baobao.rbac.modules.base.mapper.PermissionMapper;
import com.hoolai.baobao.rbac.modules.base.mapper.UserMapper;
import com.hoolai.baobao.rbac.modules.base.mapper.UserRoleMapper;
import com.hoolai.baobao.rbac.modules.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@DS("jdbc-admin")
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public User findByUsername(String username) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", username);
		User user = this.getOne(queryWrapper);
		if (user == null) {
			return null;
		}
		// 关联角色
		List<Role> roleList = userRoleMapper.findByUserId(user.getId());
		user.setRoles(roleList);
		// 关联权限菜单
		List<Permission> permissionList = permissionMapper.findByUserId(user.getId());
		user.setPermissions(permissionList);
		return user;
	}

	@Override
	public IPage<User> findByCondition(User user, SearchVo searchVo, Page page) {
		QueryWrapper<User> queryWrapper = new QueryWrapper();

		//模糊搜素
		if (StrUtil.isNotBlank(user.getUsername())) {
			queryWrapper.like("username", user.getUsername());
		}
		if (StrUtil.isNotBlank(user.getMobile())) {
			queryWrapper.like("mobile", user.getMobile());
		}
		if (StrUtil.isNotBlank(user.getEmail())) {
			queryWrapper.like("email", user.getEmail());
		}

		//性别
		if (StrUtil.isNotBlank(user.getSex())) {
			queryWrapper.eq("sex", user.getSex());
		}
		//类型
		if (user.getType() != null) {
			queryWrapper.eq("type", user.getType());
		}
		//状态
		if (user.getStatus() != null) {
			queryWrapper.eq("status", user.getStatus());
		}
		//创建时间
		if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
			Date start = DateUtil.parse(searchVo.getStartDate());
			Date end = DateUtil.parse(searchVo.getEndDate());
			queryWrapper.ge("create_time", start).le("create_time", end);

		}
		return this.page(page, queryWrapper);
	}
}
