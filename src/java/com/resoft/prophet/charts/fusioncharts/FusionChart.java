/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

/**
 * FusionChart对象用于映射FusionCharts配置文件中的chart元素，FusionChart对象用于映射FusionCharts配置文件中的chart元素。<br/>
 * 
 * @author 韦海晗
 */
public class FusionChart extends FusionElement {
	public final static String ELEMENT_NAME = "chart";
	
	/**
	 * 构造一个新的FusionChart，它表示一个FusionCharts配置文件中chart的元素。
	 */
	public FusionChart() {
		setName(ELEMENT_NAME);
	}
	
	/**
	 * 构造一个新的FusionChart，它表示一个FusionCharts配置文件中chart的元素，它包含用于设置chart元素的一组属性。
	 * 
	 * @param caption 图表的标题。
	 * @param xAxisName X轴的标题。
	 * @param yAxisName Y轴的标题。
	 * @param numberPrefix 图表中数值的前缀。
	 * @param showValues 是否显示图表上显示数值。
	 */
	public FusionChart(String caption, String xAxisName, String yAxisName, String numberPrefix, Boolean showValues) {
		setName(ELEMENT_NAME);
		
		addAttribute("caption", caption);
		addAttribute("xAxisName", xAxisName);
		addAttribute("yAxisName", yAxisName);
		addAttribute("numberPrefix", numberPrefix);
		addAttribute("showValues", showValues);
	}
	
	/**
	 * 将FusionChart转换为FusionCharts配置字符串，默认字符集编码为UTF-8。
	 * 
	 * @return str - FusionCharts配置字符串。
	 */
	public String toXML() {
		return toXML("UTF-8");
	}
	
	/**
	 * 将FusionChart转换为指定字符集的FusionCharts配置字符串。
	 * 
	 * @param charsetName 字符集编码。
	 * @return str FusionCharts配置字符串。
	 */
	public String toXML(String charsetName) {
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding(charsetName);
		document.add(createDOM4JElement());
		return document.asXML();
	}
}
