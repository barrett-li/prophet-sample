/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.jfreechart;

import java.util.Map;

import org.jfree.chart.JFreeChart;

/**
 * Chart处理器接口，用于处理JFreeChart图表对象。
 * 
 * @author 韦海晗
 */
public interface ChartProcessor {
	
	/**
	 * 处理JFreeChart图表对象。<br/>
	 * 
	 * @param chart JFreeChart图表对象
	 * @param params 处理参数
	 */
	public void processChart(JFreeChart chart, Map params);
}
