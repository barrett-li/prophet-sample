/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.sample;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.urls.CustomCategoryURLGenerator;
import org.jfree.data.category.CategoryDataset;

import com.resoft.prophet.charts.jfreechart.ChartProcessor;

/**
 * BarChart3D处理器，用于设置BarChart3D的样式和字体等。
 * 
 * @author 韦海晗
 */
public class BarChart3DProcessor implements ChartProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.charts.jfreechart.ChartProcessor#processChart(org.jfree.chart.JFreeChart, java.util.Map)
	 */
	public void processChart(JFreeChart chart, Map params) {
		// 设置字体，显示中文
		Font titleFont = new Font("SIMSUN", Font.PLAIN, 15);
		Font axisFont = new Font("SIMSUN", Font.PLAIN, 13);
		Font labelFont = new Font("SIMSUN", Font.PLAIN, 12);

		// 设置标题字体
		chart.getTitle().setFont(titleFont);

		// 设置图例字体
		LegendTitle legend = chart.getLegend();
		if (legend != null) {
			legend.setItemFont(labelFont);
		}

		// 获得图表区域对象
		CategoryPlot plot = chart.getCategoryPlot();
		// 设置图表的横轴的属性
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(axisFont);
		domainAxis.setTickLabelFont(labelFont);

		// 设置图表的纵轴的属性
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(axisFont);

		BarRenderer3D renderer = new BarRenderer3D();
		CustomCategoryURLGenerator categoryURLGenerator = new CustomCategoryURLGenerator();

		// 设定连接
		// 当单击柱状区域执行的javascript方法
		Object onclick = params.get("onclick");
		if (onclick != null) {
			CategoryDataset data = plot.getDataset();
			int rowCount = data.getRowCount();
			int columnCount = data.getColumnCount();
			for (int i = 0; i < rowCount; i++) {
				String rowKey = (String) data.getRowKey(i);
				List series = new ArrayList();
				for (int j = 0; j < columnCount; j++) {
					String columnKey = (String) data.getColumnKey(j);
					StringBuffer buf = new StringBuffer("javascript: ");
					buf.append(onclick);
					buf.append("(");
					buf.append(data.getValue(i, j));
					buf.append(", \'");
					buf.append(rowKey);
					buf.append("\'");
					buf.append(", \'");
					buf.append(columnKey);
					buf.append("\');");
					series.add(buf.toString());
				}
				categoryURLGenerator.addURLSeries(series);
			}
			renderer.setBaseItemURLGenerator(categoryURLGenerator);
			renderer.setBaseItemLabelFont(labelFont);
		}

		// 显示每个柱的数值，并修改该数值的字体属性
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelFont(labelFont);
		renderer.setBaseItemLabelsVisible(true);

		// 使用设计的效果
		plot.setRenderer(renderer);
	}

}
