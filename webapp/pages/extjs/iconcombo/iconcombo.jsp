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
		<title>Icon Combo Ext 2.0 Plugin Example</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- Common JS -->
		<%@ include file="/templets/common/scripts.jsp"%>
		<!-- DWR 2.0 (http://getahead.org/dwr) -->
  		<!-- Ext JS Extend -->
  		<script type="text/javascript" src="Ext.ux.plugins.js"></script>
  		<!-- Prophet CSS -->
	 	<style type="text/css">
	    .ux-flag-us {
	        background-image:url(./us.png) ! important;
	    }
	    .ux-flag-de {
	        background-image:url(./de.png) ! important;
	    }
	    .ux-flag-fr {
	        background-image:url(./fr.png) ! important;
	    }
	    .ux-icon-combo-icon {
	        background-repeat: no-repeat;
	        background-position: 0 50%;
	        width: 18px;
	        height: 14px;
	    }
	 
	    /* X-BROWSER-WARNING: this is not being honored by Safari */
	    .ux-icon-combo-input {
	        padding-left: 25px;
	    }
	 
	    .x-form-field-wrap .ux-icon-combo-icon {
	        top: 3px;
	        left: 5px;
	    }
	    .ux-icon-combo-item {
	        background-repeat: no-repeat ! important;
	        background-position: 3px 50% ! important;
	        padding-left: 24px ! important;
	    }
	    </style>
 
    	<script type="text/javascript">
		Ext.onReady(function() {
		    var win = new Ext.Window({
		         title:'Icon Combo Ext 2.0 Plugin Example'
		        ,width:400
		        ,height:300
		        ,layout:'form'
		        ,bodyStyle:'padding:10px'
		        ,labelWidth:70
		        ,defaults:{anchor:'100%'}
		        ,items:[{
		             xtype:'combo'
		            ,fieldLabel:'IconCombo'
		            ,store: new Ext.data.SimpleStore({
		                    fields: ['countryCode', 'countryName', 'countryFlag'],
		                    data: [
		                        ['US', 'United States', 'ux-flag-us'],
		                        ['DE', 'Germany', 'ux-flag-de'],
		                        ['FR', 'France', 'ux-flag-fr']
		                    ]
		            }),
		            plugins:new Ext.ux.plugins.IconCombo(),
		            valueField: 'countryCode',
		            displayField: 'countryName',
		            iconClsField: 'countryFlag',
		            triggerAction: 'all',
		            mode: 'local'
		        }]
		    });
		    win.show();
		});
    	</script>
	</head>
	<body>
	
	</body>
</html>