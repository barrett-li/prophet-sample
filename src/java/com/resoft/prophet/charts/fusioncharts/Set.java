/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

/**
 * FusionCharts使用XML文件创建和操作图表，Set对象用于映射FusionCharts配置文件中的set元素。<br/>
 * 每个set元素(chart或dataset元素的子元素)表示被用于划分图表上区域的数据集合。
 * 
 * @author 韦海晗
 */
public class Set extends FusionElement {
	public final static String ELEMENT_NAME = "set";

	/**
	 * 构造一个新的Set，它表示一个FusionCharts配置文件中set的元素。
	 */
	public Set() {
		setName(ELEMENT_NAME);
	}
	
	/**
	 * 构造一个新的Set，它表示一个FusionCharts配置文件中set的元素，它包含用于设置set元素的一组属性。
	 * 
	 * @param label 数据项的标注。
	 */
	public Set(String label) {
		setName(ELEMENT_NAME);
		
		addAttribute("label", label);
	}
	
	/**
	 * 构造一个新的Set，它表示一个FusionCharts配置文件中set的元素，它包含用于设置set元素的一组属性。
	 * 
	 * @param label 数据项的标注。
	 * @param link 设置单击单个数据项的链接。
	 */
	public Set(String label, String link) {
		setName(ELEMENT_NAME);
		
		addAttribute("label", label);
		addAttribute("link", link);
	}
	
	/**
	 * 构造一个新的Set，它表示一个FusionCharts配置文件中set的元素，它包含用于设置set元素的一组属性。
	 * 
	 * @param label 数据项的标注。
	 * @param value 数据项的数值。
	 */
	public Set(String label, Number value) {
		setName(ELEMENT_NAME);
		
		addAttribute("label", label);
		addAttribute("value", value);
	}
	
	/**
	 * 构造一个新的Set，它表示一个FusionCharts配置文件中set的元素，它包含用于设置set元素的一组属性。
	 * 
	 * @param label 数据项的标注。
	 * @param value 数据项的数值。
	 * @param link 设置单击单个数据项的链接。
	 */
	public Set(String label, Number value, String link) {
		setName(ELEMENT_NAME);
		
		addAttribute("label", label);
		addAttribute("value", value);
		addAttribute("link", link);
	}

	/**
	 * 构造一个新的Set，它表示一个FusionCharts配置文件中set的元素，它包含用于设置set元素的一组属性。
	 * 
	 * @param value 数据项的数值。
	 * @param color 数据项的颜色。
	 * @param link 设置单击单个数据项的链接。
	 * @param toolText 数据项的工具提示栏的内容。
	 * @param showValue 是否显示dataset的数值。
	 * @param dashed 是否将显示dataset成虚线。
	 * @param alpha 该属性用于设置整个dataset的透明度。
	 */
	public Set(Number value, String color, String link, String toolText, Boolean showValue, Boolean dashed, Number alpha) {
		setName(ELEMENT_NAME);
		
		addAttribute("value", value);
		addAttribute("color", color);
		addAttribute("link", link);
		addAttribute("toolText", toolText);
		addAttribute("showValue", showValue);
		addAttribute("dashed", dashed);
		addAttribute("alpha", alpha);
	}
}
