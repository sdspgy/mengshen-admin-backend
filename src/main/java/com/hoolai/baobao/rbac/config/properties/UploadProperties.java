package com.hoolai.baobao.rbac.config.properties;

import com.google.common.collect.Maps;
import com.hoolai.h5.util.annotation.JavaBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@JavaBean
@Configuration
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadProperties {

	private String baseLocalPath;
	private String baseNginxPath;
	private String managerAvater;
	private List<String> childrenPaths;

	public String getBaseLocalPath() {
		return baseLocalPath;
	}

	public String getBaseNginxPath() {
		return baseNginxPath;
	}

	public List<String> getChildrenPaths() {
		return childrenPaths;
	}

	public Map<String, String> getChildrenMap() {
		Map<String, String> map = Maps.newHashMapWithExpectedSize(childrenPaths.size());
		childrenPaths.stream().forEach(obj -> {
			map.put(obj.split(",")[0], obj.split(",")[1]);
		});
		return map;
	}

	public String getLocalPath(String type) {
		return baseLocalPath + type + "/";
	}

	public String getNginxPath(String type) {
		return baseNginxPath + type + "/";
	}

	public String getManagerAvater() {
		return managerAvater;
	}

	public void setBaseLocalPath(String baseLocalPath) {
		this.baseLocalPath = baseLocalPath;
	}

	public void setBaseNginxPath(String baseNginxPath) {
		this.baseNginxPath = baseNginxPath;
	}

	public void setManagerAvater(String managerAvater) {
		this.managerAvater = managerAvater;
	}

	public void setChildrenPaths(List<String> childrenPaths) {
		this.childrenPaths = childrenPaths;
	}
}
