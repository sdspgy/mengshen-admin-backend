package com.hoolai.baobao.rbac.modules.base.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hoolai.baobao.rbac.common.annotation.SystemLog;
import com.hoolai.baobao.rbac.common.enums.LogType;
import com.hoolai.baobao.rbac.common.exception.RbacException;
import com.hoolai.baobao.rbac.common.utils.PageUtil;
import com.hoolai.baobao.rbac.common.vo.PageVo;
import com.hoolai.baobao.rbac.modules.base.entity.Role;
import com.hoolai.baobao.rbac.modules.base.entity.RolePermission;
import com.hoolai.baobao.rbac.modules.base.entity.UserRole;
import com.hoolai.baobao.rbac.modules.base.service.IRolePermissionService;
import com.hoolai.baobao.rbac.modules.base.service.IRoleService;
import com.hoolai.baobao.rbac.modules.base.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baobao-admin/role")
public class RoleController {

	@Autowired
	private IRoleService iRoleService;

	@Autowired
	private IUserRoleService iUserRoleService;

	@Autowired
	private IRolePermissionService iRolePermissionService;

	@RequestMapping(value = "/getAllList", method = RequestMethod.GET)
	public Object roleGetAll() {
		return iRoleService.list();
	}

	@RequestMapping(value = "/getAllByPage", method = RequestMethod.GET)
	public Object getRoleByPage(@ModelAttribute PageVo page) {

		IPage<Role> list = iRoleService.page(PageUtil.initMpPage(page));
		for (Role role : list.getRecords()) {
			// 角色拥有权限
			List<RolePermission> permissions = iRolePermissionService.findByRoleId(role.getId());
			role.setPermissions(permissions);
		}
		return list;
	}

	@SystemLog(description = "设置或取消默认角色")
	@RequestMapping(value = "/setDefault", method = RequestMethod.POST)
	public Object setDefault(@RequestParam String id,
					@RequestParam Boolean isDefault) {
		Role role = iRoleService.getById(id);
		if (role == null) {
			throw new RbacException(500, "角色不存在", false);
		}
		role.setDefaultRole(isDefault);
		iRoleService.updateById(role);
		throw new RbacException("设置成功", true);
	}

	@SystemLog(description = "角色赋权")
	@RequestMapping(value = "/editRolePerm", method = RequestMethod.POST)
	public Object editRolePerm(@RequestParam String roleId,
					@RequestParam(required = false) String[] permIds) {

		//删除其关联权限
		iRolePermissionService.deleteByRoleId(roleId);
		//分配新权限
		for (String permId : permIds) {
			RolePermission rolePermission = new RolePermission();
			rolePermission.setRoleId(roleId);
			rolePermission.setPermissionId(permId);
			iRolePermissionService.save(rolePermission);
		}
		throw new RbacException("设置成功", true);
	}

	@SystemLog(description = "添加角色", type = LogType.OPERATION)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Object save(@ModelAttribute Role role) {
		return iRoleService.save(role);
	}

	@SystemLog(description = "更新数据")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Object edit(@ModelAttribute Role entity) {
		iRoleService.updateById(entity);
		return entity;
	}

	@SystemLog(description = "批量通过ids删除")
	@RequestMapping(value = "/delAllByIds/{ids}", method = RequestMethod.DELETE)
	public Object delByIds(@PathVariable String[] ids) {

		for (String id : ids) {
			List<UserRole> list = iUserRoleService.findByRoleId(id);
			if (list != null && list.size() > 0) {
				throw new RbacException(500, "删除失败，包含正被用户使用关联的角色", false);
			}
		}
		for (String id : ids) {
			iRoleService.removeById(id);
			//删除关联菜单权限
			iRolePermissionService.deleteByRoleId(id);
		}
		throw new RbacException("批量通过id删除数据成功", true);
	}

}
