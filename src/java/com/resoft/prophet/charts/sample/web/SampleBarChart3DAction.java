/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.sample.web;

import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.plot.PlotOrientation;

import com.resoft.prophet.charts.jfreechart.ChartImageInfo;
import com.resoft.prophet.charts.sample.BarChart3DTemplate;
import com.resoft.prophet.web.WebWorkAction;

/**
 * 示例BarChart3D Action。
 * 
 * @author 韦海晗
 */
public class SampleBarChart3DAction extends WebWorkAction {
	private static final long serialVersionUID = -6421489562527020119L;
	private BarChart3DTemplate chartTemplate;
	private ChartImageInfo chartImageInfo;
	
	public void setChartTemplate(BarChart3DTemplate chartTemplate) {
		this.chartTemplate = chartTemplate;
	}
	
	public ChartImageInfo getChartImageInfo() {
		return chartImageInfo;
	}
	
	/**
	 * 生成示例BarChart3D。<br/>
	 * 
	 */
	public String execute() throws Exception {
		// 设置图表数据
		chartTemplate.addValue(370, "学历", "高中以下");
		chartTemplate.addValue(1530, "学历", "高中");
		chartTemplate.addValue(5700, "学历", "大专");
		chartTemplate.addValue(8280, "学历", "本科");
		chartTemplate.addValue(4420, "学历", "硕士");
		chartTemplate.addValue(80, "学历", "博士");

		Map params = new HashMap();
		// 设置图表参数
		params.put("title", "程序员学历情况调查表"); // 图表标题
        params.put("categoryAxisLabel", "学历"); // 目录轴的显示标签
        params.put("valueAxisLabel", "人数"); // 数值轴的显示标签
        params.put("orientation", PlotOrientation.VERTICAL); // 图表方向：水平、垂直
        params.put("legend", new Boolean(false)); // 是否显示图例(对于简单的柱状图通常是false)
        params.put("tooltips", new Boolean(true)); // 是否生成工具
        params.put("urls", new Boolean(true)); // 是否生成URL链接
        params.put("onclick", "getData"); // 单击柱状区域执行的javascript方法
        
        // 执行模板
        chartTemplate.execute("程序员学历情况调查表", getRequest(), 500, 300, params);
		// 获取显示信息
		chartImageInfo = chartTemplate.getChartImageInfo();
		return SUCCESS;
	}

}
