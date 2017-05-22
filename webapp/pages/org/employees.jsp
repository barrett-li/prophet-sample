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
		<title>员工管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<script type="text/javascript" src="<%=rootPath%>/dwr/org/interface/orgDWRService.js"></script>
  		<!-- Ext JS Extend -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/DWRProxy.js"></script>
		<!-- Prophet CSS -->
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
		<!-- Page JS -->
		<script type="text/javascript">
			// 创建命名空间
			Ext.namespace('Prophet.org.EmployeesForm');
			// 创建Prophet.org.EmployeesForm类
			Prophet.org.EmployeesForm = function () {
				return {
					/**
					 * 创建员工查询条件表单
					 *
					 * @param orgDWRService 组织机构DWR Service, 用于提供员工数据
					 * @param el 用于被Ext.FormPanel装饰的HTML元素ID
					 *
					 * @return 员工查询条件表单 (Ext.FormPanel)
					 */
					init: function (orgDWRService, el) {
					    // 加载下拉列表框内容
						var ds = new Ext.data.Store({
							// 设置远程代理
					        proxy: new Ext.ux.data.DWRProxy({
					            method: orgDWRService.getAllDeptsDataset,
					            listeners: {
					            	// 异常处理
						            loadexception: function(proxy, o, response, e) {
						            	Ext.MessageBox.alert("警告", e);
						            }
					            }
						    }),
						    // 设置字段影射关系
					        reader: new Ext.data.JsonReader({
					            root: 'depts',
					            totalProperty: 'totalCount'
					        }, [
					            {name: 'DEPT_ID', mapping: 'id'},
					            {name: 'FULLNAME', mapping: 'fullName'}
					        ]),
					
					        // 使用服务器方式
					        remoteSort: true
					    });
					    // 设置默认排序方式
					    ds.setDefaultSort('FULLNAME', 'desc');
						
						// 查询条件表单
					    var fp = new Ext.FormPanel({
					    	id: 'employeesForm',
					    	iconCls:'p-form-icon',
					        labelAlign: 'left',
					        buttonAlign: 'right',
					        frame: true,
					        collapsible: true,
					        title: '查询条件',
					        bodyStyle:'padding:5px 5px 0',
					        width: 770,
					        labelWidth: 60,
					        items: [{
					            layout:'column',
					            items:[{
					                columnWidth:.5,
					                layout: 'form',
					                items: [{
					                	id: 'fullName',
					                    xtype:'textfield',
					                    fieldLabel: '员工姓名',
					                    anchor:'80%'
					                }]
					            },{
					                columnWidth:.5,
					                layout: 'form',
					                items: [{
					                	id: 'departmentId',
										xtype:'combo',
										store: ds,
										valueField :"DEPT_ID",
										displayField: "FULLNAME",
										forceSelection: false,
										editable: false,
										triggerAction: 'all',
										fieldLabel: '所属部门',
										anchor:'80%' 
									}] 
					            }]
					        }],
					
					        buttons: [{
					            text: '查询',
					            type:'button',
					            handler: function (btn, e) {
									var fullName = Ext.getCmp('fullName').getValue();
									var departmentId = Ext.getCmp('departmentId').getValue();
									var grid = Ext.getCmp('employeesGrid');
									
									// 设置查询条件
									var ds = grid.getStore();
									ds.baseParams = {fullName: fullName, departmentId: departmentId};
									// 重新加载表格数据
									ds.reload();
					            }
					        },{
					            text: '重置',
					            type:'reset',
					            handler: function (btn, e) {
									Ext.getCmp('employeesForm').getForm().reset();
					            }
					        }]
					    });
		
					    fp.render(el);
					    
					    return fp;
					}
				}
			}();


			// 创建命名空间
			Ext.namespace('Prophet.org.EmployeesGrid');
			// 创建Prophet.org.EmployeesGrid类
			Prophet.org.EmployeesGrid = function () {
			
				return {
				
					/**
					 * 创建员工表格
					 *
					 * @param orgDWRService 组织机构DWR Service, 用于提供员工数据
					 * @param el 用于被Ext.grid.Grid装饰的HTML元素ID
					 * @param fullName 员工姓名
					 * @param departmentId 所属部门
					 *
					 * @return 员工表格 (Ext.grid.Grid)
					 */
					init : function (orgDWRService, el, fullName, departmentId) {
					
						var employeesForm = Ext.getCmp('employeesForm');
						// 加载表格数据
					    var ds = new Ext.data.Store({
					        proxy: new Ext.ux.data.DWRProxy({
					            method: orgDWRService.getEmployeesDataset,
					            listeners: {
						            'loadexception': function(proxy, o, response, e) {
						            	Ext.MessageBox.alert("警告", e);
						            }
					            }
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
					    ds.setDefaultSort('EMPLOYEE_NAME', 'desc');
					    
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
					    	id: 'employeesGrid',
					        el: el,
					        iconCls:'p-grid-icon',
					        width: 770,
					        height: 230,
					        frame: true,
					        title: '员工列表',
					        store: ds,
					        cm: cm,
					        collapsible: true,
					       	//sm: new Ext.grid.RowSelectionModel({selectRow:Ext.emptyFn}),
					        loadMask: true,
					        tbar: [{
					            text: '新增员工',
					            xtype: 'button',
					            iconCls: 'p-btn-icon-add',
					            tooltip: '新增员工信息',
					            handler: function (btn, e) {
					            	employeesForm.getForm().getEl().dom.action = '<%=rootPath%>/pages/org/addEmployee.jsp';
									employeesForm.getForm().getEl().dom.submit();
					            }
					        }],
					        bbar: new Ext.PagingToolbar({
					            pageSize: 5,
					            store: ds,
					            buttons:['-', {
						            xtype: 'button',
						            iconCls: 'p-btn-icon-excel',
						            tooltip: '导出Excel',
						            handler: function (btn, e) {
										// 导出Excel
						            }
							    }],
					            displayInfo: true
					        })
					    });
						
						var employeeId = null;
						// 操作菜单
					    var rm = new Ext.menu.Menu({
					        id: 'rowMenu',
					        items: [{
					                text: '修改',
					        		iconCls: 'p-menu-icon-edit',
					        		handler: function (item, e) {
						            	employeesForm.getForm().getEl().dom.action = '<%=rootPath%>/pages/org/editEmployee.jsp?employeeId=' + employeeId;
										employeesForm.getForm().getEl().dom.submit();
					        		}
					            },
					            {
					                text: '删除',
					        		iconCls: 'p-menu-icon-delete',
					        		handler: function (item, e) {
					        			if(employeeId){
							        		// 弹出提示框, 提示是否删除
							        		Ext.MessageBox.confirm('确认', '你确认删除员工信息吗?', function(cbtn, text) {
											    if (cbtn == 'yes'){
													// 打开遮幕
													grid.el.mask('正在提交...', 'x-mask-loading');
											    	// 调用远程对象, 删除员工
													orgDWRService.deleteEmployee(employeeId, {
														callback: function() {
															Ext.MessageBox.alert('提示', '员工删除成功!');
															// 刷新表格
															ds.reload();
															// 移除遮幕
															grid.el.unmask(false);
														},
														// 异常处理
														exceptionHandler: function(exception) {
															Ext.MessageBox.alert('警告', exception);
															// 移除遮幕
															grid.el.unmask(false);
														}
													});
											    }
											});
							        	}else{
							        		Ext.MessageBox.alert('提示', '请选择员工!');
							        	}
					        		}
					            }
					        ]
					    });
			            
			            // 右击显示菜单
						grid.on('rowcontextmenu', function(g, rowIndex, e) {
							// 屏蔽右键
							e.stopEvent();
							g.getSelectionModel().selectRow(rowIndex, false);
							// 取该行的数据
							employeeId = g.getStore().getAt(rowIndex).get('EMPLOYEE_ID');
							// 显示菜单
							rm.show(e.getTarget());
						}, this);
			            
					    // 显示表格
					    grid.render();
		
					    // 设置查询条件
						ds.baseParams = {fullName: fullName, departmentId: departmentId};
					    // 加载数据
					    ds.load({params: {start: 0, limit: 5}});
					    
					    return grid;
					}
				}
			}();
			
			Ext.onReady(function() {
			    Ext.QuickTips.init();
			    Ext.form.Field.prototype.msgTarget = 'side';
			    
				var form = Prophet.org.EmployeesForm.init(orgDWRService, 'employees-form');
				var grid = Prophet.org.EmployeesGrid.init(orgDWRService, 'employees-grid', null, null);
			});
		</script>
	</head>
	<body>
		<p>
			<div id="employees-form"></div>
		</p>
		<p>
			<div id="employees-grid"></div>
		</p>
	</body>
</html>