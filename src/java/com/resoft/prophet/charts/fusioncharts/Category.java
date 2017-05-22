/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

/**
 * FusionCharts使用XML文件创建和操作图表，Category对象用于映射FusionCharts配置文件中的category元素。<br/>
 * 每个category元素表示一个X轴标签。
 * 
 * @author 韦海晗
 */
public class Category extends FusionElement {
	public final static String ELEMENT_NAME = "category";
	
	/**
	 * 构造一个新的Category，它表示一个FusionCharts配置文件中category的元素。
	 */
	public Category() {
		setName(ELEMENT_NAME);
	}

	/**
	 * 构造一个新的Category，它表示一个FusionCharts配置文件中category的元素，它包含用于设置category元素的一组属性。
	 * @param label 数据项的标注。
	 */
	public Category(String label) {
		setName(ELEMENT_NAME);
		
		addAttribute("label", label);
	}
	
	/**
	 * 构造一个新的Category，它表示一个FusionCharts配置文件中category的元素，它包含用于设置category元素的一组属性。
	 * @param label 数据项的标注。
	 * @param showLabel 是否显示标注。
	 * @param toolText 数据项的工具提示栏的内容。
	 */
	public Category(String label, Boolean showLabel, String toolText) {
		setName(ELEMENT_NAME);
		
		addAttribute("label", label);
		addAttribute("showLabel", showLabel);
		addAttribute("toolText", toolText);
	}
}
