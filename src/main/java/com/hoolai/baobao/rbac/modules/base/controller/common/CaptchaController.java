package com.hoolai.baobao.rbac.modules.base.controller.common;

import com.hoolai.baobao.rbac.common.utils.CreateVerifyCode;
import com.hoolai.baobao.rbac.config.security.storage.StorageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/baobao-admin/common/captcha")
@RestController
@Transactional
public class CaptchaController {

	@Autowired
	private StorageHandler storageHandler;

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public Object initCaptcha(HttpServletRequest request) {

		String captchaId = UUID.randomUUID().toString().replace("-", "");
		String code = new CreateVerifyCode().randomStr(4);
		storageHandler.put(captchaId, code, System.currentTimeMillis() + 2 * 60 * 1000);
		return captchaId;
	}

	@RequestMapping(value = "/draw/{captchaId}", method = RequestMethod.GET)
	public void drawCaptcha(@PathVariable("captchaId") String captchaId, HttpServletResponse response, HttpServletRequest request) throws IOException {

		String code = storageHandler.get(captchaId);
		CreateVerifyCode vCode = new CreateVerifyCode(116, 36, 4, 10, code);
		response.setContentType("image/png");
		vCode.write(response.getOutputStream());
	}
}
