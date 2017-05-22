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
		<title>Grid-From Window演示</title>
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
		
		    var button = Ext.get('show-btn');
			var employees = {
	        	roots: [
		            {name: '韦海晗', email: 'weihaihan@resoft.css.com.cn'},
		            {name: '谢立锋', email: 'xielifeng@resoft.css.com.cn'},
		            {name: '张奇', email: 'zhangqi@resoft.css.com.cn'}
	        	],
	        	totalCount: 3
		    };
		    var win;
		    button.on('click', function(){
				if(!win){
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
				    
				    var sm = new Ext.grid.CheckboxSelectionModel()
				    // 设置表格样式
				    var cm = new Ext.grid.ColumnModel([sm, {
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
				        store: store,
				        cm: cm,
				        trackMouseOver:false,
				        sm: sm,
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
				
					// 查询表单
				    var fp = new Ext.FormPanel({
				        labelAlign: 'left',
				        buttonAlign: 'right',
				        labelWidth: 60,
				        baseCls: 'x-plain',
				        layout: 'column',
				        items: [{
				            xtype:'fieldset',
				            collapsible: true,
				            title: '查询条件',
				            autoHeight: true,
				            bodyStyle: 'padding:5px;',
				            layout: 'column',
				            defaults: {
				            	layout: 'form',
				                baseCls: 'x-plain'
				            },
				            items:[{
				                columnWidth: .5,
				                items: [{
				                    xtype:'textfield',
				                    fieldLabel: '员工姓名',
				                    anchor:'80%'
				                }]
				            },{
				                columnWidth: .5,
				                items: [{
				                    xtype:'textfield',
				                    vtype:'email',
				                    fieldLabel: '电子邮箱',
				                    anchor:'80%'
								}] 
				            }]
				    	}],
				        buttons: [{
				            text: '查询',
				            type:'button',
				            handler: function (btn, e) {
	
				            }
				        },{
				            text: '重置',
				            type:'reset',
				            handler: function (btn, e) {
								
				            }
				        }]
				    });

			        win = new Ext.Window({
			            title: 'Grid-From Window',
			            maximizable:true,
			            collapsible: true,
			            width: 600,
			            //height: 350,
				        minWidth: 300,
				        minHeight: 200,
				        plain: true,
				        layout: 'column',
				        bodyStyle: 'padding:5px;',
				        closeAction: 'hide',
			            defaults: {
			                baseCls: 'x-plain'
			            },
			            items: [{
				            columnWidth: 1,
				            layout: 'column',
				    		items: fp
				    	}, {
				            columnWidth: 1,
				            layout: 'fit',
				    		items: gp
				    	}],
				        buttons: [{
				            text: '确定',
				            type:'button',
				            handler: function (btn, e) {
	
				            }
				        },{
				            text: '取消',
				            type:'reset',
				            handler: function (btn, e) {
								win.hide();
				            }
				        }]
			        });
				}
		        win.show(this);
		        
		    });
		});
		</script>
	</head>
	<body>
		<input type="button" id="show-btn" value="Show Window"/>
	</body>
</html>