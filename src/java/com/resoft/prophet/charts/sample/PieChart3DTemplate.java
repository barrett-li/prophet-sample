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
import org.jfree.data.general.DefaultPieDataset;

import com.resoft.prophet.charts.jfreechart.AbstractChartTemplate;
import com.resoft.prophet.charts.jfreechart.ChartTemplate;

/**
 * PicChart3D模板类。
 * 
 * @author 韦海晗
 */
public class PieChart3DTemplate extends AbstractChartTemplate implements ChartTemplate {
	DefaultPieDataset dataSet = new DefaultPieDataset();

	public void setValue(String key, double value) {
		dataSet.setValue(key, value);
	}

	public void setDataSet(DefaultPieDataset dataSet) {
		this.dataSet = dataSet;
	}

	/**
	 * 用于创建和初始化JFreeChart图表对象。<br/>
	 * 
	 * @param params 处理参数，参数说明如下：<br/>
	 * params.title 图表标题
	 * params.legend 是否显示图例(对于简单的柱状图通常是false)
	 * params.tooltips 是否生成工具
	 * params.urls 是否生成URL链接
	 * params.onclick 单击柱状区域执行的javascript方法
	 * 
	 * @return JFreeChart图表对象
	 */
	public JFreeChart createChart(Map params) {
		String title = (String) params.get("title");
		boolean legend = booleanValue(params.get("legend"));
		boolean tooltips = booleanValue(params.get("tooltips"));
		boolean urls = booleanValue(params.get("urls"));

		JFreeChart chart = ChartFactory.createPieChart3D(title, // 图表标题
				dataSet, // 数据集
				legend, // 是否显示图例(对于简单的柱状图通常是false)
				tooltips, // 是否生成工具
				urls // 是否生成URL链接
				);

		return chart;
	}
	
}
