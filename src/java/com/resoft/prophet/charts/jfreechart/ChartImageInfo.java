/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.jfreechart;

/**
 * 保存于Servlet中Chart的显示信息，包括图片的大小、图片的路径、usemap信息和usemap的名称等信息。
 * 
 * @author 韦海晗
 */
public class ChartImageInfo {
	private String name;
	
	private String height;

	private String width;

	private String src;

	private String usemapName;

	private String usemapContent;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getUsemapContent() {
		return usemapContent;
	}

	public void setUsemapContent(String usemapContent) {
		this.usemapContent = usemapContent;
	}

	public String getUsemapName() {
		return usemapName;
	}

	public void setUsemapName(String usemapName) {
		this.usemapName = usemapName;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

}
