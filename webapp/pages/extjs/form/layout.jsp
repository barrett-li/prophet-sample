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
		<title>Form Layout演示</title>
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
		    Ext.QuickTips.init();
		    Ext.form.Field.prototype.msgTarget = 'side';
		    
			var bd = Ext.getBody();
			
			// column布局
		    var cfp = new Ext.FormPanel({
		    	renderTo: bd,
		        labelAlign: 'left',
		        buttonAlign: 'right',
		        title: 'Form Layout',
		        bodyStyle:'padding:5px 5px 0;',
				renderTo: Ext.getBody(),
				collapsible: true,
				frame: true, 
				width: 500, 
		        bodyStyle:'padding:5px 5px 0;',
		        labelWidth: 60,
		        items: [{
		        	// 第1组, 共2列, 3行
		            layout:'column',
		            items:[{
		                columnWidth:.5,
		                layout: 'form',
		                items: [{
		                    xtype:'textfield',
		                    fieldLabel: 'field1.0',
		                    anchor:'80%'
		                }, {
		                    xtype:'textfield',
		                    fieldLabel: 'field1.1',
		                    anchor:'80%'
		                }, {
		                    xtype:'textfield',
		                    fieldLabel: 'field1.2',
		                    anchor:'80%'
		                }]
		            }, {
		                columnWidth:.5,
		                layout: 'form',
		                items: [{
		                    xtype:'textfield',
		                    fieldLabel: 'field2.0',
		                    anchor:'80%'
						}, {
		                    xtype:'textfield',
		                    fieldLabel: 'field2.1',
		                    anchor:'80%'
		                }, {
		                	// 该单元为空对象或不添加该对象实例
		                }] 
		            }]
		        // 第2组, 共1列, 2行
		    	}, {
		            layout:'column',
		            items:[{
		                columnWidth:1,
		                layout: 'form',
		                items: [{
		                    xtype:'textfield',
		                    fieldLabel: 'field3.0',
		                    anchor:'95%'
		                }, {
		                    xtype:'textfield',
		                    fieldLabel: 'field3.1',
		                    anchor:'95%'
		                }]
		            }]
		        // 第3组, 共1列, 1行, textarea
		    	}, {
					layout:'column',
		            items:[{
		            	columnWidth:1,
			            layout:'form',
			            items:[{
							xtype: 'textarea',
							fieldLabel: 'field4.0',
							anchor:'94.5%'
			            }]
		    		}]
		    	}]
		    });
			
			cfp.show();
			
			bd.createChild({tag: 'div', style: 'margin-bottom: 15px;'});
		});
		</script>
	</head>
	<body>

	</body>
</html>