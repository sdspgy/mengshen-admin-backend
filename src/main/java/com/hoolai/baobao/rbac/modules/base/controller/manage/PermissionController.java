package com.hoolai.baobao.rbac.modules.base.controller.manage;

import com.hoolai.baobao.rbac.common.annotation.SystemLog;
import com.hoolai.baobao.rbac.common.constant.CommonConstant;
import com.hoolai.baobao.rbac.common.exception.RbacException;
import com.hoolai.baobao.rbac.common.utils.SecurityUtil;
import com.hoolai.baobao.rbac.config.security.permission.MySecurityMetadataSource;
import com.hoolai.baobao.rbac.modules.base.entity.Permission;
import com.hoolai.baobao.rbac.modules.base.entity.RolePermission;
import com.hoolai.baobao.rbac.modules.base.entity.User;
import com.hoolai.baobao.rbac.modules.base.service.IPermissionService;
import com.hoolai.baobao.rbac.modules.base.service.IRolePermissionService;
import com.hoolai.baobao.rbac.modules.base.utils.VoUtil;
import com.hoolai.baobao.rbac.modules.base.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/baobao-admin/permission")
public class PermissionController {

	@Autowired
	private IRolePermissionService iRolePermissionService;

	@Autowired
	private IPermissionService iPermissionService;

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private MySecurityMetadataSource mySecurityMetadataSource;

	@RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
	public Object getAllMenuList() {

		List<MenuVo> menuList = new ArrayList<>();
		User u = securityUtil.getCurrUser();

		// 用户所有权限 已排序去重
		List<Permission> list = iPermissionService.findByUserId(u.getId());

		// 筛选0级页面
		for (Permission p : list) {
			if (CommonConstant.PERMISSION_NAV.equals(p.getType()) && CommonConstant.LEVEL_ZERO.equals(p.getLevel())) {
				menuList.add(VoUtil.permissionToMenuVo(p));
			}
		}
		// 筛选一级页面
		List<MenuVo> firstMenuList = new ArrayList<>();
		for (Permission p : list) {
			if (CommonConstant.PERMISSION_PAGE.equals(p.getType()) && CommonConstant.LEVEL_ONE.equals(p.getLevel())) {
				firstMenuList.add(VoUtil.permissionToMenuVo(p));
			}
		}
		// 筛选二级页面
		List<MenuVo> secondMenuList = new ArrayList<>();
		for (Permission p : list) {
			if (CommonConstant.PERMISSION_PAGE.equals(p.getType()) && CommonConstant.LEVEL_TWO.equals(p.getLevel())) {
				secondMenuList.add(VoUtil.permissionToMenuVo(p));
			}
		}
		// 筛选二级页面拥有的按钮权限
		List<MenuVo> buttonPermissions = new ArrayList<>();
		for (Permission p : list) {
			if (CommonConstant.PERMISSION_OPERATION.equals(p.getType()) && CommonConstant.LEVEL_THREE.equals(p.getLevel())) {
				buttonPermissions.add(VoUtil.permissionToMenuVo(p));
			}
		}

		// 匹配二级页面拥有权限
		for (MenuVo m : secondMenuList) {
			List<String> permTypes = new ArrayList<>();
			for (MenuVo me : buttonPermissions) {
				if (m.getId().equals(me.getParentId())) {
					permTypes.add(me.getButtonType());
				}
			}
			m.setPermTypes(permTypes);
		}
		// 匹配一级页面拥有二级页面
		for (MenuVo m : firstMenuList) {
			List<MenuVo> secondMenu = new ArrayList<>();
			for (MenuVo me : secondMenuList) {
				if (m.getId().equals(me.getParentId())) {
					secondMenu.add(me);
				}
			}
			m.setChildren(secondMenu);
		}
		// 匹配0级页面拥有一级页面
		for (MenuVo m : menuList) {
			List<MenuVo> firstMenu = new ArrayList<>();
			for (MenuVo me : firstMenuList) {
				if (m.getId().equals(me.getParentId())) {
					firstMenu.add(me);
				}
			}
			m.setChildren(firstMenu);
		}
		return menuList;
	}

	@RequestMapping(value = "/getAllList", method = RequestMethod.GET)
	public Object getAllList() {

		// 0级
		List<Permission> list0 = iPermissionService.findByLevelOrderBySortOrder(CommonConstant.LEVEL_ZERO);
		for (Permission p0 : list0) {
			// 一级
			List<Permission> list1 = iPermissionService.findByParentIdOrderBySortOrder(p0.getId());
			p0.setChildren(list1);
			// 二级
			for (Permission p1 : list1) {
				List<Permission> children1 = iPermissionService.findByParentIdOrderBySortOrder(p1.getId());
				p1.setChildren(children1);
				// 三级
				for (Permission p2 : children1) {
					List<Permission> children2 = iPermissionService.findByParentIdOrderBySortOrder(p2.getId());
					p2.setChildren(children2);
				}
			}
		}
		return list0;
	}

	@SystemLog(description = "添加")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@ModelAttribute Permission permission) {

		// 判断拦截请求的操作权限按钮名是否已存在
		if (CommonConstant.PERMISSION_OPERATION.equals(permission.getType())) {
			List<Permission> list = iPermissionService.findByTitle(permission.getTitle());
			if (list != null && list.size() > 0) {
				throw new RbacException(500, "名称已存在", false);
			}
		}
		iPermissionService.save(permission);
		//重新加载权限
		mySecurityMetadataSource.loadResourceDefine();
		return permission;
	}

	@SystemLog(description = "编辑")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Object edit(@ModelAttribute Permission permission) {

		// 判断拦截请求的操作权限按钮名是否已存在
		if (CommonConstant.PERMISSION_OPERATION.equals(permission.getType())) {
			// 若名称修改
			Permission p = iPermissionService.getById(permission.getId());
			if (!p.getTitle().equals(permission.getTitle())) {
				List<Permission> list = iPermissionService.findByTitle(permission.getTitle());
				if (list != null && list.size() > 0) {
					throw new RbacException(500, "名称已存在", false);
				}
			}
		}
		iPermissionService.updateById(permission);
		//重新加载权限
		mySecurityMetadataSource.loadResourceDefine();
		return permission;
	}

	@SystemLog(description = "批量删除")
	@RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.DELETE)
	public Object delByIds(@PathVariable String[] ids) {

		for (String id : ids) {
			List<RolePermission> list = iRolePermissionService.findByPermissionId(id);
			if (list != null && list.size() > 0) {
				throw new RbacException(500, "删除失败，包含正被角色使用关联的菜单或权限", false);
			}
		}
		for (String id : ids) {
			iPermissionService.removeById(id);
		}
		//重新加载权限
		mySecurityMetadataSource.loadResourceDefine();
		throw new RbacException("批量通过id删除数据成功", true);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Object searchPermissionList(@RequestParam String title) {
		return iPermissionService.findByTitleLikeOrderBySortOrder(title);
	}
}
