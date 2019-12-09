package com.hoolai.baobao.rbac.modules.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hoolai.baobao.rbac.common.annotation.SystemLog;
import com.hoolai.baobao.rbac.common.constant.CommonConstant;
import com.hoolai.baobao.rbac.common.enums.LogType;
import com.hoolai.baobao.rbac.common.exception.RbacException;
import com.hoolai.baobao.rbac.common.utils.PageUtil;
import com.hoolai.baobao.rbac.common.utils.SecurityUtil;
import com.hoolai.baobao.rbac.common.vo.PageVo;
import com.hoolai.baobao.rbac.common.vo.SearchVo;
import com.hoolai.baobao.rbac.modules.base.entity.Role;
import com.hoolai.baobao.rbac.modules.base.entity.User;
import com.hoolai.baobao.rbac.modules.base.entity.UserRole;
import com.hoolai.baobao.rbac.modules.base.service.IUserRoleService;
import com.hoolai.baobao.rbac.modules.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baobao-admin/user")
public class UserController {

	@Autowired
	private IUserService iUserService;

	@Autowired
	private IUserRoleService iUserRoleService;

	@Autowired
	private SecurityUtil securityUtil;

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public Object getUserInfo() {

		User u = securityUtil.getCurrUser();
		u.setPassword(null);
		return u;
	}

	@RequestMapping(value = "/unlock", method = RequestMethod.POST)
	public Object unLock(@RequestParam String password) {

		User u = securityUtil.getCurrUser();
		if (!new BCryptPasswordEncoder().matches(password, u.getPassword())) {
			throw new RbacException(500, "密码不正确", false);
		}
		return null;
	}

	@SystemLog(description = "重置密码")
	@RequestMapping(value = "/resetPass", method = RequestMethod.POST)
	public Object resetPass(@RequestParam String[] ids) {

		for (String id : ids) {
			User u = iUserService.getById(id);
			u.setPassword(new BCryptPasswordEncoder().encode("123456"));
			iUserService.updateById(u);
		}
		throw new RbacException("操作成功", true);
	}

	@SystemLog(description = "用户自己修改资料")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Object editOwn(@ModelAttribute User u) {

		User old = securityUtil.getCurrUser();
		u.setUsername(old.getUsername());
		u.setPassword(old.getPassword());
		boolean b = iUserService.updateById(u);
		if (!b) {
			throw new RbacException(500, "修改失败", false);
		}
		throw new RbacException("修改成功", true);
	}

	/**
	 * @param u
	 * @param roles0
	 * @return
	 */
	@SystemLog(description = "管理员修改资料")
	@RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
	public Object edit(@ModelAttribute User u, @RequestParam(required = false) String[] roles0) {

		User old = iUserService.getById(u.getId());
		//若修改了用户名
		if (!old.getUsername().equals(u.getUsername())) {
			//判断新用户名是否存在
			if (iUserService.findByUsername(u.getUsername()) != null) {
				throw new RbacException(500, "该用户名已被存在", false);
			}
		}
		u.setPassword(old.getPassword());
		boolean b = iUserService.updateById(u);
		if (!b) {
			throw new RbacException(500, "修改失败", false);
		}
		//删除该用户角色
		iUserRoleService.deleteByUserId(u.getId());
		if (roles0 != null && roles0.length > 0) {
			//新角色
			for (String roleId : roles0) {
				UserRole ur = new UserRole();
				ur.setRoleId(roleId);
				ur.setUserId(u.getId());
				iUserRoleService.save(ur);
			}
		}
		throw new RbacException("修改成功", true);
	}

	/**
	 * 线上demo不允许测试账号改密码
	 *
	 * @param password
	 * @param newPass
	 * @return
	 */
	@SystemLog(description = "修改密码")
	@RequestMapping(value = "/modifyPass", method = RequestMethod.POST)
	public Object modifyPass(@RequestParam String password,
					@RequestParam String newPass, @RequestParam String passStrength) {

		User user = securityUtil.getCurrUser();

		if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			throw new RbacException(500, "旧密码不正确", false);

		}

		String newEncryptPass = new BCryptPasswordEncoder().encode(newPass);
		user.setPassword(newEncryptPass);
		user.setPassStrength(passStrength);
		iUserService.updateById(user);
		throw new RbacException("修改密码成功", true);
	}

	@RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
	public Object getByCondition(@ModelAttribute User user,
					@ModelAttribute SearchVo searchVo,
					@ModelAttribute PageVo pageVo) {

		IPage<User> page = iUserService.findByCondition(user, searchVo, PageUtil.initMpPage(pageVo));
		for (User u : page.getRecords()) {
			// 关联角色
			List<Role> list = iUserRoleService.findByUserId(u.getId());
			u.setRoles(list);
			u.setPassword(null);
		}
		return page;
	}

	@SystemLog(description = "获取用户数据")
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public Object getByCondition() {

		List<User> list = iUserService.list();
		for (User u : list) {
			u.setPassword(null);
		}
		return list;
	}

	@SystemLog(description = "后台添加用户")
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	public Object regist(@ModelAttribute User u, @RequestParam(required = false) String[] roles0) {

		if (StrUtil.isBlank(u.getUsername()) || StrUtil.isBlank(u.getPassword())) {
			throw new RbacException(500, "缺少必需表单字段", false);
		}

		if (iUserService.findByUsername(u.getUsername()) != null) {
			throw new RbacException(500, "该用户名已被注册", false);
		}

		String encryptPass = new BCryptPasswordEncoder().encode(u.getPassword());
		u.setPassword(encryptPass);
		boolean save = iUserService.save(u);
		if (!save) {
			throw new RbacException(500, "添加失败", false);
		}
		if (roles0 != null && roles0.length > 0) {
			//添加角色
			for (String roleId : roles0) {
				UserRole ur = new UserRole();
				ur.setUserId(u.getId());
				ur.setRoleId(roleId);
				iUserRoleService.save(ur);
			}
		}

		return u;
	}

	@SystemLog(description = "后台禁用用户")
	@RequestMapping(value = "/admin/disable/{userId}", method = RequestMethod.POST)
	public Object disable(@PathVariable String userId) {
		return changeUserStatus(userId, CommonConstant.USER_STATUS_LOCK);
	}

	@SystemLog(description = "后台启用用户")
	@RequestMapping(value = "/admin/enable/{userId}", method = RequestMethod.POST)
	public Object enable(@PathVariable String userId) {
		return changeUserStatus(userId, CommonConstant.USER_STATUS_NORMAL);
	}

	private Object changeUserStatus(String userId, Integer integer) {
		User user = iUserService.getById(userId);
		if (user == null) {
			throw new RbacException(500, "通过userId获取用户失败", false);
		}
		user.setStatus(integer);
		iUserService.updateById(user);
		return null;
	}

	@SystemLog(description = "删除用户", type = LogType.OPERATION)
	@RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.DELETE)
	public Object delAllByIds(@PathVariable String[] ids) {

		for (String id : ids) {
			iUserService.removeById(id);
			//删除关联角色
			iUserRoleService.deleteByUserId(id);
		}
		throw new RbacException("批量通过id删除数据成功", true);
	}

}
