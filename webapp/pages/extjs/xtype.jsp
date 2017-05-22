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
		<title>Xtype Example</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<!-- Page JS -->
		<script type="text/javascript">
	    Ext.ns('Tutorial');
	    Tutorial.MyComboBox = Ext.extend(Ext.form.ComboBox, {});
	    
	    // 注册Tutorial.MyComboBox类成xtype，以便延迟加载
	    Ext.reg('mycombo', Tutorial.MyComboBox);
	    
	    Ext.onReady(function() {
	        Ext.QuickTips.init();
	        Ext.form.Field.prototype.msgTarget = 'side';
	 
	        // create example window
		    var win = new Ext.Window({
		         width:500
		        ,id:'winid'
		        ,height:300
		        ,layout:'fit'
		        ,border:false
		        ,closable:false
		        ,title:'Xtype Example'
		        ,items:[{
		             xtype:'form'
		            ,frame:true
		            ,defaultType:'textfield'
		            ,items:[{
		                  xtype:'mycombo' // 延时实例化Tutorial.MyComboBox
		                 ,fieldLabel:'Select Language'
		                 ,name:'locale'
		                 ,store:new Ext.data.SimpleStore({
		                     id:0
		                    ,fields:['file', 'locale']
		                    ,data:[
	                           	['zh', 'Chinese']
	                            ,['cs', 'Czech']
	                            ,['en', 'English']
	                            ,['fr', 'French']
	                            ,['de', 'German']
	                            ,['gr', 'Greece']
	                            ,['hu', 'Hungarian']
	                            ,['it', 'Italian']
	                            ,['ja', 'Japaneese']
	                            ,['pl', 'Polish']
	                            ,['pt', 'Portugal']
	                            ,['ru', 'Russian']
	                            ,['sk', 'Slovak']
	                            ,['es', 'Spanish']
	                            ,['sv_SE', 'Swedish']
	                            ,['tr', 'Turkish']
		                    ]
		                 })
		                ,mode:'local'
		                ,editable:false
		                ,forceSelection:true
		                ,valueField:'file'
		                ,displayField:'locale'
		                ,triggerAction:'all'
		                ,value:'Chinese'
		             }]
		        }]
		    });
	        win.show();
	    });
		</script>
	</head>
	<body>

	</body>
</html>