/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.sample.web;

import com.resoft.prophet.charts.fusioncharts.FusionChart;
import com.resoft.prophet.charts.fusioncharts.Set;
import com.resoft.prophet.charts.web.FusionChartAction;

/**
 * 为Column2D Chart提供远程XML数据
 * 
 * @author 韦海晗
 */
public class Column2DAction extends FusionChartAction {
	private static final long serialVersionUID = -6982349951790776827L;
	
	/**
	 * 返回用于Chart数据的模版方法。<br/>
	 * 当WebWork框架调用时执行该方法。
	 * 
	 * @return 用于Column2DAction Charts的XML数据的内容文本
	 */
	protected String getXML() {
		FusionChart fusionChart = new FusionChart();
		
		// 设置<chart/>属性
		fusionChart.addAttribute("palette", "2");
		fusionChart.addAttribute("caption", "单位销售量");
		fusionChart.addAttribute("xAxisName", "月份");
		fusionChart.addAttribute("yAxisName", "单位");
		fusionChart.addAttribute("showValues", "0");
		fusionChart.addAttribute("decimals", "0");
		fusionChart.addAttribute("formatNumberScale", "0");
		fusionChart.addAttribute("useRoundEdges", "1");
		fusionChart.addAttribute("baseFont", "黑体");
		fusionChart.addAttribute("rotateYAxisName", "0");

		// 增加<set/>
		fusionChart.addElement(new Set("一月", new Integer("462")));
		fusionChart.addElement(new Set("二月", new Integer("857")));
		fusionChart.addElement(new Set("三月", new Integer("671")));
		fusionChart.addElement(new Set("四月", new Integer("494")));
		fusionChart.addElement(new Set("五月", new Integer("761")));
		fusionChart.addElement(new Set("六月", new Integer("960")));
		
		return fusionChart.toXML("GBK");
	}
}
