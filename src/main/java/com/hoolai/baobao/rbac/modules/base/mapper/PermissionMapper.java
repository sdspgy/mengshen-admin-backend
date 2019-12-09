package com.hoolai.baobao.rbac.modules.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoolai.baobao.rbac.modules.base.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

	/**
	 * 通过用户id获取
	 *
	 * @param userId
	 * @return
	 */
	List<Permission> findByUserId(@Param("userId") String userId);
}
