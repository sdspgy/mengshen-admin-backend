package com.hoolai.baobao.rbac.common.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class ObjectUtil {

	public static String mapToString(Map<String, String[]> paramMap) {

		if (paramMap == null) {
			return "";
		}
		Map<String, Object> params = new HashMap<>(16);
		for (Map.Entry<String, String[]> param : paramMap.entrySet()) {

			String key = param.getKey();
			String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
			String obj = StrUtil.endWithIgnoreCase(param.getKey(), "password") ? "******" : paramValue;
			params.put(key, obj);
		}
		return JSON.toJSONString(params);
	}

	public static String mapToStringAll(Map<String, String[]> paramMap) {

		if (paramMap == null) {
			return "";
		}
		Map<String, Object> params = new HashMap<>(16);
		for (Map.Entry<String, String[]> param : paramMap.entrySet()) {

			String key = param.getKey();
			String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
			params.put(key, paramValue);
		}
		return JSON.toJSONString(params);
	}
}
