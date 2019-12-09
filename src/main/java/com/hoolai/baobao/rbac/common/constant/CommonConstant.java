package com.hoolai.baobao.rbac.common.constant;

/**
 * 常量
 */
public interface CommonConstant {

	/**
	 * 用户默认头像
	 */
	String USER_DEFAULT_AVATAR = "https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png";

	/**
	 * 用户正常状态
	 */
	Integer USER_STATUS_NORMAL = 0;

	/**
	 * 用户禁用状态
	 */
	Integer USER_STATUS_LOCK = -1;

	/**
	 * 普通用户
	 */
	Integer USER_TYPE_NORMAL = 0;

	/**
	 * 管理员
	 */
	Integer USER_TYPE_ADMIN = 1;

	/**
	 * 全部数据权限
	 */
	Integer DATA_TYPE_ALL = 0;

	/**
	 * 自定义数据权限
	 */
	Integer DATA_TYPE_CUSTOM = 1;

	/**
	 * 正常状态
	 */
	Integer STATUS_NORMAL = 0;

	/**
	 * 禁用状态
	 */
	Integer STATUS_DISABLE = -1;

	/**
	 * 删除标志
	 */
	Integer DEL_FLAG = 1;

	/**
	 * 限流标识
	 */
	String LIMIT_ALL = "RBAC_LIMIT_ALL";

	/**
	 * 顶部菜单类型权限
	 */
	Integer PERMISSION_NAV = -1;

	/**
	 * 页面类型权限
	 */
	Integer PERMISSION_PAGE = 0;

	/**
	 * 操作类型权限
	 */
	Integer PERMISSION_OPERATION = 1;

	/**
	 * 1级菜单父id
	 */
	String PARENT_ID = "0";

	/**
	 * 0级菜单
	 */
	Integer LEVEL_ZERO = 0;

	/**
	 * 1级菜单
	 */
	Integer LEVEL_ONE = 1;

	/**
	 * 2级菜单
	 */
	Integer LEVEL_TWO = 2;

	/**
	 * 3级菜单
	 */
	Integer LEVEL_THREE = 3;

	Integer DEFAULT_PORT = 80;

}