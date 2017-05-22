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
	    <script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/fusion/uxmedia.js"></script>
	    <script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/fusion/uxflash.js"></script>
	    <script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/fusion/uxfusion.js"></script>
  		<!-- FusionCharts 3.0 JS -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/fusioncharts/FusionChart.js"></script>
		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
	    <style type="text/css">
	        body .x-panel {
	            margin-bottom: 15px;
	        }
	    </style>
  		<!-- Page JS -->
		<script type="text/javascript">
			var showPieChart;

			Ext.onReady(function() {
			    Ext.QuickTips.init();
			    Ext.form.Field.prototype.msgTarget = 'side';

				// 加载表格数据
			    var ds = new Ext.data.Store({
			        proxy: new Ext.ux.data.DWRProxy({
			            method: chartsDataDWRService.getBankLoansDataset
				    }),

			        reader: new Ext.data.JsonReader({
			            root: 'bankLoans',
			            totalProperty: 'totalCount'
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
					}],
			        listeners: {
			        	// 单击显示图表
			        	'rowclick': function(g, rowIndex, e) {
							showPieChart(rowIndex);
							e.stopEvent();
						}
			        }
			    });
				
			    // 显示表格
			    grid.show();

			    // 设置查询条件
			    Ext.apply(ds.baseParams, {area: '北京'});
			    // 加载数据
			    ds.load();
				
				// 
				var getTools = function() {
					return [{id:'gear', handler:function(e, t, p){p.refreshMedia();}, qtip: {text: '刷新图表'}}];
				};
				
				var column2DChartPanel = new Ext.ux.FusionPanel({
			        title: '银行不良贷款信息图表展示',
			        iconCls:'p-chart-icon',
					collapsible: true,
					frame: true,
					renderTo: Ext.getBody(),
					floating: false,
					fusionCfg:{
						id: 'column2DChart'
					},
					autoScroll: true,
					id: 'column2DChartPanel',
					chartURL: CHARTS_ROOT_PATH + '/StackedColumn2D.swf',
					width: 700,
					height: 245,
					listeners:{
					    show: function(p){if(p.floating)p.setPosition(p.x||10,p.y||10);}
					},
				    tools: getTools()
				});

			    // 数据加载完成后，生成Chart数据
				ds.on('load', function(store, records, options) {
					// 使用FusionChart构造XML文本
					var fusionChart = new FusionChart({
						palette: '2',
						caption: records[0].get('AREA') + '各大商业银行不良贷款信息',
						subcaption: '单位：万元',
						baseFontSize: '12',
						baseFontColor: '000000',
						showShadow: '1',
						showValues: '0',
						showSum: '1',
						numdivlines: '3',
						useRoundEdges: '1',
						formatNumberScale: '0',
						numberPrefix: '%A5'
					});

					var categories = new Categories();
					fusionChart.addElement(categories);
			        
					var deadLoans = new DataSet({
						seriesName: '不良贷款总额',
						color: 'AFD8F8'
					});
					fusionChart.addElement(deadLoans);
					
					var overdueLoans = new DataSet({
						seriesName: '逾期贷款总额',
						color: 'F6BD0F'
					});
					fusionChart.addElement(overdueLoans);
					
					var violationLoans = new DataSet({
						seriesName: '违约贷款总额',
						color: '8BBA00'
					});
					fusionChart.addElement(violationLoans);

					for(var i = 0; i < records.length; i++){
						var record = records[i];
						categories.addElement(new Category({label: record.get('ORG')}));
						// 单击执行showPieChart方法。
						// 注意，showPieChart必需是全局的，而且showPieChart的参数不能包含中文字符。
						var link = ['javascript: showPieChart(', i,');'].join('');
						deadLoans.addElement(new Set({value: record.get('DEAD_LOANS'), link: link}));
						overdueLoans.addElement(new Set({value: record.get('OVERDUE_LOANS'), link: link}));
						violationLoans.addElement(new Set({value: record.get('VIOLATION_LOANS'), link: link}));
					}
					// 加载Chart数据
					column2DChartPanel.setDataXML(fusionChart.toXML(), true);
					
				}, this);

				// 显示饼图
				showPieChart = function(index) {
				    // 获取表格数据
					var grid = Ext.getCmp('bandloansGrid');
					var record = grid.getStore().getAt(index);
					
					// 使用FusionChart构造XML文本
					var fusionChart = new FusionChart({
						caption: record.get('AREA') + '地区' + record.get('ORG') + '不良贷款占比信息',
						palette: '2',
						decimals: false,
						baseFontSize: '12',
						showValues: true,
						showBorder: false,
						formatNumberScale: '0'
					});
					
					fusionChart.addElement(new Set({label: '不良贷款总额', value: record.get('DEAD_LOANS'), isSliced: 1}));
					fusionChart.addElement(new Set({label: '逾期贷款总额', value: record.get('OVERDUE_LOANS')}));
					fusionChart.addElement(new Set({label: '违约贷款总额', value: record.get('VIOLATION_LOANS')}));
					
					var styles = new Styles();
					styles.definitionStyle(new Style({type: 'font', name: 'CaptionFont', color: '666666', size: '15'}));
					styles.definitionStyle(new Style({type: 'font', name: 'SubCaptionFont', bold: '0'}));
					styles.applyStyles('caption', 'CaptionFont');
					styles.applyStyles('SubCaption', 'SubCaptionFont');
					fusionChart.addElement(styles);
					
					var dataXML = fusionChart.toXML();
					
					var pieChartWin = window['pieChartWin'];
					if(!pieChartWin){
						pieChartWin = new Ext.ux.FusionWindow({
							height: 300,
							width: 460,
							closeAction: 'hide',
					        title: '银行不良贷款占比信息',
					        iconCls:'p-chart-icon',
							renderTo: Ext.getBody(),
							collapsible: true,
							maximizable: true,
							tools: getTools(),
							fusionCfg: {id: 'pieChart'},
							id: 'pieChartWin',
							dataXML: dataXML,
							chartURL: CHARTS_ROOT_PATH + '/Pie3D.swf'
						});
						window['pieChartWin'] = pieChartWin;
					}
					
					// 加载Chart数据
					pieChartWin.setDataXML(dataXML, true);
					pieChartWin.show();
				}
				
				// 显示柱状图
				column2DChartPanel.show();
			});
		</script>
	</head>
	<body>

	</body>
</html>