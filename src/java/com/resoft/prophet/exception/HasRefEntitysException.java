/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.exception;

/**
 * 实体已经被引用异常，如果该实体被其他实体引用，则抛出该异常。<br/>
 * 
 * @author 伍军军
 */
public class HasRefEntitysException extends BusinessException {
	private static final long serialVersionUID = -5242387770683103380L;

	public HasRefEntitysException() {
		super();
	}

	public HasRefEntitysException(String message) {
		super(message);
	}

	public HasRefEntitysException(Throwable throwable) {
		super(throwable);
	}

	public HasRefEntitysException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
