/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.jfreechart;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.servlet.ServletUtilities;

/**
 * ChartImageInfoChart工厂，用于生成Chart的显示信息。
 * 
 * @author 韦海晗
 */
public class ChartImageInfoFactory {
	private String displayChartServlet;

	/**
	 * 设置显示Chart的Servlet路径。<br/>
	 * 
	 * @param displayChartServlet 显示Chart的Servlet路径
	 */
	public void setDisplayChartServlet(String displayChartServlet) {
		this.displayChartServlet = displayChartServlet;
	}

	/**
	 * 获取Chart的显示信息。<br/>
	 * 
	 * @param chart JFreeChart图表对象
	 * @param name 图表名称
	 * @param request HttpServletRequest
	 * @param height 图表高度
	 * @param width 图表宽度
	 * 
	 * @return Chart的显示信息
	 * 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * @throws NumberFormatException, IOException
	 */
	public ChartImageInfo getChartImageInfo(JFreeChart chart, String name, HttpServletRequest request, int width, int height)
			throws NumberFormatException, IOException {
		StandardEntityCollection sec = new StandardEntityCollection();
		ChartRenderingInfo crinfo = new ChartRenderingInfo(sec);

		String filename = ServletUtilities.saveChartAsJPEG(chart, width, height, crinfo, request.getSession());
		String usemapMessage = ChartUtilities.getImageMap(filename, crinfo);
		String usemapName = "#" + filename;
		StringBuffer srcBuf = new StringBuffer(request.getContextPath());
		srcBuf.append(displayChartServlet);
		srcBuf.append("?filename=");
		srcBuf.append(filename);

		ChartImageInfo chartImageInfo = new ChartImageInfo();
		chartImageInfo.setName(name);
		chartImageInfo.setHeight(String.valueOf(height));
		chartImageInfo.setWidth(String.valueOf(width));
		chartImageInfo.setSrc(srcBuf.toString());
		chartImageInfo.setUsemapContent(usemapMessage);
		chartImageInfo.setUsemapName(usemapName);

		return chartImageInfo;
	}
}
