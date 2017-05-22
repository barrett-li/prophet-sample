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
		<title>Form Window演示</title>
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
		    var win;
		    button.on('click', function(){
				if(!win){
				    var fp = new Ext.FormPanel({
				        labelAlign: 'left',
				        buttonAlign: 'right',
				        baseCls: 'x-plain',
				        labelWidth: 60,
			            items:[{
					        layout:'column',
					        baseCls: 'x-plain',
				            defaults: {
				            	layout: 'form',
				                baseCls: 'x-plain'
				            },
				            items: [{
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
				            }]
			            }]
				    });
				    
				    fp.doLayout();
			        win = new Ext.Window({
			            title: 'Form Window',
			            maximizable:true,
			            collapsible: true,
			            width: 600,
			            height: 350,
				        minWidth: 300,
				        minHeight: 200,
				        plain: true,
				        layout: 'fit',
				        bodyStyle: 'padding:5px;',
				        closeAction: 'hide',
			            items: [fp],
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