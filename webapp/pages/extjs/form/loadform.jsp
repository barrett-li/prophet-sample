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
		<title>Load Form 演示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<script type="text/javascript" src="<%=rootPath%>/dwr/org/interface/orgDWRService.js"></script>
  		<!-- Ext JS Extend -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/DWRProxy.js"></script>
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/DWRAction.js"></script>
		<!-- Prophet CSS -->
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
		<!-- Page JS -->
		<script type="text/javascript">
			var employee = {
				id : '8aa0901d-1347b0bb-0113-47b1e4b4-0002'
			};

			Ext.onReady(function() {
			    Ext.QuickTips.init();
			    Ext.form.Field.prototype.msgTarget = 'side';
				Ext.form.Action.ACTION_TYPES = {
				    'load' : Ext.ux.form.DWRAction.Load,
				    'submit' : Ext.ux.form.DWRAction.Submit
				};
				
			    // 加载下拉列表框内容
				var ds = new Ext.data.Store({
					// 设置远程代理
			        proxy: new Ext.ux.data.DWRProxy({
			            //method: orgDWRService.getAllDeptsDataset
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
			    	renderTo: Ext.getBody(),
			    	iconCls:'p-form-icon',
			        labelAlign: 'left',
			        buttonAlign: 'right',
			        frame: true,
			        collapsible: true,
			        title: '员工信息',
			        bodyStyle:'padding:5px 5px 0',
			        width: 770,
			        labelWidth: 60,
			        waitMsgTarget: true,
			        // configure how to read the JSON Data
			        reader : new Ext.data.JsonReader({
			            root: 'employee',
			            successProperty: 'success'
			        }, [
			            'id', 'name', 'email', 'mobile',
			            {name: 'department', mapping:'department.id'}
			        ]),
			        items: [{
			            layout:'column',
			            items:[{
			                columnWidth:.5,
			                layout: 'form',
			                items: [{
			                	name: 'name',
			                    xtype:'textfield',
			                    allowBlank: false,
			                    fieldLabel: '员工姓名',
			                    anchor:'80%'
			                },{
			                	name: 'email',
			                    xtype:'textfield',
			                    vtype:'email',
			                    fieldLabel: '电子邮箱',
			                    anchor:'80%'
			                }]
			            },{
			                columnWidth:.5,
			                layout: 'form',
			                items: [{
			                	hiddenName: 'department',
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
			                	name: 'mobile',
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
			            	var form = fp.getForm();
							var values = form.getValues();
							// 设置Employee对象属性值
							Ext.apply(employee, values);
							employee['department'] = {id: values['department']};
			            	form.submit({
				            	remote: function(action, o, root, successProperty, params, callback){
				    				// 调用远程方法
				            		orgDWRService.updateEmployee(params.employee, callback);
					            }, 
					            params: {employee: employee}, 
					            waitMsg:'正在提交...'
				            });
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

			    ds.on('load', function() {
			    	fp.getForm().load({
			    		remote: function(action, o, root, successProperty, params, callback){
			    			// 设置回调函数
			    			Ext.apply(callback, {
								callback: function(response){
									// 设置结果集
									var result = {};
									result[root] = [response];
									result[successProperty] = true;
									action.success(result);
								}
		            		});
		    				// 调用远程方法
		            		orgDWRService.getEmployee(params.id, callback);
			            }, 
			           	params: {id: employee.id}, 
			           	waitMsg:'加载中...'
					});
			    }, this);
			    
			    // 提前加载部门数据
			    ds.load();
			    
			    fp.show();
			});
		</script>
	</head>
	<body style="padding: 15px;">

	</body>
</html>