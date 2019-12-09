package com.hoolai.baobao.rbac.common.utils;

import com.hoolai.baobao.rbac.modules.base.entity.Permission;
import com.hoolai.baobao.rbac.modules.base.entity.User;
import com.hoolai.baobao.rbac.modules.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityUtil {

	@Autowired
	private IUserService iUserService;

	/**
	 * 获取当前登录用户
	 *
	 * @return
	 */
	public User getCurrUser() {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return iUserService.findByUsername(user.getUsername());
	}

	/**
	 * 通过用户名获取用户拥有权限
	 *
	 * @param username
	 */
	public List<GrantedAuthority> getCurrUserPerms(String username) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Permission p : iUserService.findByUsername(username).getPermissions()) {
			authorities.add(new SimpleGrantedAuthority(p.getTitle()));
		}
		return authorities;
	}
}
