/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

/**
 * FusionCharts v3 引入Styles可以帮助你在图表中设置字体，应用动态效果、渐变等各种特效，Style对象用于映射FusionCharts配置文件中的style元素。<br/>
 * style元素定义一个样式对象，该对象要定义一个唯一的名称，你可以将该样式对象应用在图表不同的元素中。
 * 
 * @author 韦海晗
 */
public class Style extends FusionElement {
	public final static String ELEMENT_NAME = "style";
	
	public final static String STYLETYPE_FONT = "Font";
	public final static String STYLETYPE_ANIMATION = "Animation";
	public final static String STYLETYPE_SHADOW = "Shadow";
	public final static String STYLETYPE_GLOW = "Glow";
	public final static String STYLETYPE_BEVEL = "Bevel";
	public final static String STYLETYPE_BLUR = "Blur";
	
	/**
	 * 构造一个新的Style，它表示一个FusionCharts配置文件中style的元素。
	 */
	public Style() {
		setName(ELEMENT_NAME);
	}
	
	/**
	 * 构造一个新的Style，它表示一个FusionCharts配置文件中style的元素，它包含用于设置style元素的一组属性。
	 * 
	 * @param name 样式对象的名称。
	 * @param type 样式对象的类型。
	 */
	public Style(String name, String type) {
		setName(ELEMENT_NAME);
		
		addAttribute("name", name);
		addAttribute("type", type);
	}
}
