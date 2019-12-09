package com.hoolai.baobao.rbac.modules.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hoolai.baobao.rbac.modules.base.entity.Role;
import com.hoolai.baobao.rbac.modules.base.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserRoleService extends IService<UserRole> {

	/**
	 * 通过用户id获取
	 *
	 * @param userId
	 * @return
	 */
	List<Role> findByUserId(@Param("userId") String userId);

	/**
	 * 通过roleId查找
	 *
	 * @param roleId
	 * @return
	 */
	List<UserRole> findByRoleId(String roleId);

	/**
	 * 删除用户角色
	 *
	 * @param userId
	 */
	void deleteByUserId(String userId);
}
