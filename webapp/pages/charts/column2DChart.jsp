<%--
Prophet 1.0
CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD

http://resoft.css.com.cn
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/templets/common/global.jsp"%>
<%@ include file="/templets/common/taglibs.jsp"%>
<html>
	<head>
		<title>Column2D Chart示例</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<!-- FusionCharts 3.0 JS -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/fusioncharts/FusionCharts.js"></script>
  		<script type="text/javascript" src="<%=rootPath%>/scripts/fusioncharts/FusionChart.js"></script>
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
  		<!-- Page JS -->
		<script type="text/javascript">
		Ext.onReady(function() {
			// 从Action获取数据
			var chart1 = new FusionCharts(CHARTS_ROOT_PATH + '/Column2D.swf', 'ChartId1', '500', '300', '0', '0');
			chart1.setDataURL("<%=rootPath%>/charts/column2D.action");
			chart1.render("chartdiv1");
			
			// 从JSP获取数据
			var chart2 = new FusionCharts(CHARTS_ROOT_PATH + '/Column2D.swf', 'ChartId2', '500', '300', '0', '0');
			chart2.setDataURL("column2D.jsp");
			chart2.render("chartdiv2");
			
			// 从XML获取数据
			var chart3 = new FusionCharts(CHARTS_ROOT_PATH + '/Column2D.swf', 'ChartId3', '500', '300', '0', '0');
			chart3.setDataURL("column2D.xml");
			chart3.render("chartdiv3");

			// 从javascript获取数据
			var chart4 = new FusionCharts(CHARTS_ROOT_PATH + '/Column2D.swf', 'ChartId4', '500', '300', '0', '1'); 
         	chart4.setDataXML(getColumn2D());
         	chart4.render("chartdiv4");
         
		});
		
		function getColumn2D(){
			// 这里也可以直接返回XML文本
			/*
			var buf = [];
			buf.push('<chart palette=\'2\' caption=\'单位销售量\' xAxisName=\'月份\' yAxisName=\'单位\' baseFont=\'黑体\' rotateYAxisName=\'0\' '); 
			buf.push('showValues=\'0\' decimals=\'0\' formatNumberScale=\'0\' useRoundEdges=\'1\'>');
			buf.push('<set label=\'一月\' value=\'462\' />');
			buf.push('<set label=\'二月\' value=\'857\' />');
			buf.push('<set label=\'三月\' value=\'671\' />');
			buf.push('<set label=\'四月\' value=\'494\' />');
			buf.push('<set label=\'五月\' value=\'761\' />');
			buf.push('<set label=\'六"月\' value=\'960\' />');
			buf.push('</chart>');
			return buf.join('');
			*/
			// 使用FusionChart构造XML文本
			var fusionChart = new FusionChart({caption: '月收入'});
			
			//设置<chart/>属性
			//fusionChart.addAttribute('caption', '月收入');
			fusionChart.addAttribute('xAxisName', '月份');
			fusionChart.addAttribute('yAxisName', '收入');
			fusionChart.addAttribute('numberPrefix', '%A5');
			fusionChart.addAttribute('showValues', '1');
			//fusionChart.addAttribute('decimals', false); // 相当于decimals='0'
			fusionChart.addAttribute('formatNumberScale', false); // 相当于formatNumberScale='0'
			fusionChart.addAttribute('useRoundEdges', true); // 相当于useRoundEdges='1'
			//fusionChart.addAttribute('labelDisplay', 'NONE');
			//fusionChart.addAttribute('baseFont', '黑体');
			
			//当使用特定的字符集时，y-axis名称使用旋转模式
			fusionChart.addAttribute('rotateYAxisName', '0');
			
			//增加<set/>
			// 在图表中使用%26apos;特征码表示单引号
			fusionChart.addElement(new Set({label : '一月%26apos;', value: 420000, link: 'javascript: alert(420000);'}));
			fusionChart.addElement(new Set({label : '二月', value: 910000}));
			fusionChart.addElement(new Set({label : '三月', value: 720000}));
			fusionChart.addElement(new Set({label : '四月', value: 550000}));
			fusionChart.addElement(new Set({label : '五月', value: 810000}));
			fusionChart.addElement(new Set({label : '六月', value: 510000}));
			fusionChart.addElement(new Set({label : '七月', value: 680000}));
			fusionChart.addElement(new Set({label : '八月', value: 620000}));
			fusionChart.addElement(new Set({label : '九月', value: 610000}));
			fusionChart.addElement(new Set({label : '十月', value: 490000}));
			fusionChart.addElement(new Set({label : '十一月', value: 530000}));
			fusionChart.addElement(new Set({label : '十二月', value: 330000}));
			
			//设置<trendLines/>
			var trendLines = new TrendLines();
			//增加<line/>
			trendLines.addElement(new Line({startValue: 700000, displayValue: 'Target', color: '009933'}));
			fusionChart.addElement(trendLines);

			//设置<styles/>
			var styles = new Styles();
			var style = new Style({name: 'CanvasAnim', type: Style.STYLETYPE_ANIMATION});
			style.addAttribute('param', '_xScale');
			style.addAttribute('start', '0');
			style.addAttribute('duration', '1');
			styles.definitionStyle(style);
			styles.applyStyles('Canvas', 'CanvasAnim');
			fusionChart.addElement(styles);

			return fusionChart.toXML();
		}
		</script>
	</head>
	<body>
		该示例演示在浏览器中从不同途径获取FusionCharts数据的能力。
		<br>
		<br>
		<table width="98%" border="0" cellspacing="0" cellpadding="3" align="center">
		  	<tr>
			    <td valign="top" align="center">
			    	<br/>
			    	<h3>Column2D Chart示例(从Action获取数据)</h3>
					<div id="chartdiv1" align="center"></div>
				</td>
			</tr>
		  	<tr>
			    <td valign="top" align="center">
			    	<br/>
			    	<h3>Column2D Chart示例(从JSP获取数据)</h3>
					<div id="chartdiv2" align="center"></div>
				</td>
			</tr>
		  	<tr>
			    <td valign="top" align="center">
			    	<br/>
			    	<h3>Column2D Chart示例(从XML获取数据)</h3>
					<div id="chartdiv3" align="center"></div>
				</td>
			</tr>
		  	<tr>
			    <td valign="top" align="center">
			    	<br/>
			    	<h3>Column2D Chart示例(从javascript获取数据)</h3>
					<div id="chartdiv4" align="center"></div>
				</td>
			</tr>
		</table>
	</body>
</html>