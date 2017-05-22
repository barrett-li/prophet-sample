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
		<title>Tab Panel演示</title>
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
			var employees = {
	        	roots: [
		            {name: '韦海晗', email: 'weihaihan@resoft.css.com.cn'},
		            {name: '谢立锋', email: 'xielifeng@resoft.css.com.cn'},
		            {name: '张奇', email: 'zhangqi@resoft.css.com.cn'},
		            {name: '韦海晗', email: 'weihaihan@resoft.css.com.cn'},
		            {name: '谢立锋', email: 'xielifeng@resoft.css.com.cn'},
		            {name: '张奇', email: 'zhangqi@resoft.css.com.cn'},
		            {name: '韦海晗', email: 'weihaihan@resoft.css.com.cn'},
		            {name: '谢立锋', email: 'xielifeng@resoft.css.com.cn'},
		            {name: '张奇', email: 'zhangqi@resoft.css.com.cn'}
	        	],
	        	totalCount: 3
		    };
			// 加载表格数据
		    var store = new Ext.data.Store({
				proxy: new Ext.data.MemoryProxy(employees),
		        
		        reader: new Ext.data.JsonReader({
		            root: 'roots',
		            totalProperty: 'totalCount'
		        }, [
		            {name: 'name', mapping: 'name'},
		            {name: 'email', mapping: 'email'}
		        ]),
		        remoteSort: true
		    });
		    store.setDefaultSort('name', 'desc');
		    
		    // 设置表格样式
		    var cm = new Ext.grid.ColumnModel([{
					id: 'name', 
					header: '姓名',
					dataIndex: 'name',
					width: 120,
					css: 'white-space:normal;'
		        },{
					header: 'e-mail',
					dataIndex: 'email',
					width: 220
		        }]);
		
		    // 默认分类
		    cm.defaultSortable = true;
			
		    // 创建表格
		    var gp = new Ext.grid.GridPanel({
		        border: false,
		        store: store,
		        cm: cm,
		        trackMouseOver:false,
		        sm: new Ext.grid.RowSelectionModel({selectRow:Ext.emptyFn}),
		        loadMask: true,
		        viewConfig: {
		            forceFit:true,
		            enableRowBody:true,
		            showPreview:true
		        },
		        bbar: new Ext.PagingToolbar({
		            pageSize: 5,
		            store: store,
		            displayInfo: true
		        })
		    });
		    
		    // 加载数据
		    store.load();
		    
			// 表单
		    var fp = new Ext.FormPanel({
		        labelAlign: 'left',
		        buttonAlign: 'right',
		        labelWidth: 60,
		        layout: 'column',
		        baseCls: 'x-panel-mc',
		        bodyStyle: 'padding:5px;',
	            defaults: {
	            	layout: 'form'
	            },
	            items:[{
	            	columnWidth:.5,
	                items: [{
	                    xtype:'textfield',
	                    fieldLabel: '员工姓名',
	                    anchor:'80%'
	                }]
	            },{
	            	columnWidth:.5,
	                items: [{
	                    xtype:'textfield',
	                    fieldLabel: '电子邮箱',
	                    anchor:'80%'
					}] 
	            }],
		        buttons: [{
		            text: '确定',
		            type:'button',
		            handler: function (btn, e) {

		            }
		        }]
		    });

			var ifp = new Ext.Panel({
				border:false,
				html: '<iframe src="./iframe.html" scrolling="auto" frameborder="no" width="100%" height="100%"></iframe>'
			});

	        // tabs for the center
	        var tabs = new Ext.TabPanel({
	            region: 'center',
	            activeTab: 0,
	            resizeTabs: true,
		        minTabWidth: 135,
		        tabWidth: 135,
		        layoutOnTabChange: true,
	            defaults:{
	            	layout: 'fit',
	            	autoScroll: true
	            },
	            items:[{
	                title: 'From Tab',
	                items: fp
	            },{
	                title: 'Grid Tab',
	                items: gp
	            },{
	                title: 'IFrame Tab',
	                items: ifp
	            }]
	        });
	        
	        var p = new Ext.Panel({
	        	renderTo: Ext.getBody(),
	            title: 'Tab',
	            frame: true,
	            collapsible: true,
	            width: 600,
	            height: 350,
	            plain: true,
	            layout: 'fit',
	            items: tabs
	        });
	        p.show();
		});
		</script>
	</head>
	<body>

	</body>
</html>