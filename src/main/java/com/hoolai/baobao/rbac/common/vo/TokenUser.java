package com.hoolai.baobao.rbac.common.vo;

import com.hoolai.h5.util.annotation.JavaBean;

import java.io.Serializable;
import java.util.List;

@JavaBean
public class TokenUser implements Serializable {

	private String username;

	private List<String> permissions;

	private Boolean saveLogin;

	public String getUsername() {
		return username;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public Boolean getSaveLogin() {
		return saveLogin;
	}

	public TokenUser() {
	}

	public TokenUser(String username, List<String> permissions, Boolean saveLogin) {
		this.username = username;
		this.permissions = permissions;
		this.saveLogin = saveLogin;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSaveLogin(Boolean saveLogin) {
		this.saveLogin = saveLogin;
	}
}
