package com.hoolai.baobao.rbac.modules.base.controller.common;

import com.hoolai.baobao.rbac.common.exception.RbacException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baobao-admin/common")
@Transactional
public class SecurityController {

	@RequestMapping(value = "/needLogin", method = RequestMethod.GET)
	public Object needLogin() {
		throw new RbacException(401, "您还未登录", false);
	}

}
