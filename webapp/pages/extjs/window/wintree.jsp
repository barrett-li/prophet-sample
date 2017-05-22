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
		<title>Tree Window演示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/TreeCheckNodeUI.js"></script>
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
  		<style>
			.x-tree-selected {
			    border:1px dotted #a3bae9;
			    background:#DFE8F6;
			}
			.x-tree-node .x-tree-selected a span{
				background:transparent;
				color:#1E4176;
			    font-weight:bold;
			}
  		</style>
  		<!-- Page JS -->
		<script type="text/javascript">
		Ext.onReady(function(){
		
		    var button = Ext.get('show-btn');
		    var win;
		    button.on('click', function(){
				if(!win){
				    var tp = new Ext.tree.TreePanel({
						checkModel: 'cascade',   // 对树的级联多选
						//checkModel: 'single', // 单选
						onlyLeafCheckable: false, // 对树所有结点都可选
						//onlyLeafCheckable: true, // 只选叶子节点
						animate: true,
						autoScroll: true,
						loader: new Ext.tree.TreeLoader(),
						enableDD: false,
						lines: false,
						containerScroll: true,
						checkName: 'checktree',
						root: new Ext.tree.AsyncTreeNode({
							text: 'Prophet',
							draggable: false,
							expanded: true,
							id: '0',
				            children:[{
								text: '系统管理',
								value:'系统管理',
								uiProvider: 'Ext.ux.tree.TreeCheckNodeUI',
								children:[{
									leaf : true,
									text:'用户管理',
									value:'用户管理',
									uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
								},{
									leaf : true,
									text:'组织机构管理',
									value:'组织机构管理',
									uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
								},{
									leaf : true,
									text:'参数配置',
									value: '参数配置',
									uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
								},{
									text:'系统日志',
									value:'系统日志',
									uiProvider: 'Ext.ux.tree.TreeCheckNodeUI',
									children:[{
										leaf : true,
										text:'日志浏览',
										value:'日志浏览',
										uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
									},{
										leaf : true,
										text:'日志备份',
										value:'日志备份',
										uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
									}]
								}]
							},{
								leaf : true,
								text: '销售管理',
								id: '销售管理',
								uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
							}]
						}),
						border: false,
						dropConfig: {appendOnly: true}
					});
				    
			        win = new Ext.Window({
			            title: 'Tree Window',
			            maximizable: true,
			            collapsible: true,
			            closable: true,
			            resizable: true,
			            width: 600,
			            height: 350,
				        minWidth: 300,
				        minHeight: 200,
			            layout: 'fit',
			            plain: true,
			            closeAction: 'hide',
			            //bodyStyle: 'padding:5px;',
			            items: tp,
				        buttons: [{
				            text: '确定',
				            type:'button',
				            handler: function (btn, e) {
	
				            }
				        },{
				            text: '取消',
				            type:'reset',
				            handler: function (btn, e) {
								win.hide();
				            }
				        }]
			        });
				}
		        win.show(this);
		        
		    });
		});
		</script>
	</head>
	<body>
		<input type="button" id="show-btn" value="Show Window"/>
	</body>
</html>