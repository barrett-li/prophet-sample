<%--
Prophet 1.0
CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD

http://resoft.css.com.cn
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.awt.Color"%>
<%@ page import="java.awt.Font"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@ page import="org.jfree.chart.*"%>
<%@ page import="org.jfree.chart.plot.*"%>
<%@ page import="org.jfree.chart.servlet.ServletUtilities"%>
<%@ page import="org.jfree.chart.entity.StandardEntityCollection"%>
<%@ page import="org.jfree.chart.urls.CustomCategoryURLGenerator"%>
<%@ page import="org.jfree.chart.renderer.category.BarRenderer3D"%>
<%@ page import="org.jfree.chart.title.LegendTitle"%>
<%@ page import="org.jfree.chart.labels.StandardCategoryItemLabelGenerator"%>
<%@ page import="org.jfree.chart.axis.CategoryAxis"%>
<%@ page import="org.jfree.chart.axis.ValueAxis"%>
<%@ page import="com.resoft.prophet.charts.jfreechart.*"%>

<%@ include file="/templets/common/global.jsp"%>
<%@ include file="/templets/common/taglibs.jsp"%>
<%
	DefaultCategoryDataset data = new DefaultCategoryDataset();
	data.addValue(370, "学历", "高中以下");
	data.addValue(1530, "学历", "高中");
	data.addValue(5700, "学历", "大专");
	data.addValue(8280, "学历", "本科");
	data.addValue(4420, "学历", "硕士");
	data.addValue(80, "学历", "博士");

	// 设置连接的内容
	// series=学历; degree=高中以下, 高中, 大专, 本科, 硕士, 博士
	List series = new ArrayList();
	series.add("javascript: getData(370, \'高中以下\');");
	series.add("javascript: getData(1530, \'高中\');");
	series.add("javascript: getData(5700, \'大专\');");
	series.add("javascript: getData(8280, \'本科\');");
	series.add("javascript: getData(4420, \'硕士\');");
	series.add("javascript: getData(80, \'博士\');");

	JFreeChart chart = ChartFactory.createBarChart3D("程序员学历情况调查表", // 图表标题
	"学历", // 目录轴的显示标签
	"人数", // 数值轴的显示标签
	data, // 数据集
	PlotOrientation.VERTICAL, // 图表方向：水平、垂直
	false, // 是否显示图例(对于简单的柱状图通常是false)
	true, // 是否生成工具
	true // 是否生成URL链接
	);

	// 设置字体，显示中文
	Font titleFont = new Font("SIMSUN", Font.PLAIN, 15);
	Font axisFont = new Font("SIMSUN", Font.PLAIN, 13);
	Font labelFont = new Font("SIMSUN", Font.PLAIN, 12);
	
	// 设置标题字体
	chart.getTitle().setFont(titleFont);
	
	// 设置图例字体
	//LegendTitle legend = chart.getLegend();
	//legend.setItemFont(labelFont);

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
	categoryURLGenerator.addURLSeries(series);

	// 设定连接
	renderer.setItemURLGenerator(categoryURLGenerator);
	renderer.setItemLabelFont(labelFont);

	//显示每个柱的数值，并修改该数值的字体属性
	renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	renderer.setItemLabelFont(labelFont);
	renderer.setItemLabelsVisible(true);

	// 使用设计的效果
	plot.setRenderer(renderer);

	ChartImageInfoFactory servletChartFactory = new ChartImageInfoFactory();
	servletChartFactory.setDisplayChartServlet("/jfreechart/DisplayChart");
	ChartImageInfo chartImageInfo = servletChartFactory.getChartImageInfo(chart, "barChart3D", request, 600, 300);

	String src = chartImageInfo.getSrc();
	String width = chartImageInfo.getWidth();
	String height = chartImageInfo.getHeight();
	String usemapName = chartImageInfo.getUsemapName();
	String usemapContent = chartImageInfo.getUsemapContent();
%>
<html>
	<head>
		<title>程序员学历情况调查表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
		<!-- Ext JS Extend -->
		<!-- Prophet CSS -->
		<link rel="stylesheet" type="text/css"
			href="<%=rootPath%>/styles/common.css"></link>
		<!-- Page JS -->
		<script type="text/javascript">
			function getData(value, columnKey){
				alert(value + ', ' + columnKey);
			}
		</script>
	</head>
	<body align="center">
		<%=usemapContent%>
		<img src="<%=src%>" width="<%=width%>" height="<%=height%>" border="0" usemap="<%=usemapName%>">
	</body>
</html>
