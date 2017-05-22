/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.sample;

import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.resoft.prophet.charts.jfreechart.AbstractChartTemplate;
import com.resoft.prophet.charts.jfreechart.ChartTemplate;

/**
 * BarChart3D模板类。
 * 
 * @author 韦海晗
 */
public class BarChart3DTemplate extends AbstractChartTemplate implements ChartTemplate {
	protected DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

	public void addValue(double value, String series, String category) {
		dataSet.addValue(value, series, category);
	}

	public void setDataSet(DefaultCategoryDataset dataSet) {
		this.dataSet = dataSet;
	}

	/**
	 * 用于创建和初始化JFreeChart图表对象。<br/>
	 * 
	 * @param params 处理参数，参数说明如下：<br/>
	 * params.title 图表标题
	 * params.categoryAxisLabel 目录轴的显示标签
	 * params.valueAxisLabel 数值轴的显示标签
	 * params.orientation 图表方向：水平、垂直
	 * params.legend 是否显示图例(对于简单的柱状图通常是false)
	 * params.tooltips 是否生成工具
	 * params.urls 是否生成URL链接
	 * params.onclick 单击柱状区域执行的javascript方法
	 * 
	 * @return JFreeChart图表对象
	 */
	public JFreeChart createChart(Map params) {
		String title = (String) params.get("title");
		String categoryAxisLabel = (String) params.get("categoryAxisLabel");
		String valueAxisLabel = (String) params.get("valueAxisLabel");
		PlotOrientation orientation = (PlotOrientation) params.get("orientation");
		boolean legend = booleanValue(params.get("legend"));
		boolean tooltips = booleanValue(params.get("tooltips"));
		boolean urls = booleanValue(params.get("urls"));

		JFreeChart chart = ChartFactory.createBarChart3D(title, // 图表标题
				categoryAxisLabel, // 目录轴的显示标签
				valueAxisLabel, // 数值轴的显示标签
				dataSet, // 数据集
				orientation, // 图表方向：水平、垂直
				legend, // 是否显示图例(对于简单的柱状图通常是false)
				tooltips, // 是否生成工具
				urls // 是否生成URL链接
				);

		return chart;
	}
	
}
