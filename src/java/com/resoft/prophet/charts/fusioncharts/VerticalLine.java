/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

/**
 * FusionCharts使用XML文件创建和操作图表，VerticalLine对象用于映射FusionCharts配置文件中的vLine元素。<br/>
 * 每个vLine元素表示数据分隔线，用于帮助你分隔数据区域。
 * 
 * @author 韦海晗
 */
public class VerticalLine extends FusionElement {
	public final static String ELEMENT_NAME = "vLine";
	
	/**
	 * 构造一个新的VerticalLine，它表示一个FusionCharts配置文件中vLine的元素。
	 */
	public VerticalLine() {
		setName(ELEMENT_NAME);
	}
	
	/**
	 * 构造一个新的VerticalLine，它表示一个FusionCharts配置文件中vLine的元素，它包含用于设置vLine元素的一组属性。
	 * 
	 * @param color vertical-line的颜色。
	 * @param thickness vertical-line的厚度。
	 */
	public VerticalLine(String color, Number thickness) {
		setName(ELEMENT_NAME);

		addAttribute("color", color);
		addAttribute("thickness", thickness);
	}
	
	/**
	 * 构造一个新的VerticalLine，它表示一个FusionCharts配置文件中vLine的元素，它包含用于设置vLine元素的一组属性。
	 * 
	 * @param color vertical-line的颜色。
	 * @param thickness vertical-line的厚度。
	 * @param alpha vertical-line的透明度。
	 * @param dashed 是否将vertical-line显示成虚线。
	 * @param dashLen 如果你选择vertical-line显示成虚线，该属性设置虚线的长度。
	 * @param dashGap 如果你选择vertical-line显示成虚线，该属性设置虚线的间隔。
	 */
	public VerticalLine(String color, Number thickness, Number alpha, Boolean dashed, Number dashLen, Number dashGap) {
		setName(ELEMENT_NAME);

		addAttribute("color", color);
		addAttribute("thickness", thickness);
		addAttribute("alpha", alpha);
		addAttribute("dashed", dashed);
		addAttribute("dashLen", dashLen);
		addAttribute("dashGap", dashGap);
	}
}
