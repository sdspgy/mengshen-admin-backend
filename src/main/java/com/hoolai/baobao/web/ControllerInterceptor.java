package com.hoolai.baobao.web;

import com.hoolai.baobao.rbac.common.exception.RbacException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Aspect
@Component
public class ControllerInterceptor {

	@Pointcut("execution(public Object com.hoolai.baobao.rbac.modules.*..*Controller.*(..))")
	public void aspectByRbac() {
	}

	@Around("aspectByRbac()")
	public Object aroundByRbac(ProceedingJoinPoint invocation) throws Throwable {
		Map<String, Object> result = new HashMap<>();
		try {
			Object data = invocation.proceed();
			result.put("success", true);
			result.put("message", "success");
			result.put("code", 200);
			result.put("timestamp", System.currentTimeMillis());
			result.put("result", data);
			return result;
		} catch (RbacException e) {
			result.put("success", e.isSuccess());
			result.put("message", e.getMsg());
			result.put("code", e.getCode());
			result.put("timestamp", System.currentTimeMillis());
			return result;
		} catch (Exception e) {
			String msg = e.getMessage();
			result.put("success", false);
			result.put("message", msg == null ? "系统异常" : msg);
			result.put("code", 500);
			result.put("timestamp", System.currentTimeMillis());
			return result;
		} finally {
		}
	}

}
