/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

/**
 * FusionCharts使用XML文件创建和操作图表，TrendLines对象用于映射FusionCharts配置文件中的trendLines元素。<br/>
 * trendLines元素和其子元素line，用于在图表中定义趋势线。
 * 
 * @author 韦海晗
 */
public class TrendLines extends FusionElement {
	public final static String ELEMENT_NAME = "trendLines";
	
	/**
	 * 构造一个新的TrendLines，它表示一个FusionCharts配置文件中trendLines的元素。
	 */
	public TrendLines() {
		setName(ELEMENT_NAME);
	}
}
