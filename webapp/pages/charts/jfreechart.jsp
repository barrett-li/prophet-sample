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
		<title>DWR远程对象获取和包含JFreeChart显示信息</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<script type="text/javascript" src="<%=rootPath%>/dwr/charts/interface/chartsDataDWRService.js"></script>
  		<!-- Ext JS Extend -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/DWRProxy.js"></script>
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/JFreeChart.js"></script>
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
	    <style type="text/css">
	        body .x-panel {
	            margin-bottom: 15px;
	        }
	    </style>
  		<!-- Page JS -->
		<script type="text/javascript">
		Ext.onReady(function() {
		    Ext.QuickTips.init();
		    Ext.form.Field.prototype.msgTarget = 'side';

			// 
			var getTools = function() {
				return [{id:'gear', handler:function(e, t, p){p.refreshChart();}, qtip: {text: '刷新图表'}}];
			};

			// 加载表格数据
		    var barStore = new Ext.data.Store({
		        proxy: new Ext.ux.data.DWRProxy({
		            method: chartsDataDWRService.getBankLoansChartImageInfo
			    }),

		        reader: new Ext.data.JsonReader({
		            root: 'images'
		        }, [
		        	{name: 'name', mapping: 'name'},
		        	{name: 'src', mapping: 'src'},
		            {name: 'width', mapping: 'width'},
		            {name: 'height', mapping: 'height'},
		            {name: 'usemapName', mapping: 'usemapName'},
		            {name: 'usemapContent', mapping: 'usemapContent'}
		        ])
		    });
		    var width = 385;
		    var height = 210;
		    // 设置查询条件
			barStore.baseParams = {area: '北京', width: width - 14, height: height - 43};
		    // 加载数据
		    barStore.load();

		    // 显示图表窗体
			var barWin = new Ext.ux.JFreeChartWindow({
				x: 15,
				y: 15,
				width: width,
				height: height,
				store: barStore,
		        title: '银行不良贷款信息',
		        iconCls:'p-chart-icon',
				renderTo: Ext.getBody(),
				collapsible: true,
				maximizable: true,
				loadMask: true,
				resizeImage: true,
				tools: getTools()
			});
			barWin.show();
			
			// 加载表格数据
		    var pieStore = new Ext.data.Store({
		        proxy: new Ext.ux.data.DWRProxy({
		            method: chartsDataDWRService.getBankLoansPieChartImageInfo
			    }),

		        reader: new Ext.data.JsonReader({
		            root: 'images'
		        }, [
		        	{name: 'name', mapping: 'name'},
		        	{name: 'src', mapping: 'src'},
		            {name: 'width', mapping: 'width'},
		            {name: 'height', mapping: 'height'},
		            {name: 'usemapName', mapping: 'usemapName'},
		            {name: 'usemapContent', mapping: 'usemapContent'}
		        ])
		    });
		    var width = 385;
		    var height = 210;
		    // 设置查询条件
			pieStore.baseParams = {area: '北京', org: '中国银行', width: width - 14, height: height - 43};
		    // 加载数据
		    pieStore.load();
			
		    // 显示图表窗体
			var pieWin = new Ext.ux.JFreeChartWindow({
				width: width,
				x: 415,
				y: 15,
				height: height,
				store: pieStore,
		        title: '银行不良贷款信息',
		        iconCls:'p-chart-icon',
				renderTo: Ext.getBody(),
				collapsible: true,
				maximizable: true,
				loadMask: true,
				resizeImage: true,
				tools: getTools()
			});
			pieWin.show();
		});
		
		function getBarChartData(value, rowKey, columnKey){
			alert(value + ', ' + rowKey + ', ' + columnKey);
		}
		
		function getPieChartData(key, value){
			alert(key + ', ' + value);
		}
		</script>
	</head>
	<body>
		<div id="chart-div"></div>
	</body>
</html>