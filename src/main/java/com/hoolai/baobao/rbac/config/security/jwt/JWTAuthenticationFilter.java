package com.hoolai.baobao.rbac.config.security.jwt;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hoolai.baobao.rbac.common.constant.SecurityConstant;
import com.hoolai.baobao.rbac.common.utils.ResponseUtil;
import com.hoolai.baobao.rbac.common.utils.SecurityUtil;
import com.hoolai.baobao.rbac.common.vo.TokenUser;
import com.hoolai.baobao.rbac.config.security.storage.StorageHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

	private SecurityUtil securityUtil;

	private StorageHandler storageHandler;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SecurityUtil securityUtil, StorageHandler storageHandler) {
		super(authenticationManager);
		this.securityUtil = securityUtil;
		this.storageHandler = storageHandler;
	}

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
		super(authenticationManager, authenticationEntryPoint);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		String header = request.getHeader(SecurityConstant.HEADER);
		if (StrUtil.isBlank(header)) {
			header = request.getParameter(SecurityConstant.HEADER);
		}
		Boolean notValid = StrUtil.isBlank(header);
		if (notValid) {
			chain.doFilter(request, response);
			return;
		}
		try {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(header, response);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			e.toString();
		}

		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String header, HttpServletResponse response) {

		// 用户名
		String username = null;
		// 权限
		List<GrantedAuthority> authorities = new ArrayList<>();

		// redis
		String v = storageHandler.get(SecurityConstant.TOKEN_PRE + header);
		if (StrUtil.isBlank(v)) {
			ResponseUtil.out(response, ResponseUtil.resultMap(false, 401, "登录已失效，请重新登录"));
			return null;
		}
		TokenUser user = JSON.parseObject(v, TokenUser.class);
		username = user.getUsername();

		// 未缓存 读取权限数据
		authorities = securityUtil.getCurrUserPerms(username);

		if (!user.getSaveLogin()) {
			// 若未保存登录状态重新设置失效时间
			storageHandler.put(SecurityConstant.USER_TOKEN + username, header);
			storageHandler.put(SecurityConstant.TOKEN_PRE + header, v);
		}

		if (StrUtil.isNotBlank(username)) {
			//踩坑提醒 此处password不能为null
			User principal = new User(username, "", authorities);
			return new UsernamePasswordAuthenticationToken(principal, null, authorities);
		}
		return null;
	}
}

