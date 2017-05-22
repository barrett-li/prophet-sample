/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

/**
 * FusionCharts使用XML文件创建和操作图表，Line对象用于映射FusionCharts配置文件中的line元素。<br/>
 * trendLines元素和其子元素line，用于在图表中定义趋势线。
 * 
 * @author 韦海晗
 */
public class Line extends FusionElement {
	public final static String ELEMENT_NAME = "line";

	/**
	 * 构造一个新的Line，它表示一个FusionCharts配置文件中line的元素。
	 */
	public Line() {
		setName(ELEMENT_NAME);
	}
	
	/**
	 * 构造一个新的Line，它表示一个FusionCharts配置文件中line的元素，它包含用于设置line元素的一组属性。
	 * 
	 * @param startValue trend-line的开始值。
	 * @param displayValue 是否显示trend-line的值。
	 * @param color trend-line的颜色。
	 */
	public Line(Number startValue, String displayValue, String color) {
		setName(ELEMENT_NAME);
		
		addAttribute("startValue", startValue);
		addAttribute("displayValue", displayValue);
		addAttribute("color", color);
	}

	/**
	 * 构造一个新的Line，它表示一个FusionCharts配置文件中line的元素，它包含用于设置line元素的一组属性。
	 * 
	 * @param startValue trend-line的初始值。
	 * @param endValue trend-line的结束值。
	 * @param displayValue 是否显示trend line的值。
	 * @param color  trend-line的颜色。
	 * @param isTrendZone 是否显示trend-line/zone。
	 * @param showOnTop 是否在trend-line下显示数据工具栏。
	 * @param thickness trend-line的厚度。
	 * @param alpha trend-line的透明度。
	 * @param dashed 是否将trend-line显示成虚线。
	 * @param dashLen 如果你选择trend-line显示成虚线，该属性设置虚线的长度。
	 * @param dashGap 如果你选择trend-line显示成虚线，该属性设置虚线的间隔。
	 * @param valueOnRight 是否将trend-line的值显示图表的左侧或右侧。
	 */
	public Line(Number startValue, 
			Number endValue, 
			String displayValue, 
			String color, 
			Boolean isTrendZone, 
			Boolean showOnTop, 
			Number thickness, 
			Number alpha, 
			Boolean dashed, 
			Number dashLen, 
			Number dashGap, 
			Boolean valueOnRight) {
		setName(ELEMENT_NAME);
		
		addAttribute("startValue", startValue);
		addAttribute("endValue", endValue);
		addAttribute("displayValue", displayValue);
		addAttribute("color", color);
		addAttribute("isTrendZone", isTrendZone);
		addAttribute("showOnTop", showOnTop);
		addAttribute("thickness", thickness);
		addAttribute("alpha", alpha);
		addAttribute("dashed", dashed);
		addAttribute("dashLen", dashLen);
		addAttribute("dashGap", dashGap);
		addAttribute("valueOnRight", valueOnRight);
	}
}
