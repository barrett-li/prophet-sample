/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.fusioncharts;

import com.resoft.prophet.charts.fusioncharts.FusionChart;
import com.resoft.prophet.charts.fusioncharts.Line;
import com.resoft.prophet.charts.fusioncharts.Set;
import com.resoft.prophet.charts.fusioncharts.Style;
import com.resoft.prophet.charts.fusioncharts.Styles;
import com.resoft.prophet.charts.fusioncharts.TrendLines;

import junit.framework.TestCase;

/**
 * FusionChart单元测试类
 * 
 * @author 韦海晗
 */
public class FusionChartTest extends TestCase {
	private FusionChart fusionChart;

	protected void setUp() throws Exception {
		fusionChart = new FusionChart();

		// 设置<chart/>属性
		fusionChart.addAttribute("caption", "月收入");
		fusionChart.addAttribute("xAxisName", "月份");
		fusionChart.addAttribute("yAxisName", "收入");
		fusionChart.addAttribute("numberPrefix", "%A5");
		fusionChart.addAttribute("showValues", "0");
		fusionChart.addAttribute("labelDisplay", "NONE");
		fusionChart.addAttribute("baseFont", "黑体");

		// 当使用特定的字符集时，y-axis名称使用旋转模式
		fusionChart.addAttribute("rotateYAxisName", "0");

		// 增加<set/>
		fusionChart.addElement(new Set("一月", new Integer("420000")));
		fusionChart.addElement(new Set("二月", new Integer("910000")));
		fusionChart.addElement(new Set("三月", new Integer("720000")));
		fusionChart.addElement(new Set("四月", new Integer("550000")));
		fusionChart.addElement(new Set("五月", new Integer("810000")));
		fusionChart.addElement(new Set("六月", new Integer("510000")));
		fusionChart.addElement(new Set("七月", new Integer("680000")));
		fusionChart.addElement(new Set("八月", new Integer("620000")));
		fusionChart.addElement(new Set("九月", new Integer("610000")));
		fusionChart.addElement(new Set("十月", new Integer("490000")));
		fusionChart.addElement(new Set("十一月", new Integer("530000")));
		fusionChart.addElement(new Set("十二月", new Integer("330000")));

		// 设置<trendLines/>
		TrendLines trendLines = new TrendLines();
		// 增加<line/>
		trendLines.addElement(new Line(new Integer("700000"), "Target", "009933"));
		fusionChart.addElement(trendLines);

		// 设置<styles/>
		Styles styles = new Styles();
		Style style = new Style("CanvasAnim", Style.STYLETYPE_ANIMATION);
		style.addAttribute("param", "_xScale");
		style.addAttribute("start", "0");
		style.addAttribute("duration", "1");
		styles.definitionStyle(style);
		styles.applyStyles("Canvas", "CanvasAnim");
		fusionChart.addElement(styles);
	}

	public void testToXML() {
		System.out.println(fusionChart.toXML("GBK"));
	}
}
