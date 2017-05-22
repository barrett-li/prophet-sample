/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.security.domain;

/**
 * 资源实体。
 * 
 * @author 韦海晗
 */
public class Resource {
	public static final String RESOURCE_URL = "URL";
	public static final String RESOURCE_FUNCTION = "FUNCTION";
	public static final String RESOURCE_MENU = "MENU";
	
	private String id;
	private String resType;
	private String resString;
	private String display;
	
	public String getDisplay() {
		return display;
	}
	
	public void setDisplay(String display) {
		this.display = display;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getResString() {
		return resString;
	}
	
	public void setResString(String resString) {
		this.resString = resString;
	}
	
	public String getResType() {
		return resType;
	}
	
	public void setResType(String type) {
		this.resType = type;
	}
}
