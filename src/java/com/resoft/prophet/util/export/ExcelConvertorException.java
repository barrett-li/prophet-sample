/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.util.export;

/**
 * Excel转换器抛出的异常。<br/>
 * 
 * @author 伍军军
 */
public class ExcelConvertorException extends RuntimeException {
	static final long serialVersionUID = -1;

	public ExcelConvertorException() {
	}

	public ExcelConvertorException(String msg) {
		super(msg);
	}

	public ExcelConvertorException(String msg, Exception e) {
		super(msg, e);
	}

	public ExcelConvertorException(Exception e) {
		super(e);
	}
}