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
		<title>Form Fields演示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<script type="text/javascript" src="<%=rootPath%>/scripts/extjs/extend/Label.js"></script>
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/scripts/extjs/extend/resources/css/form.css"></link>
  		<!-- Prophet CSS -->
  		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/styles/common.css"></link>
  		<!-- Page JS -->
		<script type="text/javascript">
		Ext.onReady(function(){ 
		    Ext.QuickTips.init();
		    Ext.form.Field.prototype.msgTarget = 'side';
			
			var arr=[[1, '韦海晗'], [2, '谢立锋'],[3, '伍军军']]; 
			var reader = new Ext.data.ArrayReader( 
				{id: 0}, 
				[
					{name: 'value'}, 
					{name: 'key'} 
				]);
			
			var store=new Ext.data.Store({ 
				reader: reader 
			}); 
			store.loadData(arr); 
			
			var fp = new Ext.FormPanel({ 
		        labelAlign: 'left',
		        buttonAlign: 'right',
		        title: 'Form Fields',
		        bodyStyle:'padding:5px 5px 0;',
				renderTo: Ext.getBody(),
				collapsible: true,
				frame: true, 
				width: 800, 
				labelWidth: 75, 
				defaultType: 'textfield', 
				items: [{
		            xtype: 'fieldset',
		            title: 'checkbox',
		            collapsible: true,
		            autoHeight: true,
		            layout:'column',
		            items:[{
		                columnWidth: .5,
		                layout: 'form',
		                items: [{
							xtype: 'checkbox',
							boxLabel: 'checkbox 1',
							labelSeparator: '', 
							name: 'cb', 
							checked: true
		                }]
		            },{
		                columnWidth: .5,
		                layout: 'form',
		                items: [{
							xtype: 'checkbox',
							boxLabel: 'checkbox 2',
							labelSeparator: '', 
							name: 'cb', 
							checked: true
						}] 
		            }]
		            }, {
						xtype: 'fieldset',
						title:'radio',
						collapsible: true,
			            autoHeight: true,
			            layout:'column',
						items:[{
							columnWidth: .5,
							layout: 'form',
			                items: [{
								xtype: 'radio',
								boxLabel: 'radio 1',
								name: 'radio', 
								labelSeparator: '',
								checked: true
							}]
						}, {
							columnWidth: .5,
							layout: 'form',
			                items: [{
								xtype: 'radio',
								boxLabel: 'radio 2',
								name: 'radio', 
								labelSeparator: '',
								checked: true
							}]
						}]
					}, {
						xtype: 'fieldset',
						title:'text',
						collapsible: true,
			            autoHeight: true,
			            layout:'column',
			            items: [{
							columnWidth: 1,
							layout: 'column',
			                items: [{
								columnWidth: .5,
								layout: 'form',
				                items: [{
									xtype: 'textfield',
									fieldLabel: 'text', 
									name: 'text', 
									allowBlank: false,
									anchor:'90%'
								}]
							}, {
								columnWidth: .5,
								layout: 'form',
				                items: [{
									xtype: 'numberfield',
									fieldLabel: 'number',
									name: 'number',
									allowNegative: true,
									anchor:'90%'
								}]
							}]
						}, {
							columnWidth: 1,
							layout: 'form',
			                items: [{
								xtype: 'textarea',
								fieldLabel:'textarea',
								name:'textarea',
								anchor:'95%'
							}]
						}]
					}, {
						xtype: 'fieldset',
						title:'combo',
						collapsible: true,
			            autoHeight: true,
			            layout:'column',
			            items: [{
							columnWidth: .5,
							layout: 'form',
			                items: [{
								xtype: 'combo',
								fieldLabel:'select',
								editable: false, 
								triggerAction: 'all', 
								valueField: 'value', 
								displayField: 'key', 
								mode: 'local', 
								store: store,
								anchor:'90%'
							}]
						}, {
							columnWidth: .5,
							layout: 'form',
			                items: [{
								xtype: 'combo',
								fieldLabel:'combobox', 
								displayField: 'key', 
								mode: 'local', 
								store: store,
								anchor:'90%'
							}]
						}]
					}, {
						xtype: 'fieldset',
						title:'label',
						collapsible: true,
			            autoHeight: true,
			            layout:'column',
			            items: [{
							columnWidth: .5,
							layout: 'form',
			                items: [{
			                    xtype:'label',
			                    fieldLabel:'label 1',
			                    encode: false,
			                    value: "label 1 text <br/> label 1 text",
			                    anchor:'90%'
							}]
						}, {
							columnWidth: .5,
							layout: 'form',
			                items: [{
			                    xtype:'label',
			                    fieldLabel:'label 2',
			                    value: "label 2 text <br/> label 2 text",
			                    anchor:'90%'
							}]
						}]
					}, {
						xtype: 'fieldset',
						title:'datetime',
						collapsible: true,
			            autoHeight: true,
			            layout:'column',
			            items: [{
							columnWidth: .5,
							layout: 'form',
			                items: [{
								xtype: 'datefield',
								fieldLabel:'datefield',
								format:'Y-m-d',
								disabledDays:[0, 6],
								anchor:'90%'
							}]
						}, {
							columnWidth: .5,
							layout: 'form',
			                items: [{
								xtype: 'timefield',
								fieldLabel: 'timefield',
								mode: 'local',
								increment:60,
								anchor:'90%'
							}]
						}]
					}, {					
						xtype: 'fieldset',
						title:'htmleditor',
						collapsible: true,
			            autoHeight: true,
			            layout:'fit',
			            items: [{
		                    xtype:'htmleditor',
		                    fieldLabel:'htmleditor',
		                    id:'editor'
			            }]
				}],
		        buttons: [{
		            text: '提交',
		            type:'button',
		            handler: function (btn, e) {

		            }
		        },{
		            text: '重置',
		            type:'reset',
		            handler: function (btn, e) {
						fp.getForm().reset();
		            }
		        }]
			});
			
			fp.show();
		});
		</script>
	</head>
	<body>

	</body>
</html>