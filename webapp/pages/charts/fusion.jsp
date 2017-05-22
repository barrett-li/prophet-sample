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
		<title>Ext.ux.Fusion 示例</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
	    <script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/fusion/uxmedia.js"></script>
	    <script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/fusion/uxflash.js"></script>
	    <script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/fusion/uxfusion.js"></script>
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
  		<!-- Page JS -->
		<script type="text/javascript">
	    Ext.onReady(function(){
			Ext.QuickTips.init();
			Ext.QuickTips.getQuickTip().interceptTitles = true;
			
			Ext.QuickTips.enable();

			var getTools = function(){
				return [{id:'gear', handler:function(e, t, p){p.refreshMedia();}, qtip: {text: 'Refresh the Chart'}}//,
					//{id:'print', handler:function(e, t, p){p.print();}, qtip: {text: 'Print the Chart'}}
				];
			};
		
			var chartEvents = {
	        	//'mousemove':function(){console.log(['mousemove',arguments])}
	        };
		
			panel = new Ext.ux.FusionPanel({
				title: 'ux.FusionPanel',
				collapsible: true,
				renderTo: Ext.getBody(),
				floating: false,
				fusionCfg:{id: 'chart1',
					listeners: chartEvents
				},
				autoScroll: true,
				id: 'chartpanel',
				chartURL: CHARTS_ROOT_PATH + '/Pie3D.swf',
				dataURL: 'Column3D.xml',
				width: 300,
				height: 400,
				listeners:{
				    show: function(p){if(p.floating)p.setPosition(p.x||10,p.y||10);}
				    //,chartload : function(p,obj){console.log('chart '+obj.id+' loaded.')},
				    //,chartrender : function(p,obj){console.log('chart '+obj.id+' rendered.')}
				},
			    tools: getTools()
			});
		
			(view = new Ext.ux.FusionWindow({
				height: 250,
				width: 460,
				x: 330,
				y: 15,
				closeAction: 'hide',
				title: 'ux.FusionWindow',
				renderTo: Ext.getBody(),
				collapsible: true,
				maximizable: true,
				renderTo: Ext.getBody(),
				tools: getTools(),
				fusionCfg: {id: 'chart2',
					listeners: chartEvents
				},
				id: 'demoFusion',
				chartURL: CHARTS_ROOT_PATH + '/Column3D.swf',
				dataURL: 'Column3D.xml' //let the chartObj load it
			})).show();

		  	panel.show();
		
		  	Ext.EventManager.on(window, "beforeunload", function(){
		       Ext.destroy(view, panel);
			} ,window ,{single:true});
		});
		</script>
	</head>
	<body>

	</body>
</html>