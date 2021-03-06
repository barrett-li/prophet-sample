<%--
Prophet 1.0
CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD

http://resoft.css.com.cn
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/templets/common/global.jsp"%>
<%@ include file="/templets/common/taglibs.jsp"%>
<%
String employeeId = request.getParameter("employeeId");
%>
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
			var employee;
			
			// 创建命名空间
			Ext.namespace('Prophet.org.EmployeesForm');
			// 创建Prophet.org.EmployeesForm类
			Prophet.org.EmployeesForm = function () {
				return {
					/**
					 * 创建员工表单
					 *
					 * @param orgDWRService 组织机构DWR Service, 用于提供部门和员工数据
					 * @param el 用于被Ext.FormPanel装饰的HTML元素ID
					 * @param afterInit 当Prophet.org.EmployeesForm初始化完成后执行该方法
					 
					 * @return 员工表单 (Ext.FormPanel)
					 */
					init: function (orgDWRService, el, afterInit) {
					    // 加载下拉列表框内容
						var ds = new Ext.data.Store({
							// 设置远程代理
					        proxy: new Ext.ux.data.DWRProxy({
					            method: orgDWRService.getAllDeptsDataset
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
						
						// 员工表单
					    var fp = new Ext.FormPanel({
					    	id: 'employeesForm',
					    	iconCls:'p-form-icon',
					        labelAlign: 'left',
					        buttonAlign: 'right',
					        frame: true,
					        collapsible: true,
					        title: '员工信息',
					        bodyStyle:'padding:5px 5px 0',
					        width: 770,
					        labelWidth: 60,
					        items: [{
					            layout:'column',
					            items:[{
					                columnWidth:.5,
					                layout: 'form',
					                items: [{
					                	id: 'name',
					                    xtype:'textfield',
					                    allowBlank: false,
					                    fieldLabel: '员工姓名',
					                    anchor:'80%'
					                },{
					                	id: 'email',
					                    xtype:'textfield',
					                    vtype:'email',
					                    fieldLabel: '电子邮箱',
					                    anchor:'80%'
					                }]
					            },{
					                columnWidth:.5,
					                layout: 'form',
					                items: [{
					                	id: 'department',
					                	hiddenName: 'department.id',
										xtype:'combo',
										allowBlank: false,
										store: ds,
										valueField :"DEPT_ID",
										displayField: "FULLNAME",
										forceSelection: false,
										editable: false,
										mode: 'local',
										triggerAction: 'all',
										fieldLabel: '所属部门',
										anchor:'80%' 
									},{
					                	id: 'mobile',
					                    xtype:'textfield',
					                    fieldLabel: '移动电话',
					                    anchor:'80%'
					                }] 
					            }]
					        }],
					
					        buttons: [{
					            text: '保存',
					            type:'button',
					            handler: function (btn, e) {
									var panel = Ext.getCmp('employeesForm');
									var form = panel.getForm();
									if(form.isValid()) {
										var values = form.getValues();
										// 设置Employee对象属性值
										Ext.apply(employee, values);
										employee['department'] = {id: values['department.id']};
										// 打开遮幕
										panel.el.mask('正在提交...', 'x-mask-loading');
										orgDWRService.updateEmployee(employee, {
											callback: function() {
												Ext.MessageBox.alert('提示', '信息保存成功!');
												// 移除遮幕
												panel.el.unmask(false);
											},
											// 异常处理
											exceptionHandler: function(exception) {
												Ext.MessageBox.alert('警告', exception);
												// 移除遮幕
												panel.el.unmask(false);
											}
										});
									}
					            }
					        },{
					            text: '重置',
					            type:'reset',
					            handler: function (btn, e) {
					            	setEmployee(employee)
					            }
					        },{
					            text: '返回',
					            type:'button',
					            handler: function (btn, e) {
									window.history.back();
					            }
					        }]
					    });
		
					    fp.render(el);
					    
					    ds.on('load', function() {
					    	afterInit(fp);
					    }, this);
					    
					    // 提前加载部门数据
					    ds.load();
					    
					    return fp;
					}
				}
			}();
			
			function setEmployee(data) {
				// 设置员工信息
				employee = data;
				var panel = Ext.getCmp('employeesForm');
				var form = panel.getForm();
		        form.items.each(function(f){
		        	var v = employee[f.id];
		        	if(v){
		        		f.setValue(v.id ? v.id : v);
		        	}
		        });
			}
			
			Ext.onReady(function() {
			    Ext.QuickTips.init();
			    Ext.form.Field.prototype.msgTarget = 'side';
			    
			    var panel = Prophet.org.EmployeesForm.init(orgDWRService, 'employees-form', function (p) {
			    	// 当EmployeesForm初始化完成后加载数据
					p.el.mask('加载中...', 'x-mask-loading');
			    	// 调用远程对象, 获取员工信息
					orgDWRService.getEmployee('<%=employeeId%>', {
						callback: function(data) {
							// 设置员工信息
							setEmployee(data);
							// 移除遮幕
							p.el.unmask(false);
						},
						// 异常处理
						exceptionHandler: function(exception) {
							Ext.MessageBox.alert('警告', exception);
							// 移除遮幕
							p.el.unmask(false);
						}
					});
			    });
			});
		</script>
	</head>
	<body style="padding: 15px;">
		<p>
			<div id="employees-form"></div>
		</p>
	</body>
</html>