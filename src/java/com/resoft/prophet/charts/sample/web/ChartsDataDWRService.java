/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.charts.sample.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.chart.plot.PlotOrientation;

import com.resoft.prophet.charts.jfreechart.ChartImageInfo;
import com.resoft.prophet.charts.sample.BarChart3DTemplate;
import com.resoft.prophet.charts.sample.PieChart3DTemplate;
import com.resoft.prophet.charts.sample.domain.BankLoan;
import com.resoft.prophet.charts.sample.service.ChartsDataService;
import com.resoft.prophet.web.DWRService;

/**
 * 为图表提供远程数据，数据的内容如下：
 * 地区：
 * 北京，上海，深圳
 * 机构：
 * 中国银行，建设银行，工商银行，交通银行，农业银行
 * 指标：
 * 不良贷款总额，逾期贷款总额，违约贷款总额
 * 
 * @author 韦海晗
 */
public class ChartsDataDWRService extends DWRService {
	private ChartsDataService chartsDataService;

	private BarChart3DTemplate barChart3DTemplate;

	private PieChart3DTemplate pieChart3DTemplate;

	public void setChartsDataService(ChartsDataService chartsDataService) {
		this.chartsDataService = chartsDataService;
	}

	public void setBarChart3DTemplate(BarChart3DTemplate barChart3DTemplate) {
		this.barChart3DTemplate = barChart3DTemplate;
	}

	public void setPieChart3DTemplate(PieChart3DTemplate pieChart3DTemplate) {
		this.pieChart3DTemplate = pieChart3DTemplate;
	}
	
	/**
	 * 返回银行贷款数据数据集。
	 * 
	 * @param start 当前页起始行
	 * @param limit 当前页结束行
	 * @param sort 排序的列
	 * @param direction 排序顺序，'ASC'或者'DESC'
	 * @param root 表格数据集根节点键名，用于设置结果集的键
	 * @param totalProperty 表格数据集行数节点键名，用于设置行数的键
	 * @param successProperty 表示构造是否成功键名，用于设置构造成功的键
	 * @param params 查询参数
	 * 
	 * @return 银行贷款数据集，该数据集被json协议序列化后的表示： { 'totalProperty': 2, 'successProperty': true, 'root': [ { 'id': 1, 'name': 'Bill' }, { 'id': 2, 'name':
	 *         'Ben' } ] }
	 *         
	 * @exception Exception
	 */
	public Map getBankLoansDataset(Integer start, Integer limit, String sort, String direction, String root, String totalProperty, String successProperty, Map params) {
		String area = (String) params.get("area");
		String org = (String) params.get("org");

		List bankLoans = chartsDataService.getBankLoans(area, org, start, limit, sort, direction);
		Integer count = chartsDataService.countBankLoans(area, org);

		Map bankLoansMap = new HashMap();
		bankLoansMap.put(root, bankLoans);
		bankLoansMap.put(totalProperty, count);
		return bankLoansMap;
	}
	
