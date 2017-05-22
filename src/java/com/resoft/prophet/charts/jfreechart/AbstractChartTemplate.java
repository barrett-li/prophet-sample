/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.jfreechart;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.JFreeChart;

/**
 * ChartTemplate抽象类，用于创建JFreeChart图表对象，使用ChartProcessor处理Chart，<br/> 并生成Chart显示信息。
 * 
 * @author 韦海晗
 */
public class AbstractChartTemplate implements ChartTemplate {
	private JFreeChart chart;

	private ChartProcessor chartProcessor;

	private ChartImageInfoFactory chartImageInfoFactory;

	private ChartImageInfo chartImageInfo;

	public void setChartProcessor(ChartProcessor chartProcessor) {
		this.chartProcessor = chartProcessor;
	}

	public void setChartImageInfoFactory(ChartImageInfoFactory chartImageInfoFactory) {
		this.chartImageInfoFactory = chartImageInfoFactory;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public ChartImageInfo getChartImageInfo() {
		return chartImageInfo;
	}

	public void execute(String name, HttpServletRequest request, int width, int height, Map params) throws NumberFormatException, IOException {
		chart = createChart(params);
		chartProcessor.processChart(chart, params);
		chartImageInfo = chartImageInfoFactory.getChartImageInfo(chart, name, request, width, height);
	}
	
	/**
	 * 用于创建和初始化JFreeChart图表对象。<br/>
	 * 
	 * @param params 处理参数
	 * 
	 * @return JFreeChart图表对象
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public JFreeChart createChart(Map params) {
		return null;
	}
	
	/**
	 * 将此指定的对象转换为boolean。如果对象为null，则返回false
	 * 
	 * @param obj 需要转换的对象。
	 * @return boolean值
	 */
	protected boolean booleanValue(Object obj) {
		boolean bln = false;
		if (obj != null && obj instanceof Boolean) {
			bln =  ((Boolean) obj).booleanValue();
		}
		return bln;
	}
}
