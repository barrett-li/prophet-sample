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
 * Chart模板接口，用于创建JFreeChart图表对象，使用ChartProcessor处理Chart，<br/>
 * 并生成Chart显示信息。
 * 
 * @author 韦海晗
 */
public interface ChartTemplate {
	
	/**
	 * 设置Chart处理器。<br/>
	 * 
	 * @param chartProcessor Chart处理器
	 */
	public void setChartProcessor(ChartProcessor chartProcessor);
	
	/**
	 * 设置ChartImageInfoChart工厂，用于生成Chart的显示信息。<br/>
	 * 
	 * @param chartProcessor Chart处理器
	 */
	public void setChartImageInfoFactory(ChartImageInfoFactory chartImageInfoFactory);
	
	/**
	 * 获取Chart。<br/>
	 * 
	 * @return JFreeChart图表对象
	 */
	public JFreeChart getChart();
	
	/**
	 * 获取Chart的显示信息。<br/>
	 * 
	 * @return Chart的显示信息
	 */
	public ChartImageInfo getChartImageInfo();
	
	/**
	 * 创建JFreeChart，执行处理器和生成ChartImageInfo对象。<br/>
	 * 
	 * @param name 图表名称
	 * @param request HttpServletRequest
	 * @param height 图表高度
	 * @param width 图表宽度
	 * @param params 处理参数
	 * 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * @throws NumberFormatException, IOException
	 */
	public void execute(String name, HttpServletRequest request, int width, int height, Map params) throws NumberFormatException, IOException;
	
	/**
	 * 用于创建和初始化JFreeChart图表对象。<br/>
	 * 
	 * @param params 处理参数
	 * 
	 * @return JFreeChart图表对象
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public JFreeChart createChart(Map params);
}
