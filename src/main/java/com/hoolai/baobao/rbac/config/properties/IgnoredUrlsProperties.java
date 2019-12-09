package com.hoolai.baobao.rbac.config.properties;

import com.hoolai.h5.util.annotation.JavaBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@JavaBean
@Configuration
@Component
@ConfigurationProperties(prefix = "ignored")
public class IgnoredUrlsProperties {

	private List<String> urls = new ArrayList<>();

	public List<String> getUrls() {
		return urls;
	}
}
