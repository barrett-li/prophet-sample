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
		<title>Customizing TreePanel演示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/scripts/extjs/extend/resources/css/column-tree.css" />
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/TreeCheckNodeUI.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/ColumnNodeUI.js"></script>
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/column-tree.css" />
  		<style>
			.task {
			    background-image:url(<%=rootPath%>/images/icons/fam/cog.png) !important;
			}
			.task-folder {
			    background-image:url(<%=rootPath%>/images/icons/fam/folder_go.png) !important;
			}
  		</style>
  		<!-- Page JS -->
		<script type="text/javascript">
		Ext.onReady(function(){
		    var tree = new Ext.ux.tree.ColumnTree({
		        el:'tree-ct',
			    width: 572,   
			    height:300,   
			    border: true,   
			    lines: false,   
			    animate: false,   
			    rootVisible: false,   
			    autoScroll:true,
			    //checkModel: 'cascade',//级联多选，如果不需要checkbox,该属性去掉   
			    //onlyLeafCheckable: true,//所有结点可选，如果不需要checkbox,该属性去掉 
		        title: 'Example Tasks',
		        
		        columns:[{
		            header:'Task',
		            width:350,
		            dataIndex:'task'
		        },{
		            header:'Duration',
		            width:100,
		            dataIndex:'duration'
		        },{
		            header:'Assigned To',
		            width:100,
		            dataIndex:'user'
		        }],
		
		        loader: new Ext.tree.TreeLoader({
		            dataUrl:'column-data.json',
		            uiProviders:{
		                'col': Ext.ux.tree.ColumnTreeNodeUI
		                //'col': Ext.ux.tree.ColumnTreeCheckNodeUI
		            }
		        }),
		
		        root: new Ext.tree.AsyncTreeNode({
		            text:'Tasks'
		        })
		    });
		    tree.render();
		});
		</script>
	</head>
	<body>
		<div id="tree-ct">
		    
		</div>
	</body>
</html>