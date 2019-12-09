package com.hoolai.baobao.rbac.config.security.jwt;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hoolai.baobao.rbac.common.annotation.SystemLog;
import com.hoolai.baobao.rbac.common.constant.SecurityConstant;
import com.hoolai.baobao.rbac.common.enums.LogType;
import com.hoolai.baobao.rbac.common.utils.ResponseUtil;
import com.hoolai.baobao.rbac.common.vo.TokenUser;
import com.hoolai.baobao.rbac.config.security.storage.StorageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 登录成功处理类
 */

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private StorageHandler storageHandler;

	@Override
	@SystemLog(description = "登录系统", type = LogType.LOGIN)
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		//用户选择保存登录状态几天
		String saveLogin = request.getParameter(SecurityConstant.SAVE_LOGIN);
		Boolean saved = false;
		if (StrUtil.isNotBlank(saveLogin) && Boolean.valueOf(saveLogin)) {
			saved = true;
		}
		String username = ((UserDetails) authentication.getPrincipal()).getUsername();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails) authentication.getPrincipal()).getAuthorities();
		List<String> list = new ArrayList<>();
		for (GrantedAuthority g : authorities) {
			list.add(g.getAuthority());
		}
		// 登陆成功生成token
		String token;
		token = UUID.randomUUID().toString().replace("-", "");
		TokenUser user = new TokenUser(username, list, saved);
		user.setPermissions(null);
		// 单设备登录
		if (SecurityConstant.SSO) {
			String oldToken = storageHandler.get(SecurityConstant.USER_TOKEN + username);
			if (StrUtil.isNotBlank(oldToken)) {
				storageHandler.remove(SecurityConstant.TOKEN_PRE + oldToken);
			}
		}

		storageHandler.put(SecurityConstant.USER_TOKEN + username, token);
		storageHandler.put(SecurityConstant.TOKEN_PRE + token, JSON.toJSONString(user));

		storageHandler.clearInvalid();

		ResponseUtil.out(response, ResponseUtil.resultMap(true, 200, "登录成功", token));
	}

}
