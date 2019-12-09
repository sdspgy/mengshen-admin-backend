package com.hoolai.baobao.rbac.config.security.jwt;

import cn.hutool.core.util.StrUtil;
import com.hoolai.baobao.rbac.common.exception.LoginFailLimitException;
import com.hoolai.baobao.rbac.common.utils.ResponseUtil;
import com.hoolai.baobao.rbac.config.security.storage.StorageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private StorageHandler storageHandler;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

		if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
			String username = request.getParameter("username");
			recordLoginTime(username, request);
			String key = "loginTimeLimit:" + username;

			String value = storageHandler.get(key);
			if (StrUtil.isBlank(value)) {
				value = "0";
			}
			//获取已登录错误次数
			int loginFailTime = Integer.parseInt(value);
			int restLoginTime = 10 - loginFailTime;//10次登录失败后等待
			if (restLoginTime <= 3 && restLoginTime > 0) {
				ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "用户名或密码错误，还有" + restLoginTime + "次尝试机会"));
			} else if (restLoginTime <= 0) {
				ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "登录错误次数超过限制，请10分钟后再试"));
			} else {
				ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "用户名或密码错误"));
			}
		} else if (e instanceof DisabledException) {
			ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "账户被禁用，请联系管理员"));
		} else if (e instanceof LoginFailLimitException) {
			ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, ((LoginFailLimitException) e).getMsg()));
		} else {
			ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "登录失败，其他内部错误"));
		}
	}

	/**
	 * 判断用户登陆错误次数
	 */
	public boolean recordLoginTime(String username, HttpServletRequest request) {

		String key = "loginTimeLimit:" + username;
		String flagKey = "loginFailFlag:" + username;
		String value = storageHandler.get(key);
		if (StrUtil.isBlank(value)) {
			value = "0";
		}
		//获取已登录错误次数
		int loginFailTime = Integer.parseInt(value) + 1;
		storageHandler.put(key, String.valueOf(loginFailTime));
		if (loginFailTime >= 10) {
			//登录失败10次，10分钟后才能继续登录
			storageHandler.put(flagKey, String.valueOf(System.currentTimeMillis() + 10 * 60 * 1000));
			return false;
		}
		return true;
	}
}
