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
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {

	private List<String> image = new ArrayList<>();

	public List<String> getImage() {
		return image;
	}
}
