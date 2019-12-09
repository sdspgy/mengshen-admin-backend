package com.hoolai.baobao.rbac.config.security.validate;

import cn.hutool.core.util.StrUtil;
import com.hoolai.baobao.rbac.common.utils.ResponseUtil;
import com.hoolai.baobao.rbac.config.properties.CaptchaProperties;
import com.hoolai.baobao.rbac.config.security.storage.StorageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图形验证码过滤器
 */

@Configuration
public class ImageValidateFilter extends OncePerRequestFilter {

	@Autowired
	private CaptchaProperties captchaProperties;

	@Autowired
	private StorageHandler storageHandler;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

		// 判断URL是否需要验证
		Boolean flag = false;
		String requestUrl = request.getRequestURI();
		PathMatcher pathMatcher = new AntPathMatcher();
		for (String url : captchaProperties.getImage()) {
			if (pathMatcher.match(url, requestUrl)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			String captchaId = request.getParameter("captchaId");
			String code = request.getParameter("code");
			if (StrUtil.isBlank(captchaId) || StrUtil.isBlank(code)) {
				ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "请传入图形验证码所需参数captchaId或code"));
				return;
			}
			String sessionCode = storageHandler.get(captchaId);
			if (StrUtil.isBlank(sessionCode)) {
				ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "验证码已过期，请重新获取"));
				return;
			}

			if (!sessionCode.toLowerCase().equals(code.toLowerCase())) {
				ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "图形验证码输入错误"));
				return;
			}
			storageHandler.remove(captchaId);
			chain.doFilter(request, response);
			return;
		}
		// 无需验证 放行
		chain.doFilter(request, response);
	}
}
