package com.hoolai.baobao.rbac.config.security;

import com.hoolai.baobao.rbac.common.exception.LoginFailLimitException;
import com.hoolai.baobao.rbac.config.security.storage.StorageHandler;
import com.hoolai.baobao.rbac.modules.base.entity.User;
import com.hoolai.baobao.rbac.modules.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserService iUserService;

	@Autowired
	private StorageHandler storageHandler;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String flagKey = "loginFailFlag:" + username;
		Long value = 0L;
		if (Objects.nonNull(storageHandler.get(flagKey))) {
			value = Long.parseLong(storageHandler.get(flagKey));
		}
		if (Objects.nonNull(value) && value > System.currentTimeMillis()) {
			//超过限制次数
			throw new LoginFailLimitException("登录错误次数超过限制，请10分钟后再试");
		}
		User user = iUserService.findByUsername(username);
		return new SecurityUserDetails(user);
	}
}
