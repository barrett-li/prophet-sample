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
		<title>Reader 演示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
  		<!-- Page JS -->
		<script type="text/javascript">
		Ext.onReady(function(){
            // Array Reader
			var store = new Ext.data.Store({
		        proxy: new Ext.data.HttpProxy({
					url: 'countryarray.json'
			    }),
		        reader: new Ext.data.ArrayReader({
		            id: 0
		        }, [
		        	// 'countryCode', 'countryName'
		            {name: 'countryCode', mapping: 0},
		            {name: 'countryName', mapping: 1}
		        ])
            });
            //store.load();
            // Json Reader
			var store = new Ext.data.Store({
		        proxy: new Ext.data.HttpProxy({
					url: 'countrys.json'
			    }),
		        reader: new Ext.data.JsonReader({
		            root: 'countrys',
		            totalProperty: 'totalCount'
		        }, [
		        	// 'countryCode', 'countryName'
		            {name: 'countryCode', mapping: 'countryCode'},
		            {name: 'countryName', mapping: 'countryName'}
		        ])
            });
            store.load();
            
			var combo = new Ext.form.ComboBox({
	            applyTo: 'local-states',
	            store: store,
	            valueField: 'countryCode',
	            displayField: 'countryName',
	            mode: 'local',
	            triggerAction: 'all'
	            
			});
			combo.show();
		});
		</script>
	</head>
	<body>
		<input type="text" id="local-states" size="20"/>
	</body>
</html>