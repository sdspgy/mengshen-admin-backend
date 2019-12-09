package com.hoolai.baobao.rbac.common.exception;

import org.springframework.security.authentication.InternalAuthenticationServiceException;

public class LoginFailLimitException extends InternalAuthenticationServiceException {

	private String msg;

	public LoginFailLimitException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public LoginFailLimitException(String msg, Throwable t) {
		super(msg, t);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
