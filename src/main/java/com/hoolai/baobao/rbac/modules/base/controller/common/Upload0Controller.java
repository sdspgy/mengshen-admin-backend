//package com.hoolai.baobao.rbac.modules.base.controller.common;
//
//import cn.hutool.core.util.StrUtil;
//import com.google.common.collect.Maps;
//import com.hoolai.baobao.rbac.common.constant.CommonConstant;
//import com.hoolai.baobao.rbac.common.exception.RbacException;
//import com.hoolai.baobao.rbac.common.utils.Base64DecodeMultipartFile;
//import com.hoolai.baobao.rbac.config.properties.UploadProperties;
//import com.hoolai.h5.util.string.StringUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/baobao-admin/upload")
//@Transactional
//public class Upload0Controller {
//
//	@Autowired
//	private UploadProperties uploadProperties;
//
//	@RequestMapping(value = "/file", method = RequestMethod.POST)
//	public Object upload(@RequestParam(value = "file", required = false) MultipartFile[] files, @RequestParam String type) {
//
//		if (StringUtil.isEmpty(type) || uploadProperties.getChildrenMap().get(type) == null) {
//			throw new RbacException(500, "参数异常，类型错误", false);
//		}
//
//		if (files == null || files.length == 0) {
//			throw new RbacException(500, "文件空", false);
//		}
//
//		List<String> succ = new ArrayList<>();
//		List<String> error = new ArrayList<>();
//
//		for (MultipartFile file : files) {
//			try {
//				file.transferTo(new File(uploadProperties.getLocalPath(type) + file.getOriginalFilename()));
//				succ.add(uploadProperties.getNginxPath(type) + file.getOriginalFilename());
//
//			} catch (Exception e) {
//				error.add(file.getOriginalFilename() + "--" + e.toString());
//				continue;
//			}
//		}
//		Map<String, Object> result = Maps.newHashMap();
//		result.put("succ", succ);
//		result.put("error", error);
//		return result;
//	}
//
//	@RequestMapping(value = "/avater", method = RequestMethod.POST)
//	public Object upload(@RequestParam(required = false) MultipartFile file,
//					@RequestParam(required = false) String base64,
//					HttpServletRequest request) {
//
//		if (StrUtil.isNotBlank(base64)) {
//			// base64上传
//			file = Base64DecodeMultipartFile.base64Convert(base64);
//		}
//		String result = null;
//		//没配置管理台头像统一返回默认头像
//		if (StringUtil.isEmpty(uploadProperties.getManagerAvater())) {
//			return CommonConstant.USER_DEFAULT_AVATAR;
//		}
//		try {
//			file.transferTo(new File(uploadProperties.getLocalPath(uploadProperties.getManagerAvater()) + file.getOriginalFilename()));
//			result = uploadProperties.getNginxPath(uploadProperties.getManagerAvater()) + file.getOriginalFilename();
//		} catch (Exception e) {
//			throw new RbacException(500, e.toString(), false);
//		}
//		return result;
//	}
//
//	@GetMapping("getUploadImgConfig")
//	public Object getUploadImgConfig() {
//		return uploadProperties.getChildrenPaths();
//	}
//}
