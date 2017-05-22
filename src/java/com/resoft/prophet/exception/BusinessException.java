/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.exception;

/**
 * 业务异常类，该类用于描述包含业务规则的异常。<br/>
 * 
 * @author 韦海晗
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 6326269736365777366L;
	
	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable throwable) {
		super(throwable);
	}

	public BusinessException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
