/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.web;

import java.io.UnsupportedEncodingException;

/**
 * 为浏览器提供远程PlainText数据的Action基类
 * 
 * @author 韦海晗
 */
public class PlainTextAction extends StreamAction {
	private static final long serialVersionUID = -1780681855272482228L;

	private final static String CHARSET_NAME_DEFAULT = "UTF-8";

	private final static String CONTENT_DEFAULT = "";

	protected String charsetName;

	protected String defaultContent = CONTENT_DEFAULT;

	public String getCharsetName() {
		return charsetName;
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public String getDefaultContent() {
		return defaultContent;
	}

	public void setDefaultContent(String defaultContent) {
		this.defaultContent = defaultContent;
	}

	/**
	 * 返回用于PlainText数据的模版方法。<br/> 
	 * 当WebWork框架调用时执行该方法。
	 * 
	 * @return PlainText文本
	 */
	protected String getText() {
		return null;
	}

	protected byte[] getContent() {
		String content = getText();
		byte[] bout = null;
		try {
			bout = (content == null ? this.defaultContent : content).getBytes((this.charsetName == null ? CHARSET_NAME_DEFAULT : this.charsetName));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bout;
	}
}