	/**
	 * 返回银行贷款数据数据集和JFreeChart图表信息。
	 * 
	 * @param start 当前页起始行
	 * @param limit 当前页结束行
	 * @param sort 排序的列
	 * @param direction 排序顺序，'ASC'或者'DESC'
	 * @param root 表格数据集根节点键名，用于设置结果集的键
	 * @param totalProperty 表格数据集行数节点键名，用于设置行数的键
	 * @param chartsProperty JFreeChart图表信息节点键名，用于设置JFreeChart图表信息的键
	 * @param successProperty 表示构造是否成功键名，用于设置构造成功的键
	 * @param params 查询参数
	 * 
	 * @return 银行贷款数据集和JFreeChart图表信息，该数据集被json协议序列化后的表示： { 'totalProperty': 2, 'successProperty': true, 'root': [ { 'id': 1, 'name': 'Bill' }, { 'id': 2, 'name':
	 *         'Ben' } ], 'charts': [{height: '', width: '', src: '', usemapName: '', usemapContent: ''}] }
	 *         
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public Map getBankLoansChartDataset(Integer start, Integer limit, String sort, String direction, String root, String totalProperty, String chartsProperty, String successProperty, Map params) throws NumberFormatException, IOException {
		String area = (String) params.get("area");
		String org = (String) params.get("org");
		String width = (String) params.get("width");
		String height = (String) params.get("height");

		List bankLoans = chartsDataService.getBankLoans(area, org, start, limit, sort, direction);
		Integer count = chartsDataService.countBankLoans(area, org);

		Map bankLoansMap = new HashMap();
		bankLoansMap.put(root, bankLoans);
		bankLoansMap.put(totalProperty, count);
		// 获取设置JFreeChart图表信息
		bankLoansMap.put(chartsProperty, getBankLoansCharts(area, width, height, bankLoans));
		return bankLoansMap;
	}
	
	/**
	 * 返回银行贷款图表显示信息。
	 * 
	 * @param start 当前页起始行
	 * @param limit 当前页结束行
	 * @param sort 排序的列
	 * @param direction 排序顺序，'ASC'或者'DESC'
	 * @param root 表格数据集根节点键名，用于设置结果集的键
	 * @param totalProperty 表格数据集行数节点键名，用于设置行数的键
	 * @param successProperty 表示构造是否成功键名，用于设置构造成功的键
	 * @param params 查询参数
	 * 
	 * @return 银行贷款数据集和JFreeChart图表信息，该数据集被json协议序列化后的表示： { 'totalProperty': 2, 'successProperty': true, 'root': [ { 'id': 1, 'name': 'Bill' }, { 'id': 2, 'name':
	 *         'Ben' } ]}
	 *         
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public Map getBankLoansChartImageInfo(Integer start, Integer limit, String sort, String direction, String root, String totalProperty, String successProperty, Map params) throws NumberFormatException, IOException {
		String area = (String) params.get("area");
		String org = (String) params.get("org");
		String width = (String) params.get("width");
		String height = (String) params.get("height");

		List bankLoans = chartsDataService.getBankLoans(area, org, start, limit, sort, direction);

		Map bankLoansMap = new HashMap();
		bankLoansMap.put(root, getBankLoansCharts(area, width, height, bankLoans));
		return bankLoansMap;
	}
	
	/**
	 * 返回银行贷款PieChart图表显示信息。
	 * 
	 * @param start 当前页起始行
	 * @param limit 当前页结束行
	 * @param sort 排序的列
	 * @param direction 排序顺序，'ASC'或者'DESC'
	 * @param root 表格数据集根节点键名，用于设置结果集的键
	 * @param totalProperty 表格数据集行数节点键名，用于设置行数的键
	 * @param successProperty 表示构造是否成功键名，用于设置构造成功的键
	 * @param params 查询参数
	 * 
	 * @return 银行贷款数据集和JFreeChart图表信息，该数据集被json协议序列化后的表示： { 'totalProperty': 2, 'successProperty': true, 'root': [ { 'id': 1, 'name': 'Bill' }, { 'id': 2, 'name':
	 *         'Ben' } ]}
	 *         
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public Map getBankLoansPieChartImageInfo(Integer start, Integer limit, String sort, String direction, String root, String totalProperty, String successProperty, Map params) throws NumberFormatException, IOException {
		String area = (String) params.get("area");
		String org = (String) params.get("org");
		String width = (String) params.get("width");
		String height = (String) params.get("height");

		List bankLoans = chartsDataService.getBankLoans(area, org, start, limit, sort, direction);

		Map bankLoansMap = new HashMap();
		bankLoansMap.put(root, getBankLoansPieCharts(width, height, (BankLoan) bankLoans.get(0)));
		return bankLoansMap;
	}
	
	// 创建PieChart图表
	private ChartImageInfo[] getBankLoansPieCharts(String width, String height, BankLoan bankLoan) throws NumberFormatException, IOException {
		// 设置图表数据
		pieChart3DTemplate.setValue("不良贷款总额", bankLoan.getDeadLoans().doubleValue());
		pieChart3DTemplate.setValue("逾期贷款总额", bankLoan.getOverdueLoans().doubleValue());
		pieChart3DTemplate.setValue("违约贷款总额", bankLoan.getViolationLoans().doubleValue());

		String area = bankLoan.getArea();
		String org = bankLoan.getOrg();
		String title = area + "地区" + org + "不良贷款信息";

		Map params = new HashMap();
		// 设置图表参数
		params.put("title", title); // 图表标题
		params.put("legend", new Boolean(true)); // 是否显示图例
		params.put("tooltips", new Boolean(true)); // 是否生成工具
		params.put("urls", new Boolean(true)); // 是否生成URL链接
		params.put("onclick", "getPieChartData"); // 单击柱状区域执行的javascript方法

		// 执行模板
		pieChart3DTemplate.execute(title, getRequest(), Integer.parseInt(width), Integer.parseInt(height), params);
		ChartImageInfo[] charts = { pieChart3DTemplate.getChartImageInfo() };
		return charts;
	}
	
	// 创建BarChart图表
	private ChartImageInfo[] getBankLoansCharts(String area, String width, String height, List bankLoans) throws NumberFormatException, IOException {
		// 设置图表数据
		for (Iterator iter = bankLoans.iterator(); iter.hasNext();) {
			BankLoan bankLoan = (BankLoan) iter.next();
			barChart3DTemplate.addValue(bankLoan.getDeadLoans().doubleValue(), "不良贷款总额", bankLoan.getOrg());
			barChart3DTemplate.addValue(bankLoan.getOverdueLoans().doubleValue(), "逾期贷款总额", bankLoan.getOrg());
			barChart3DTemplate.addValue(bankLoan.getViolationLoans().doubleValue(), "违约贷款总额", bankLoan.getOrg());
		}

		Map params = new HashMap();
		// 设置图表参数
		params.put("title", area + "各大商业银行不良贷款信息"); // 图表标题
		params.put("valueAxisLabel", "万元"); // 数值轴的显示标签
		params.put("orientation", PlotOrientation.VERTICAL); // 图表方向：水平、垂直
		params.put("legend", new Boolean(true)); // 是否显示图例
		params.put("tooltips", new Boolean(true)); // 是否生成工具
		params.put("urls", new Boolean(true)); // 是否生成URL链接
		params.put("onclick", "getBarChartData"); // 单击柱状区域执行的javascript方法

		// 执行模板
		barChart3DTemplate.execute(area + "各大商业银行不良贷款信息", getRequest(), Integer.parseInt(width), Integer.parseInt(height), params);
		ChartImageInfo[] charts = { barChart3DTemplate.getChartImageInfo() };
		return charts;
	}

}
