package com.hoolai.baobao.rbac.common.exception;

public class RbacException extends RuntimeException {

	private int code;

	private String msg;

	private boolean success;

	public RbacException(String msg, boolean success) {
		super(msg);
		this.code = 200;
		this.msg = msg;
		this.success = success;
	}

	public RbacException(int code, String msg, boolean success) {
		super(msg);
		this.code = code;
		this.msg = msg;
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	public boolean isSuccess() {
		return success;
	}
}
