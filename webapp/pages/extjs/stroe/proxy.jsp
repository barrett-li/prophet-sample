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
		<title>Proxy 演示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<script type="text/javascript" src="<%=rootPath%>/dwr/org/interface/orgDWRService.js"></script>
  		<!-- Ext JS Extend -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/DWRProxy.js"></script>
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
  		<!-- Page JS -->
		<script type="text/javascript">
		Ext.onReady(function(){
            // Http Proxy
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
            
            // Memory Proxy and Array Reader
			var store = new Ext.data.Store({
		        proxy: new Ext.data.MemoryProxy([
				    ['US', 'United States'],
				    ['DE', 'Germany'],
				    ['FR', 'France']
				]),
		        reader: new Ext.data.ArrayReader({
		            id: 0
		        }, [
		        	// 'countryCode', 'countryName'
		            {name: 'countryCode', mapping: 0},
		            {name: 'countryName', mapping: 1}
		        ])
            });
            //store.load();
            
            // Memory Proxy and Json Reader
			var store = new Ext.data.Store({
		        proxy: new Ext.data.MemoryProxy({
				    countrys: [
				        {countryCode: 'US', countryName: 'United States'},
				        {countryCode: 'DE', countryName: 'Germany'},
				        {countryCode: 'FR', countryName: 'France'}
				    ],
				    totalCount: 3
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
            //store.load();
            
            // DWR Proxy and Json Reader
			var store = new Ext.data.Store({
		        proxy: new Ext.ux.data.DWRProxy({
		            method: orgDWRService.getAllDeptsDataset
		            /*
		            mode: 'local',
		            method: function(proxy, opts, start, limit, sort, direction, root, totalProperty, successProperty, params, callback) {
		    			// 设置回调函数
		    			Ext.apply(callback, {
							callback: function(response){
								// 设置结果集
								var result = {};
								result[root] = response;
								result[totalProperty] = response.length;
								proxy.loadResponse(opts, true, result);
							}
	            		});
		            	orgDWRService.getAllDepts(sort, direction, callback);
		            }
		            */
			    }),
		        reader: new Ext.data.JsonReader({
		            root: 'depts',
		            totalProperty: 'totalCount'
		        }, [
		            {name: 'countryCode', mapping: 'id'},
		            {name: 'countryName', mapping: 'fullName'}
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