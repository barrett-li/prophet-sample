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
		<title>Prophet示例：TreeCheckNodeUI演示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<script type="text/javascript" src="<%=rootPath%>/dwr/security/interface/securityDWRService.js"></script>
  		<!-- Ext JS Extend -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/TreeDWRLoader.js"></script>
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/TreeCheckNodeUI.js"></script>
		<!-- Prophet CSS -->
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
		<!-- Page JS -->
		<script type="text/javascript">
		Ext.onReady(function() {
	        var tree = new Ext.tree.TreePanel({
				applyTo: 'menu-tree',
				checkModel: 'cascade',   // 对树的级联多选
				onlyLeafCheckable: false, // 对树所有结点都可选
				animate: true, 
				autoScroll: true,
				loader: new Ext.ux.tree.TreeDWRLoader({
					method: securityDWRService.getMenuTreeNodes,
					baseParams: { uiProvider: 'Ext.ux.tree.TreeCheckNodeUI' } // 设置uiProvider属性
				}),
				enableDD: false,
				lines: false,
				containerScroll: true,
				root: new Ext.tree.AsyncTreeNode({
					text: 'Prophet', 
					draggable: false,
					expanded: true,
					id: '0'
				}),
				border: false,
				dropConfig: {appendOnly: true}
			});
			
			// 注册"check"事件
			tree.on('checkchange', function(node, checked) {
				alert(node.text + " = " + checked)
			}); 
		});
		</script>
	</head>
	<body>
		<h2>
			TreeCheckNodeUI演示
		</h2>
		<br />
		<br />
		<p>演示Ext.tree.TreeNodeUI进行checkbox功能的扩展。</p>
		<p>
		<div id="menu-tree" style="width: 210px; height: 350px;">
		</div>
		</p>
	</body>
</html>