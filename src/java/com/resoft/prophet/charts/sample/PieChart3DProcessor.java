/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.sample;

import java.awt.Font;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.urls.CustomPieURLGenerator;
import org.jfree.data.general.PieDataset;

import com.resoft.prophet.charts.jfreechart.ChartProcessor;

/**
 * PicChart3D处理器，用于设置PicChart3D的样式和字体等。
 * 
 * @author 韦海晗
 */
public class PieChart3DProcessor implements ChartProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.resoft.prophet.charts.jfreechart.ChartProcessor#processChart(org.jfree.chart.JFreeChart, java.util.Map)
	 */
	public void processChart(JFreeChart chart, Map params) {
		// 设置字体，显示中文
		Font titleFont = new Font("SIMSUN", Font.PLAIN, 15);
		Font labelFont = new Font("SIMSUN", Font.PLAIN, 12);
		// 设置标题字体
		chart.getTitle().setFont(titleFont);
		
		// 设置图例字体
		LegendTitle legend = chart.getLegend();
		if (legend != null) {
			legend.setItemFont(labelFont);
		}
		
		// 获得图表区域对象
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setLabelFont(labelFont);
		// 饼图显示提示信息
		plot.setToolTipGenerator(new StandardPieToolTipGenerator());
		// 饼图显示百分比
		// plot.setLabelGenerator(new StandardPieSectionLabelGenerator(StandardPieSectionLabelGenerator.DEFAULT_SECTION_LABEL_FORMAT));
		// 或者采用下面自定义样式显示，{0}表示选项，{1}表示数值，{2}表示所占比例
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: ({1}, {2})"));
		// 设定背景透明度（0-1.0之间）
		plot.setBackgroundAlpha(0.4f);
		// 设定前景透明度（0-1.0之间）
		plot.setForegroundAlpha(0.6f);
		
		// 设定连接
		// 当单击柱状区域执行的javascript方法
		Object onclick = params.get("onclick");
		if (onclick != null) {
			PieDataset data = plot.getDataset();
			List keys = data.getKeys();
			Map map = new HashMap();
			for (Iterator iter = keys.iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				StringBuffer buf = new StringBuffer("javascript: ");
				buf.append(onclick);
				buf.append("(\'");
				buf.append(key);
				buf.append("\', ");
				buf.append(data.getValue(key));
				buf.append(");");
				map.put(key, buf.toString());
			}
			CustomPieURLGenerator cpu = new CustomPieURLGenerator();
			cpu.addURLs(map);
			plot.setURLGenerator(cpu);
		}
	}

}
