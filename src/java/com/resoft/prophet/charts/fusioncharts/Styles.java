/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

import org.dom4j.Element;

/**
 * FusionCharts v3 引入Styles可以帮助你在图表中设置字体，应用动态效果、渐变等各种特效，Styles对象用于映射FusionCharts配置文件中的styles元素。<br/>
 * styles元素定义一组样式对象。
 * 
 * @author 韦海晗
 */
public class Styles extends FusionElement {
	private FusionElement definition = new FusionElement("definition");
	private FusionElement application = new FusionElement("application");
	
	public final static String ELEMENT_NAME = "styles";
	
	/**
	 * 构造一个新的Styles，它表示一个FusionCharts配置文件中styles的元素。
	 */
	public Styles() {
		setName(ELEMENT_NAME);
	}
	
	/**
	 * 返回表示application的FusionElement对象。
	 * 
	 * @return application 表示application的FusionElement对象。
	 */
	public FusionElement getApplication() {
		return application;
	}

	/**
	 * 返回表示definition的FusionElement对象。
	 * 
	 * @return definition 表示definition的FusionElement对象。
	 */
	public FusionElement getDefinition() {
		return definition;
	}

	/**
	 * 为图表定义一个样式对象。
	 * 
	 * @return style 样式对象。
	 */
	public void definitionStyle(Style style) {
		definition.addElement(style);
	}
	
	/**
	 * 将一组样式对象应用于图表对象。
	 * 
	 * @param toObject 图表对象的名称。
	 * @param styles 一组样式对象名称，多个用“,”分隔。
	 */
	public void applyStyles(String toObject, String styles) {
		FusionElement apply = new FusionElement("apply");
		application.addElement(apply);
		
		apply.addAttribute("toObject", toObject);
		apply.addAttribute("styles", styles);
	}
	
	/**
	 * 创建一个DOM4J的Element对象。<br/>
	 * Element对象的属性由FusionElement对象的属性指定。<br/>
	 * Element对象的子元素由FusionElement对象的子元素指定。
	 * 
	 * @return dom4jElement 创建一个DOM4J的Element对象。
	 */
	public Element createDOM4JElement() throws NoElementNameError {
		Element dom4jElement = super.createDOM4JElement();
		//设置definition
		dom4jElement.add(definition.createDOM4JElement());
		//设置application
		dom4jElement.add(application.createDOM4JElement());
		return dom4jElement;
	}
}
