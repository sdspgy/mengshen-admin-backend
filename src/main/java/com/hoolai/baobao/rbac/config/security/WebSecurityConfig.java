package com.hoolai.baobao.rbac.config.security;

import com.hoolai.baobao.rbac.common.utils.SecurityUtil;
import com.hoolai.baobao.rbac.config.properties.IgnoredUrlsProperties;
import com.hoolai.baobao.rbac.config.security.jwt.AuthenticationFailHandler;
import com.hoolai.baobao.rbac.config.security.jwt.AuthenticationSuccessHandler;
import com.hoolai.baobao.rbac.config.security.jwt.JWTAuthenticationFilter;
import com.hoolai.baobao.rbac.config.security.jwt.RestAccessDeniedHandler;
import com.hoolai.baobao.rbac.config.security.permission.MyFilterSecurityInterceptor;
import com.hoolai.baobao.rbac.config.security.storage.StorageHandler;
import com.hoolai.baobao.rbac.config.security.validate.ImageValidateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security 核心配置类
 * 开启控制权限至Controller
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private IgnoredUrlsProperties ignoredUrlsProperties;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthenticationSuccessHandler successHandler;

	@Autowired
	private AuthenticationFailHandler failHandler;

	@Autowired
	private RestAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

	@Autowired
	private ImageValidateFilter imageValidateFilter;

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private StorageHandler storageHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
						.authorizeRequests();

		// 除配置文件忽略路径其它所有请求都需经过认证和授权
		for (String url : ignoredUrlsProperties.getUrls()) {
			registry.antMatchers(url).permitAll();
		}

		registry.and()
						// 表单登录方式
						.formLogin()
						.loginPage("/baobao-admin/common/needLogin")
						// 登录请求url
						.loginProcessingUrl("/baobao-admin/login")
						.permitAll()
						// 成功处理类
						.successHandler(successHandler)
						// 失败
						.failureHandler(failHandler)
						.and()
						// 允许网页iframe
						.headers().frameOptions().disable()
						.and()
						.logout()
						.permitAll()
						.and()
						.authorizeRequests()
						// 任何请求
						.anyRequest()
						// 需要身份认证
						.authenticated()
						.and()
						// 关闭跨站请求防护
						.csrf().disable()
						// 前后端分离采用JWT 不需要session
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
						.and()
						// 自定义权限拒绝处理类
						.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
						.and()
						// 添加自定义权限过滤器
						.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
						// 图形验证码过滤器
						.addFilterBefore(imageValidateFilter, UsernamePasswordAuthenticationFilter.class)
						// 添加JWT过滤器 除已配置的其它请求都需经过此过滤器
						.addFilter(new JWTAuthenticationFilter(authenticationManager(), securityUtil, storageHandler));
	}
}
