/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

/**
 * FusionCharts使用XML文件创建和操作图表，DataSet对象用于映射FusionCharts配置文件中的dataset元素。<br/>
 * 每个dataset元素包含一组数据。
 * 
 * @author 韦海晗
 */
public class DataSet extends FusionElement {
	public final static String ELEMENT_NAME = "dataset";
	
	/**
	 * 构造一个新的DataSet，它表示一个FusionCharts配置文件中dataset的元素。
	 */
	public DataSet() {
		setName(ELEMENT_NAME);
	}
	
	/**
	 * 构造一个新的DataSet，它表示一个FusionCharts配置文件中dataset的元素，它包含用于设置dataset元素的一组属性。
	 * 
	 * @param seriesName 数据序列的名称。
	 */
	public DataSet(String seriesName) {
		setName(ELEMENT_NAME);
		
		addAttribute("seriesName", seriesName);
	}
	
	/**
	 * 构造一个新的DataSet，它表示一个FusionCharts配置文件中dataset的元素，它包含用于设置dataset元素的一组属性。
	 * 
	 * @param seriesName 数据序列的名称。
	 * @param color 该属性用于设置圆柱、线和用data-set划分的区域的颜色。
	 * @param alpha 该属性用于设置整个data-set的透明度。
	 * @param ratio 如果你选择显示圆柱颜色渐变，该属性设置颜色的比率。 
	 * @param showValues 是否显示data-set的数值。
	 * @param dashed 是否将显示data-set成虚线。
	 * @param includeInLegend 是否将数据序列的名称显示到data-set的图例中。
	 */
	public DataSet(String seriesName, String color, String alpha, String ratio, Boolean showValues, Boolean dashed, Boolean includeInLegend) {
		setName(ELEMENT_NAME);
		
		addAttribute("seriesName", seriesName);
		addAttribute("color", color);
		addAttribute("alpha", alpha);
		addAttribute("ratio", ratio);
		addAttribute("showValues", showValues);
		addAttribute("dashed", dashed);
		addAttribute("includeInLegend", includeInLegend);
	}
}
