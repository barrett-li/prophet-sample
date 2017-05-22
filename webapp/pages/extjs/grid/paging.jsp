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
		<title>Prophet示例：DWRProxy演示</title>
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
			Ext.onReady(function() {
				var fullName = Ext.get('fullName');
				var departmentId = Ext.get('departmentId');
				
				// 加载表格数据
			    var store = new Ext.data.Store({
			        proxy: new Ext.ux.data.DWRProxy({
			            method: orgDWRService.getEmployeesDataset
				    }),
			        
			        reader: new Ext.data.JsonReader({
			            root: 'employees',
			            totalProperty: 'totalCount',
			            id: 'EMPLOYEE_ID'
			        }, [
			        	{name: 'EMPLOYEE_ID', mapping: 'id'},
			            {name: 'EMPLOYEE_NAME', mapping: 'name'},
			            {name: 'EMAIL', mapping: 'email'},
			            {name: 'MOBILE', mapping: 'mobile'},
			            {name: 'FULLNAME', mapping: 'department.fullName'}
			        ]),
			        remoteSort: true
			    });
			    store.setDefaultSort('EMPLOYEE_NAME', 'desc');
			    
			    // 设置表格样式
			    var cm = new Ext.grid.ColumnModel([{
						id: 'name', 
						header: '姓名',
						dataIndex: 'EMPLOYEE_NAME',
						width: 120,
						css: 'white-space:normal;'
			        },{
						header: 'e-mail',
						dataIndex: 'EMAIL',
						width: 220
			        },{
						header: '手机',
						dataIndex: 'MOBILE',
						width: 100
			        },{
						header: '部门',
						dataIndex: 'FULLNAME',
						width: 220
			        }]);
			
			    // 默认分类
			    cm.defaultSortable = true;
				
			    // 创建表格
			    var grid = new Ext.grid.GridPanel({
			        width: 650,
			        height: 210,
			        title: '员工列表',
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

			    // 显示表格
			    grid.render('employees-grid');
			    
			    // 设置查询条件
			    Ext.apply(store.baseParams, {fullName: fullName.getValue(), departmentId: departmentId.getValue()});

			    // 加载数据
			    store.load({params: {start: 0, limit: 5}});
			    
			    var button = Ext.get('query');
			    
			    button.on('click', function(){
					Ext.apply(store.baseParams, {fullName: fullName.getValue(), departmentId: departmentId.getValue()});
					// 重新加载表格数据
					store.reload();
			    }, this)
			});
		</script>
	</head>
	<body style="padding: 15px;">
		<h2>
			Ext.ux.data.DWRProxy演示
		</h2>
		<br />
		<br />
		<p>演示Ext.ux.data.DWRProxy通过DWR获取远程数据。</p>
		<p>
			员工姓名：<br/>
			<input type="text" id="fullName" value="" size="35"/><br/>
			部门ID：<br/>
			<input type="text" id="departmentId" value="" size="35"/><br/>
			<input type="button" id="query" value="查询"/><br/>
		</p>
		<p>
			<div id="employees-grid"></div>
		</p>
	</body>
</html>