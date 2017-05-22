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
		<title>IFrame Window演示</title>
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
		    var win;
		    var button = Ext.get('show-btn');
		    button.on('click', function(){
				if(!win){
					//  创建模版
					var tpl = new Ext.Template('<iframe src="{src}" scrolling="{scrolling}" frameborder="{frameborder}" width="{width}" height="{width}" />');
					tpl.compile();
					
					var ifp = new Ext.Panel({
						html: tpl.applyTemplate({
						    src: './iframe.html', 
						    scrolling: 'auto', 
						    frameborder: 'no',
						    width: '100%',
						    height: '100%'
						})
						//html: '<iframe src="./iframe.html" scrolling="auto" frameborder="no" width="100%" height="100%"></iframe>'
					});

			        win = new Ext.Window({
			            title: 'IFrame Window',
			            maximizable: true,
			            collapsible: true,
			            closable: true,
			            resizable: true,
			            width: 600,
			            height: 350,
				        minWidth: 300,
				        minHeight: 200,
			            layout: 'fit',
			            plain: true,
			            closeAction: 'hide',
			            bodyStyle: 'padding:5px;',
			            items: ifp,
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