/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

/**
 * FusionCharts使用XML文件创建和操作图表，Categories对象用于映射FusionCharts配置文件中的categories元素。<br/>
 * categories元素允许你捆绑图表的X轴标签。
 * 
 * @author 韦海晗
 */
public class Categories extends FusionElement {
	public final static String ELEMENT_NAME = "categories";
	
	/**
	 * 构造一个新的Categories，它表示一个FusionCharts配置文件中categories的元素。
	 */
	public Categories() {
		setName(ELEMENT_NAME);
	}
	
	/**
	 * 构造一个新的Categories，它表示一个FusionCharts配置文件中categories的元素，它包含用于设置categories元素的一组属性。
	 * 
	 * @param font X轴的数据标签的字体。
	 * @param fontSize X轴的数据标签的字体大小。
	 * @param fontColor X轴的数据标签的字体颜色。
	 */
	public Categories(String font, Number fontSize, String fontColor) {
		setName(ELEMENT_NAME);
		
		addAttribute("font", font);
		addAttribute("fontSize", fontSize);
		addAttribute("fontColor", fontColor);
	}
}
