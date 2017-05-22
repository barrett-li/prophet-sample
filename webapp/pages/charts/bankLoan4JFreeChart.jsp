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
		<title>银行贷款信息示例</title>
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
		// 
		var getTools = function() {
			return [{id:'gear', handler:function(e, t, p){p.refreshChart();}, qtip: {text: '刷新图表'}}];
		};
		
		Ext.onReady(function() {
		    Ext.QuickTips.init();
		    Ext.form.Field.prototype.msgTarget = 'side';

			// 加载表格数据
		    var ds = new Ext.data.Store({
		        proxy: new Ext.ux.data.DWRChartProxy({
		            method: chartsDataDWRService.getBankLoansChartDataset
			    }),

		        reader: new Ext.data.JsonReader({
		            root: 'bankLoans',
		            totalProperty: 'totalCount',
		            chartsProperty: 'chartsProperty'
		        }, [
		        	{name: 'AREA', mapping: 'area'},
		            {name: 'ORG', mapping: 'org'},
		            {name: 'DEAD_LOANS', mapping: 'deadLoans'},
		            {name: 'OVERDUE_LOANS', mapping: 'overdueLoans'},
		            {name: 'VIOLATION_LOANS', mapping: 'violationLoans'}
		        ]),
		        remoteSort: true
		    });
		    ds.setDefaultSort('ORG', 'desc');
		    
		    // 设置表格样式
		    var cm = new Ext.grid.ColumnModel([{
					id: 'org', 
					header: '机构',
					dataIndex: 'ORG',
					width: 220,
					css: 'white-space:normal;'
		        },{
					header: '不良贷款总额',
					dataIndex: 'DEAD_LOANS',
					width: 120
		        },{
					header: '逾期贷款总额',
					dataIndex: 'OVERDUE_LOANS',
					width: 120
		        },{
					header: '违约贷款总额',
					dataIndex: 'VIOLATION_LOANS',
					width: 120
		        }]);
		
		    // 默认分类
		    cm.defaultSortable = true;

		    // 创建表格
		    var grid = new Ext.grid.GridPanel({
		    	id: 'bandloansGrid',
		        renderTo: Ext.getBody(),
		        iconCls:'p-grid-icon',
		        width: 700,
		        height: 200,
		        frame: true,
		        title: '银行贷款信息列表',
		        store: ds,
		        cm: cm,
		        collapsible: true,
		       	//sm: new Ext.grid.RowSelectionModel({selectRow:Ext.emptyFn}),
		        loadMask: true,
		        tbar: [
		            '地区: ', ' ',{
	                	id: 'area',
						xtype:'combo',
						width: 200,
			            store: new Ext.data.SimpleStore({
			                    fields: ['areaCode', 'areaName'],
			                    data: [
			                        ['北京', '北京'],
			                        ['上海', '上海'],
			                        ['深圳', '深圳']
			                    ]
			            }),
			            listeners: {
			            	// 查询数据
			            	'select': function(combo, record, index) {
								var area = combo.getValue();
								var grid = Ext.getCmp('bandloansGrid');
								
								// 设置查询条件
								Ext.apply(ds.baseParams, {area: area});
								// 重新加载表格数据
								ds.reload();
			            	}
			            },
						valueField: "areaCode",
						displayField: "areaName",
						forceSelection: false,
						editable: false,
			            triggerAction: 'all',
			            mode: 'local',
						fieldLabel: '所属地区',
						anchor:'80%' 
				}]
		    });
			
		    // 显示表格
		    grid.show();
		    
		    var width = 700;
		    var height = 340;
		    // 设置查询条件
			ds.baseParams = {area: '北京', width: width - 14, height: height - 37};
		    // 加载数据
		    ds.load();
		    
		    // 显示图表
		    var panel = new Ext.ux.JFreeChartPanel({
		        id:'bankLoansChart',
		        title: '银行不良贷款信息',
		        iconCls:'p-chart-icon',
		        store: ds,
		        renderTo: Ext.getBody(),
		        frame: true,
		        width: 700,
		        height: 340,
		        collapsible: true,
		        loadMask: true,
		        layout: 'fit',
		        tools: getTools()
		    });
		    panel.show();
		});
		
		var pieWin;
		var pieStore;
		var getPieChartData = Ext.emptyFn;
		function getBarChartData(value, rowKey, columnKey){
			var area = Ext.getCmp('area').getValue();
			
		    // 显示图表窗体
		    if(!pieWin) {
				// 加载表格数据
			    pieStore = new Ext.data.Store({
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
			    var width = 485;
			    var height = 310;

			    // 设置查询条件
				pieStore.baseParams = {area: area, org: columnKey, width: width - 14, height: height - 43};
			    // 加载数据
			    pieStore.load();
			    
				pieWin = new Ext.ux.JFreeChartWindow({
					width: width,
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
				
		    } else {
				// 设置查询条件
				Ext.apply(pieStore.baseParams, {area: area, org: columnKey});
				// 重新加载表格数据
				pieStore.reload();
		    }

			pieWin.show();
		}
		
		</script>
	</head>
	<body>
		<div id="chart-div"></div>
	</body>
</html>