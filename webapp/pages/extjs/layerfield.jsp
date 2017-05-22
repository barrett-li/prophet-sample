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
		<title>Prophet示例：LayerField演示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/LayerField.js"></script>
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
  		<!-- Page JS -->
		<script type="text/javascript">
		Ext.onReady(function(){
		    var field = new Ext.ux.form.LayerField({
		    	applyTo: 'local-states',
		    	title: 'Sample LayerField',
		    	html: 'It\'s a Sample LayerField body.',
		    	//hiddenName: 'field1',
				//resizable: true,
				//editable: false,
				listeners: {
					'render': function(field){
						//alert("render, " + field.body.id);
					},
					'keyup': function(){
						//alert("keyup," + field.getValue());
					},
					'expand': function(field){
						//alert("expand" + field.body.id);
					},
					'resize': function(field, w, h){
						//alert('resize,' + w + ',' + h);
					},
					'collapse': function(field){
						//alert("collapse, " + field.getValue());
					}
				}
		    });
		});
		</script>
	</head>
	<body>
		<div>
		    <input type="text" id="local-states" size="20"/>
		</div>
	</body>
</html>