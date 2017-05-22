/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.exception;

/**
 * 实体已经存在，如果数据表中存在相同的实体，则抛出该异常。<br/>
 * 
 * @author 伍军军
 */
public class EntityExistedException extends BusinessException {
	private static final long serialVersionUID = -4643324901721684418L;

	public EntityExistedException() {
		super();
	}

	public EntityExistedException(String message) {
		super(message);
	}

	public EntityExistedException(Throwable throwable) {
		super(throwable);
	}

	public EntityExistedException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
