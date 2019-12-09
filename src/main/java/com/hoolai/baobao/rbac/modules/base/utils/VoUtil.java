package com.hoolai.baobao.rbac.modules.base.utils;

import cn.hutool.core.bean.BeanUtil;
import com.hoolai.baobao.rbac.modules.base.entity.Permission;
import com.hoolai.baobao.rbac.modules.base.vo.MenuVo;

public class VoUtil {

	public static MenuVo permissionToMenuVo(Permission p) {

		MenuVo menuVo = new MenuVo();
		BeanUtil.copyProperties(p, menuVo);
		return menuVo;
	}
}
