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
		<title>Prophet示例：Ext.tree.TreePanel</title>
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
		<!-- Page JS -->
		<script type="text/javascript">
		Ext.onReady(function() {
			// menu tree
	        var menutree = new Ext.tree.TreePanel({
				applyTo: 'menu-tree',
				animate: true,
				autoScroll: true,
				loader: new Ext.tree.TreeLoader(),
				enableDD: false,
				lines: false,
				containerScroll: true,
				root: new Ext.tree.AsyncTreeNode({
					text: 'Prophet',
					draggable: false,
					expanded: true,
					id: '0',
		            children:[{   
						text: '系统管理',
						children:[{
							leaf : true,
							text:'用户管理'
						},{
							leaf : true,
							text:'组织机构管理'
						},{
							leaf : true,
							text:'参数配置'
						},{
							leaf : true,
							text:'系统日志'
						}]
					},{
						leaf : true,
						text: '销售管理'
					}]
				}),
				border: false,
				dropConfig: {appendOnly: true}
			});

			menutree.on('click', function(node, e){
				alert(node.text);
			});
			
			// check tree
	        var checktree = new Ext.tree.TreePanel({
				applyTo: 'check-tree',
				checkModel: 'cascade',   // 对树的级联多选
				//checkModel: 'single', // 单选
				//onlyLeafCheckable: false, // 对树所有结点都可选
				onlyLeafCheckable: true, // 只选叶子节点
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
						uiProvider: 'Ext.ux.tree.TreeCheckNodeUI',
						children:[{
							leaf : true,
							text:'用户管理',
							uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
						},{
							leaf : true,
							text:'组织机构管理',
							uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
						},{
							leaf : true,
							text:'参数配置',
							uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
						},{
							text:'系统日志',
							uiProvider: 'Ext.ux.tree.TreeCheckNodeUI',
							children:[{
								leaf : true,
								text:'日志浏览',
								uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
							},{
								leaf : true,
								text:'日志备份',
								uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
							}]
						}]
					},{
						leaf : true,
						checked: true,
						text: '销售管理',
						checkName: 'checktree2',
						value: '0',
						uiProvider: 'Ext.ux.tree.TreeCheckNodeUI'
					}]
				}),
				border: false,
				dropConfig: {appendOnly: true}
			});

			checktree.on('checkchange', function(node, checked) {
				if(node.ui.checkbox) {
					alert(node.text + ' = ' + node.ui.checkbox.name + ', ' + node.ui.checkbox.value + ', ' + checked);
				}
			});
		});
		</script>
	</head>
	<body>
		<h2>
			Ext.tree.TreePanel演示
		</h2>
		<br />
		<br />
		<p>演示Ext.tree.TreePanel读取本地数据。</p>
		<div id="menu-tree" style="width: 210px;">
		</div>
		<p/>
		<div id="check-tree" style="width: 210px;">
		</div>
	</body>
</html>