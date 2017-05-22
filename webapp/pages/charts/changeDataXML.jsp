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
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
  		<script type="text/javascript" src="<%=rootPath%>/scripts/fusioncharts/FusionCharts.js"></script>
  		<!-- Page JS -->
		<script type="text/javascript">
		//updateChart method is called whenever the user clicks the button to
		//update the chart. Here, we get a reference to the chart and update it's
		//data using setDataXML method.
		function updateChart(DOMId){
			//Get reference to chart object using Dom ID
			var chartObj = getChartFromId("chart1Id");
			//Update it's XML
			chartObj.setDataXML("<chart><set label='B' value='12' /><set label='C' value='10' /><set label='D' value='18' /><set label='E' value='21' /></chart>");			
			//Disable the button
			this.document.frmUpdate.btnUpdate.disabled = true;
		}
		</script>
	</head>
	<body>
		<h2>FusionCharts & JavaScript - Updating chart using setDataXML() Method</h2>
		
		<div id="chart1div">
			FusionCharts
		</div>
		<script language="JavaScript">					
			var chart1 = new FusionCharts(CHARTS_ROOT_PATH + "/Column3D.swf", "chart1Id", "400", "300", "0", "1");		   			
			chart1.setDataXML("<chart><set label='A' value='10' /><set label='B' value='11' /></chart>");
			chart1.render("chart1div");
		</script>
		<br>
		<form name='frmUpdate'>
		<input type='button' value='Change Data' onClick='javaScript:updateChart();' name='btnUpdate'>		
		</form>
	</body>
</html>