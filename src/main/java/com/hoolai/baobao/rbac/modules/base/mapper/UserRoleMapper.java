package com.hoolai.baobao.rbac.modules.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.baobao.rbac.modules.base.entity.Role;
import com.hoolai.baobao.rbac.modules.base.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

	/**
	 * 通过用户id获取
	 *
	 * @param userId
	 * @return
	 */
	List<Role> findByUserId(@Param("userId") String userId);
}
