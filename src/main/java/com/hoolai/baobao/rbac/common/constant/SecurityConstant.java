package com.hoolai.baobao.rbac.common.constant;

public interface SecurityConstant {

	/**
	 * token分割
	 */
	String TOKEN_SPLIT = "Bearer ";

	/**
	 * JWT签名加密key
	 */
	String JWT_SIGN_KEY = "baobao";

	/**
	 * token参数头
	 */
	String HEADER = "accessToken";

	/**
	 * 权限参数头
	 */
	String AUTHORITIES = "authorities";

	/**
	 * 用户选择JWT保存时间参数头
	 */
	String SAVE_LOGIN = "saveLogin";

	/**
	 * 交互token前缀key
	 */
	String TOKEN_PRE = "BAOBAO_TOKEN_PRE:";

	/**
	 * 用户token前缀key 单点登录使用
	 */
	String USER_TOKEN = "BAOBAO_USER_TOKEN:";

	/**
	 * 是否单点登录
	 */
	Boolean SSO = false;

	/**
	 * 默认过期时间
	 */
	Long DEFAULT_EXIST_TIME = 7 * 24 * 60 * 60 * 1000L;
}
