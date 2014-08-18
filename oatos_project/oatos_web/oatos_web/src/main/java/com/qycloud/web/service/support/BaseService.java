package com.qycloud.web.service.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.utils.LogicException;
import com.conlect.oatos.utils.SysLogger;

@ControllerAdvice
public abstract class BaseService {

	/**
	* 异常控制，这便是异常细节可控，将来可用于支持国际化（异常信息国际化）
	* */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException(Exception ex, HttpServletRequest request) {
		SysLogger.getServerLogger().error(request.getRequestURI() + " request fail! [excetption]:  ", ex);
		return ErrorType.error500.name();
	}

	@ExceptionHandler(LogicException.class)
	@ResponseBody
	public String handleLogicException(LogicException ex, HttpServletRequest request) {
		String errorCode = ex.getError() != null ? ex.getError().name() : ErrorType.error500.name();
		SysLogger.getServerLogger().info(request.getRequestURI() + " request fail! error type is: " + errorCode);
		return errorCode;
	}
}
